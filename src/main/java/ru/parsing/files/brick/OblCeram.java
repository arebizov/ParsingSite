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

public class OblCeram {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();

        pagesList.add("https://obl-ceram.ru/catalog/kirpich-oblicovochnyj/?arrFilter_P1_MIN=&arrFilter_P1_MAX=&arrFilter_10_3377485650=Y&arrFilter_37_1606258402=Y&arrFilter_78_2876789844=Y&arrFilter_50_2505597371=Y&arrFilter_53_1483701346=Y&arrFilter_53_1986614753=Y&arrFilter_53_3244699096=Y&set_filter=%D0%9F%D1%80%D0%B8%D0%BC%D0%B5%D0%BD%D0%B8%D1%82%D1%8C");


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
                Elements items = doc.getElementsByClass("catalog-all-items-custom");
                for (Element item : items) {
                    String offers = item.getElementsByClass("custom-h2").get(0).getElementsByTag("a").get(0).html();
                    String priceString = item.getElementsByClass("all-layout-center-price-wholesale").get(0).getElementsByClass("ruble").html();
                    Double price = Double.valueOf(priceString);

                    listSource.add(new SourceData(store, offers, unit, price, date, category));
                }
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }
        }
        return listSource;
    }
}


