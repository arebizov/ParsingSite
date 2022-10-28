package ru.parsing.files.beton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Paritet {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 17286;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("http://s-paritet.ru/price/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                LoadFromSite loadFromSite = new LoadFromSite();
                String store = loadFromSite.getStore(url);
                Profile profile = new Profile();
                Date date = profile.getDate();


                Document doc = Jsoup.connect(url).get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");
                for (int i = 2; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.get(1).text().length() > 1) {

                        String name = cols.get(1).text();
                        String unit = cols.get(2).text();
                        float price = Float.parseFloat(cols.get(4).text().replace("-", "."));

                        listSource.add(new SourceData(store, name, unit, price, date, category));
                    }
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("ошибка обработки Paritet");
            }

        }
        return listSource;
    }

}
