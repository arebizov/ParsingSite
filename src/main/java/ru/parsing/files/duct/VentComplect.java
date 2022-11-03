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


public class VentComplect {

    private static int category = 94777249;
    private static String unit = "пм";
    public String store;

    public List<SourceData> listSource = new ArrayList<>();


    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("http://www.xn----ctbjabaradsbgenidaqfv5cpx1d4evb6fk.xn--p1ai/goods/47706508-otsinkovanny_listovoy_metall_0_7_1250_2500_mm");

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

            } catch (IOException | NumberFormatException |NullPointerException| IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }

        }
        return listSource;
    }

}
