package ru.parsing.files.pipes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HidroControl {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 7189;
    private static String unit = "М";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.hidrocontrol.ru/products/truba-rautitan-pink-artikul-136062-00");
        pagesList.add("https://www.hidrocontrol.ru/products/truba-rautitan-pink-artikul-136072-00");
        pagesList.add("https://www.hidrocontrol.ru/products/truba-rehau-rautitan-stabil-25-h-3-7-buhta-50-m");
        pagesList.add("https://www.hidrocontrol.ru/products/truba-rautitan-flex-artikul-130390-05");
        pagesList.add("https://www.hidrocontrol.ru/products/truba-rautitan-flex-artikul-130400-00");


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
                String priceStr = doc.getElementsByClass("details-payment-block").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr.replace("₽", "").replace(" ", ""));
//            System.out.println(price);
//            System.out.println(offers);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category + " "+url);
            }
        }
        return listSourceAll;
    }

}
