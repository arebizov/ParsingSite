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

    private static int category = 1449;
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("http://s-paritet.ru/price/");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                store = Profile.getStore(url);
                Profile profile = new Profile();
                Date date = profile.getDate();


                Document doc = Jsoup.connect(url).get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");
                for (int i = 2; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.get(1).text().length() > 1) {

                        String offers = cols.get(1).text();
                        String unit = cols.get(2).text();
                        float price = Float.parseFloat(cols.get(4).text().replace("-", "."));

                        listSource.add(new SourceData(store, offers, unit, price, date, category));
                    }
                }

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }

        }
        return listSource;
    }

}
