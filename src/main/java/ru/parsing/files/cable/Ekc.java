package ru.parsing.files.cable;

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

public class Ekc {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://e-kc.ru/cena/cable-vvgng-3-1_5");
        pagesList.add("https://e-kc.ru/cena/cable-vvgng-3-2_5");



        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                LoadFromSite loadFromSite = new LoadFromSite();
                String page = LoadFromSite.download(url, ll.indexOf(url));
                String store = loadFromSite.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);

                Element table = doc.select("table").get(2);

//            System.out.println(table);
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size() - 1; i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.get(1).text().length() > 1) {
                        if (cols.get(0).text().length() > 1) {

                            String offers = cols.get(0).text();
                            String priceStr = cols.get(2).text().split(" ")[0];
                            Double price = Double.valueOf(priceStr);
                            System.out.print(offers);
                            System.out.println(price);
                            listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                        }

                    }

                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки Cable");
            }
        }

        return listSourceAll;

    }

}
