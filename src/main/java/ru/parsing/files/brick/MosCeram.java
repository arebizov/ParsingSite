package ru.parsing.files.brick;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MosCeram {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://moskeram.ru/catalog/kirpich/oblitsovochnyy_kirpich/oblitsovochnyy-pustotelyy-m-150-146013.html");
        pagesList.add("https://moskeram.ru/catalog/kirpich/oblitsovochnyy_kirpich/oblitsovochnyy-pustotelyy-m-150-002015.html");
        pagesList.add("https://moskeram.ru/catalog/kirpich/stroitelnyy_kirpich/stroitelnyy-polnotelyy-m150-vorotinsky.html");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                store = Profile.getStore(url);
                LoadFromSiteSelenium loadFromSiteSelenium = new LoadFromSiteSelenium();

                String page = loadFromSiteSelenium.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                Elements items = doc.getElementsByClass("product-detail-page");
                for (Element item : items) {

                    String offers = item.getElementsByClass("page_title").get(0).getElementsByTag("h1").get(0).getElementsByTag("h1").html();
                    String priceStr = item.getElementsByClass("product_detail_prices").get(0).getElementsByClass("propvalue").attr("class", "price rouble").get(4).attr("itemprop", "price").text();
                    Double price = Double.valueOf(priceStr.split(" ")[0]);
//
//                    System.out.println(offers);
//                    System.out.println(price);

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


