package ru.parsing.files.haydite;

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

public class NerudSnabHaydite {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1453;
    private static String unit = "m3";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();

        pagesList.add("https://nerudsnab.ru/catalog/keramzit/keramzit-rossypju/");

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
                Elements table = doc.select("table").get(0).getElementsByAttributeValue("id","tablepress-12");
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    String offers = cols.get(0).text();
                    String priceStr = cols.get(2).text().split(" ")[1];
                    Double price = Double.valueOf(priceStr);
//                    System.out.println(offers);
//                    System.out.println(price);
                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("???????????? ???????????? ???????????? (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("?????????????????? ???????????? ???????????? " + url);
            }
        }
        return listSourceAll;
    }

}
