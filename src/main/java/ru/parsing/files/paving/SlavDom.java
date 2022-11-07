package ru.parsing.files.paving;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlavDom {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 10049;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://slavdom.ru/catalog/p/62584-trotuarnaya-plitka-vybor-la-liniya-2p-10-gladkaya-seryy-200kh100kh100-mm/");

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
                String priceStr = doc.getElementsByClass("cart__prices-row").get(0).getElementsByAttributeValue("class", "cart__price-item is-black").get(0).childNodes().get(0).toString();
                Double price = Double.valueOf(priceStr.replace(" ", ""));
//                System.out.println(price);
//                System.out.println(offers);

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
