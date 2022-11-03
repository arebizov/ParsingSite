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

public class Alkasar {

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
                Elements items = doc.getElementsByAttributeValue("class", "item_info N");
                for (Element item : items) {

                    String offers = item.getElementsByTag("a").get(0).text();
                    String priceStr = item.getElementsByClass("price").get(0).text().split("руб")[0].replace(" ", "");
                    Double price = Double.valueOf(priceStr);

//                    System.out.println(offers);
//                    System.out.println(price);
                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки" + store + " " + category);
            }
        }
        return listSourceAll;
    }

}
