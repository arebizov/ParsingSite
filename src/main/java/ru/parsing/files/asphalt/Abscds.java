package ru.parsing.files.asphalt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Abscds {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1453;


    private static String unit = "тонна";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://abscds.ru/products/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                LoadFromSite loadFromSite = new LoadFromSite();

                String page = LoadFromSiteSelenium.download(url, ll.indexOf(url));
                store = Profile.getStore(url);
                Profile profile = new Profile();
                Date date = profile.getDate();
                Document doc = Jsoup.parse(page);
                Element table = doc.select("table").get(3);
//                System.out.println(table);
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    String offers = cols.get(0).text();
                    String priceStr = cols.get(1).text();
                    Double price = Double.valueOf(priceStr);


//                    System.out.println(offers);
//                    System.out.println(price);
                    listSource.add(new SourceData(store, offers, unit, price, date, category));

                }

            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }

        }
        return listSource;
    }

}
