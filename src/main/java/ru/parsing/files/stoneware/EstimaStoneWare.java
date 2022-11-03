package ru.parsing.files.stoneware;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstimaStoneWare {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 7140;
    private static String unit = "M2";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://msk.estima.ru/catalog/gabbro/gb03_60x60x10_nepol_rekt_keramicheskiy_granit/");
        pagesList.add("https://msk.estima.ru/catalog/gabbro/gb03_60x120x10_nepol_rekt_keramicheskiy_granit/");
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
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("product-price-value").get(0).child(0).child(0).text();//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr.replace(" ", ""));
//            System.out.println(offers);
//            System.out.println(price);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category + " "+url);
            }
        }

        return listSourceAll;
    }

}
