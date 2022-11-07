package ru.parsing.files.asphalt;

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


public class PlatonStroy {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1453;
    public String store;

    private static String unit = "тонна";

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://2221722.ru/podolsk-sheben");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {

                store = Profile.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Profile profile = new Profile();
                Date date = profile.getDate();
                Document doc = Jsoup.parse(page);
                Elements table = doc.getElementsByClass("field field-name-body field-type-text-with-summary field-label-hidden").select("table");
                Elements rows = table.select("tr");
                for (int i = 3; i < rows.size()-1; i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if(cols.size()>1) {

                        String offers = cols.get(0).text();
                        int size = cols.get(1).childNodes().size()-1;
                        Double price = null;
                        if (size >0) {
                            price = Double.valueOf(cols.get(1).text().split("↓ ")[1].split(" ")[0]);
                        } else {
                            price = Double.valueOf(cols.get(1).text().split(" ")[0]);
                        }
//                        System.out.println(offers);
//                        System.out.println(price);
                        listSource.add(new SourceData(store, offers, unit, price, date, category));
                    }
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
