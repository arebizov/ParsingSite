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

public class RuKeram {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://rukeram.ru/catalog/kirpich/gzhel/m175/");


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
                Elements pr = doc.getElementsByClass("content bottom_border");
                Elements asd = pr.get(0).getElementsByClass("border");
                for (Element item : asd) {

                    Elements bOzonPrice = item.getElementsByClass("bOzonPrice");
                    Double price = Double.valueOf(bOzonPrice.get(0).getElementsByTag("span").get(0).getElementsByClass("eOzonPrice_main").html());
                    Elements top_zone = item.getElementsByClass("top_zone");
                    String offers = top_zone.get(0).getElementsByTag("a").get(1).getElementsByClass("name").html();

                    listSource.add(new SourceData(store, offers, unit, price, date, category));
                }
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }

        }
        return listSource;
    }

}
