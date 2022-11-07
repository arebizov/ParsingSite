package ru.parsing.files.heater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StParHeater {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1285;
    private static String unit = "m3";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://st-par.ru/catalog/mineralnaya_vata/19096/");  //4.16666
        pagesList.add("https://st-par.ru/catalog/mineralnaya_vata/15592/"); //4.16666
        pagesList.add("https://st-par.ru/catalog/mineralnaya_vata/19113/"); //3.0303


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
                Document doc = Jsoup.connect(url).get();

                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = null;
                if (ll.indexOf(url) < 2) {
                    price = Double.valueOf(priceStr) * 4.16666;
                } else {
                    price = Double.valueOf(priceStr) * 3.0303;
                }
//                System.out.println(offers);
//                System.out.println(priceStr);

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
