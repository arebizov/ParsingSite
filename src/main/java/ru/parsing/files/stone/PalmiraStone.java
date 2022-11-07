package ru.parsing.files.stone;

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

public class PalmiraStone {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 46554423;
    private static String unit = "m2";

    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://alkasar05.ru/catalog/oblicovochnyj-kamen/dolomit-osetinskiy-genaldon/");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                LoadFromSite loadFromSite = new LoadFromSite();
                String page = LoadFromSite.download(url, ll.indexOf(url));
                store = loadFromSite.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                Elements items = doc.getElementsByAttributeValue("class", "catalog_wrap_item flex");
                for (Element item : items) {

                    String offers = item.getElementsByTag("h4").html();
                    String priceStr = item.getElementsByClass("digits").get(0).text();
                    Double price = Double.valueOf(priceStr);

//                    System.out.println(offers);
//                    System.out.println(price);
                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSourceAll;
    }

}
