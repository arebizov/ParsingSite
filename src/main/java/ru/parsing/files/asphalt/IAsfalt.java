package ru.parsing.files.asphalt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IAsfalt {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1453;
    public String store;


    private static String unit = "тонна";

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://iasfalt.ru/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                store = Profile.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Profile profile = new Profile();
                Date date = profile.getDate();
                Document doc = Jsoup.parse(page);
                Element items = doc.getElementsByClass("elementor-background-overlay").nextAll().get(0);
                Elements elements = items.getElementsByClass("ct-heading h-align- wow fadeInLeft sub-style1 ct-heading-left item-st-line-left2");
                for (Element row : elements) {

                    String priceStr = items.getElementsByAttributeValue("class", "elementor-text-editor elementor-clearfix").attr("style", "vertical-align: inherit;").get(elements.indexOf(row) + 1).text().split("₽")[0].replace(" ", "");
                    try {
                        String offers = row.text();
                        Double price = Double.valueOf(priceStr);
//                        System.out.println(offers);
//                        System.out.println(price);
                        listSource.add(new SourceData(store, offers, unit, price, date, category));
                    } catch (NumberFormatException e) {

                    }
                }

            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }

        }
        return listSource;
    }

}
