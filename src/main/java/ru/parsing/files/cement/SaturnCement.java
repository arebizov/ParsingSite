package ru.parsing.files.cement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaturnCement {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 10049;
    private static String unit = "кг";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://msk.saturn.net/catalog/Stroymateriali/Stroitelnie-smesi-i-grunti/TSement/TSement-TSEM-I-425N-M-500-D0-50-kg/");

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
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("itemprop", "price").text();
                Double price = Double.valueOf(priceStr.replace(" ", "")) / 50;

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException |NullPointerException| IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category + " "+url);

            }
        }

        return listSource;
    }

}
