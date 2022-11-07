package ru.parsing.files.duct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VentSystem {

    private static int category = 94777249;
    private static String unit = "пм";
    public String store;

    public List<SourceData> listSource = new ArrayList<>();


    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://vent-mo.ru/goods/236589935/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                String page = LoadFromSite.download(url, ll.indexOf(url));
                LoadFromSite loadFromSite = new LoadFromSite();
                Date date = Profile.getDate();
                Document doc = Jsoup.connect(url).get();
                store = loadFromSite.getStore(url);

                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("itemprop", "price").text();
                Double price = Double.valueOf(priceStr);
//                System.out.println(price);
//                System.out.println(offers);

                listSource.add(new SourceData(store, offers, unit, price, date, category));

            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }

        }
        return listSource;
    }

}
