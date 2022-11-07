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

public class Ccomplekt {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";

    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://cckomplekt.ru/catalog/kirpich/ryadovoi-kirpich/5373/");

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
                Elements items = doc.getElementsByClass("item-card");
                for (Element item : items) {
                    String pricetr = item.getElementsByClass("pay").get(0).getElementsByClass("price").get(0).childNode(0).toString();
                    Double price = Double.valueOf(pricetr.split(" ")[0]);
                    String offers = doc.select("h1").html();
//                System.out.println(offers);
//                System.out.println(price);
                    listSource.add(new SourceData(store, offers, unit, price, date, category));
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
