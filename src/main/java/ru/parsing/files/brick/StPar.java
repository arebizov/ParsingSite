package ru.parsing.files.brick;

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

public class StPar {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://st-par.ru/catalog/2_01_kirpich_stroitelnyy/20799/");
        pagesList.add("https://st-par.ru/catalog/2_01_kirpich_stroitelnyy/21301/");
        pagesList.add("https://st-par.ru/catalog/kirpich-oblicovochnyj/2652/");
        pagesList.add("https://st-par.ru/catalog/kirpich-oblicovochnyj/1372/");




        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {

                LoadFromSite loadFromSite = new LoadFromSite();
                String store = loadFromSite.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.connect(url).get();

                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr);
                System.out.println(offers);
                System.out.println(priceStr);

                listSource.add(new SourceData(store, offers, unit, price, date, category));

            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки CComplekt");
                ;
            }
        }
        return listSource;
    }
}
