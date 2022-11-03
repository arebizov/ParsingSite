package ru.parsing.files.drywall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RdStroyDrywall {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 113714477;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://rdstroy.ru/catalog/knauf_3/gipsokarton_gkl_12_5_1200_3000_knauf_list_274979/");

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
//                System.out.println(doc);
                String offers = doc.getElementsByTag("h1").text();

                String priceStr = doc.getElementsByClass("ga-item-price-value").get(0).getElementsByAttributeValue("itemprop", "lowPrice").text();
                Double price = Double.valueOf(priceStr) / 3.33333;
//                System.out.println(price);
//                System.out.println(offers);
                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException |NullPointerException| IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category + " "+url);

            }
        }

        return listSource;
    }

}
