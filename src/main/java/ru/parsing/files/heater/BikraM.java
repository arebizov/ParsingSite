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


public class BikraM {

    private static int category = 1285;
    private static String unit = "m3";
    public String store;

    public List<SourceData> listSource = new ArrayList<>();


    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://bikra-m.ru/isolation/uteplitel/rockwool_venti_batts_n/");//3.4722
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
                String priceStr = doc.getElementsByAttributeValue("itemprop", "price").get(0).attr("content");//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr.replace(" ", "")) * 3.4722;
//                System.out.println(offers);
//                System.out.println(price);

                listSource.add(new SourceData(store, offers, unit, price, date, category));

            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }

        }
        return listSource;
    }

}
