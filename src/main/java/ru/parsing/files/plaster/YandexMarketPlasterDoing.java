package ru.parsing.files.plaster;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YandexMarketPlasterDoing {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1447;
    private static String unit = "кг";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://market.yandex.ru/product--shtukaturka-knauf-rotband-30-kg/1863692361?cpa=1");

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
                String offers = doc.getElementsByClass("product").get(0).getElementsByTag("h1").html().replace("&nbsp;", " ");
                String priceStr = doc.getElementsByClass("product").get(0).getElementsByAttributeValue("itemprop", "lowPrice").get(0).attr("content");
                Double price = Double.valueOf(priceStr.replace(" ", ""));

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }
        }

        return listSource;
    }

}
