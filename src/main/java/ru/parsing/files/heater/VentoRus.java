package ru.parsing.files.heater;

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


public class VentoRus {

    private static int category = 1285;
    private static String unit = "m3";
    public String store;

    public List<SourceData> listSource = new ArrayList<>();


    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://neotreid.ru/bazaltovaya-teploizolyaciya/rockwool/rokvul-venti-batts");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                String page = LoadFromSite.download(url, ll.indexOf(url));
                LoadFromSite loadFromSite = new LoadFromSite();
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                store = loadFromSite.getStore(url);

                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("class", "element_full_price_int").text().replace(" ", "").replace(",", ".");
                Double price = Double.valueOf(priceStr) * 4.166666;
//                System.out.println(offers);
//                System.out.println(price);

                listSource.add(new SourceData(store, offers, unit, price, date, category));

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSource;
    }

}
