package ru.parsing.files.roofIzol;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TnMSK {
    public List<SourceData> listSource = new ArrayList<>();
    private static int category = 1285;
    private static String unit = "м2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://tn-msk.ru/kat/texnoelast/texnoelast-epp/");

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
                String offers = doc.getElementsByTag("h1").get(0).childNodes().get(0).childNode(0).toString();
                String priceStr = doc.getElementsByClass("product-info__price bold mb-3").get(0).getElementsByAttributeValue("class", "woocommerce-Price-amount amount").get(0).childNodes().get(0).childNode(0).toString();
                Double price = Double.valueOf(priceStr.replace(" ", ""));

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }
        }

        return listSource;
    }

}
