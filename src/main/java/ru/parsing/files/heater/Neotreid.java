package ru.parsing.files.heater;

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


public class Neotreid {

    private static int category = 1557;
    private static String unit = "m3";

    public List<SourceData> listSource = new ArrayList<>();


    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://neotreid.ru/bazaltovaya-teploizolyaciya/rockwool/rokvul-venti-batts");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {
                String page = LoadFromSite.download(url, ll.indexOf(url));
                LoadFromSite loadFromSite = new LoadFromSite();
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                String store = loadFromSite.getStore(url);

                Elements table = doc.getElementsByClass("tab-content").select("table").get(0).getElementsByAttributeValue("border","1");
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.get(1).text().length() > 1) {

                        String offers = cols.get(1).text();
                        String priceStr = cols.get(7).text().replace(" ","").replace(",",".");
                        Double  price = Double.valueOf(priceStr);
                        System.out.println(offers);
                        System.out.println(price);


                        listSource.add(new SourceData(store, offers, unit, price, date, category));
                    }
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("ошибка обработки Paritet");
            }

        }
        return listSource;
    }

}
