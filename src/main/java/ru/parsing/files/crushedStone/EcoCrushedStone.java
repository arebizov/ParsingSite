package ru.parsing.files.crushedStone;

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

public class EcoCrushedStone {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1453;
    private static String unit = "М3";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://xn----btblbvwc3g5ab.xn--p1ai/ceny#price-21");
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
                Element table = doc.getElementsByClass("price_common price_full table-responsive").select("table").get(0);
//                System.out.println(table);
                Elements rows = table.select("tr");
                for (int i = 2; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String offers = null;
                    String priceStr = null;
                    if (cols.size() > 5) {
//
                        offers = cols.get(1).text();
                        priceStr = cols.get(2).text();

                    } else {
                        offers = cols.get(0).text();
                        priceStr = cols.get(1).text();
                    }
//                    System.out.println(offers);
                    Double price = Double.valueOf(priceStr);
//                    System.out.println(price);

                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException | NumberFormatException |NullPointerException| IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки "+store + " "+category);
            }
        }

        return listSourceAll;

    }

}
