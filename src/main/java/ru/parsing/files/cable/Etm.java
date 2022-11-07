package ru.parsing.files.cable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etm {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.etm.ru/cat/nn/2126656");
        pagesList.add("https://www.etm.ru/cat/nn/452373");


        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                store = Profile.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                String priceStr = doc.getElementsByAttributeValueContaining("title", "Цена с учетом акций и скидок от розничной цены").get(0).parentNode().parentNode().childNodes().get(1).childNodesCopy().get(0).toString();

                Double price = Double.valueOf(String.valueOf(priceStr));
                Elements hTags = doc.select("h1");
                Elements h1Tags = hTags.select("h1");
                String offers = h1Tags.html();

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSource;
    }

}
