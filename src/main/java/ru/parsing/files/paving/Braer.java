package ru.parsing.files.paving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Braer {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 10049;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://braer.ru/catalog/bruschatka/trotuarnaya-plitka-pryamougolnik-seryj-h-40-mm-dvuhslojnaya");

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
                Elements items = doc.getElementsByClass("col-lg-6 pl-md-0");
                String offers = items.get(0).getElementsByAttributeValue("itemprop", "name").html();
                String priceStr = items.get(0).getElementsByAttributeValue("class", "current_price").html();
                Double price = Double.valueOf(priceStr);

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }

        return listSource;
    }
}
