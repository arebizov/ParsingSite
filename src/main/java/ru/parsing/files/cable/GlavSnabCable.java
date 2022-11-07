package ru.parsing.files.cable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GlavSnabCable {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "M";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
//        pagesList.add("https://glavsnab.net/kabel-vvgng-a-ls-3h1-5-100-m-sevkabel-gost.html");
        pagesList.add("https://glavsnab.net/kabel-vvgng-a-ls-krugliy-3h2-5-m-promel.html");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                String page = LoadFromSite.download(url, ll.indexOf(url));
                store = Profile.getStore(url);
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
