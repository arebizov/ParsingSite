package ru.parsing.files.crushedStone;

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

public class BetonTransStroy {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1453;
    private static String unit = "М3";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.betontransstroy.ru/catalog/gravijnyj-shheben/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                String page = LoadFromSiteSelenium.download(url, ll.indexOf(url));
                store = Profile.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                Elements rows = doc.getElementsByClass("product__right").select("tr");
                for (int i = 2; i < rows.size() - 1; i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String offers = cols.get(0).text();
                    String priceStr = cols.get(1).text().replace(" ", "").split("руб")[0];
                    Double price = Double.valueOf(priceStr);
                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }


        }
        return listSourceAll;
    }
}
