package ru.parsing.files.paving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GvavSnabPaving {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 10049;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://glavsnab.net/trotuarnaya-plitka-braer-pryamougolnik-seraya-200h100h40-mm.html");
        pagesList.add("https://glavsnab.net/trotuarnaya-plitka-braer-pryamougolnik-seraya-200h100h60-mm.html");
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
                String priceStr = doc.getElementsByClass("product-prices clearfix").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr);
//            System.out.println(offers);
//            System.out.println(price);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }

        return listSourceAll;
    }

}
