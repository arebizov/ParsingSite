package ru.parsing.files.roof;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopTn {
    public List<SourceData> listSource = new ArrayList<>();
    private static int category = 1285;
    private static String unit = "м2";

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://shop.tn.ru/tehnojelast-jepp-10h1-m");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                LoadFromSite loadFromSite = new LoadFromSite();
                String store = loadFromSite.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("class", "js-first-unit-price").text();//.get(0).childNodes().get(0).childNode(0).toString();
                Double price = Double.valueOf(priceStr.replace(" ", ""));
//            System.out.println(offers);
//            System.out.println(priceStr);

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки ShopTn");
            }
        }

        return listSource;
    }

}
