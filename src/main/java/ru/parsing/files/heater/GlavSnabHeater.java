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

public class GlavSnabHeater {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1285;
    private static String unit = "m3";

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://glavsnab.net/bazaltovaya-vata-rockwool-venti-batts-1000h600h50-mm-8-shtuk-v-upakovke.html");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                LoadFromSite loadFromSite = new LoadFromSite();
                String page = LoadFromSite.download(url, ll.indexOf(url));
                String store = loadFromSite.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("product-prices clearfix").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr)*4.166666;
//            System.out.println(offers);
//            System.out.println(price);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки GlavsnabHeater");
            }
        }
        return listSourceAll;
    }

}
