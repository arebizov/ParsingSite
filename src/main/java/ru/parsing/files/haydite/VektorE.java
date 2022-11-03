package ru.parsing.files.haydite;

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

public class VektorE {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1453;
    private static String unit = "м3";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://vektor-e.ru/keramzit/v-meshkakh/");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {

                LoadFromSite loadFromSite = new LoadFromSite();
                store = loadFromSite.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                Elements items = doc.getElementsByClass("siteorigin-widget-tinymce textwidget");
//                System.out.println(items);

                for (int i = 0; i < items.size() - 1; i++) {
                    String offers = items.get(i).getElementsByTag("h3").text();

                    String priceStr = items.get(i).childNode(3).attr("style", "text-align: center;").childNode(1).childNode(0).toString();
                    Double price = Double.valueOf(priceStr);
//                    System.out.println(price);
//                    System.out.println(offers);

                    listSource.add(new SourceData(store, offers, unit, price, date, category));
                }
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category + " "+url);
            }
        }
        return listSource;
    }
}
