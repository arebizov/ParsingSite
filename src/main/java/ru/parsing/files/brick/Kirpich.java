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

public class Kirpich {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.kirpich.ru/shop/kirpich/kirpich_oblitsovochnyy/kirpich_oblitsovochnyy_keramicheskiy_krasnyy_evro_gladkiy_m_150_kerma/");
        pagesList.add("https://www.kirpich.ru/shop/kirpich/kirpich_oblitsovochnyy/kirpich_oblitsovochnyy_keramicheskiy_krasnyy_odinarnyy_gladkiy_m_150_vorotynskiy_kirpich/");
        pagesList.add("https://www.kirpich.ru/shop/kirpich/kirpich_stroitelnyy/kirpich_stroitelnyy_polnotelyy_odinarnyy_m_150_riflenyy_vorotynskiy/");
        pagesList.add("https://www.kirpich.ru/shop/kirpich/kirpich_keramicheskiy_odinarnyy_polnotelyy_m_150_gladkiy_bolokhovo/?from=google_merchant&utm_source=merchant&utm_term=44457&gclid=EAIaIQobChMIrtfJttyd7QIV3QCiAx2zwwQGEAQYASABEgKJdPD_BwE");

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
                Elements items = doc.getElementsByClass("single-card__main");
                for (Element item : items) {
                    String offers = item.getElementsByClass("popup-card__title").get(0).childNodes().get(0).toString().trim();
                    String priceStr = item.getElementsByAttributeValue("itemprop", "https://schema.org/price").get(0).tagName("meta").getElementsByTag("meta").attr("content").toString();
                    Double price = Double.valueOf(priceStr);
//                System.out.println(offers);
//                System.out.println(price);


                    listSource.add(new SourceData(store, offers, unit, price, date, category));
                }
            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
            }
        }
        return listSource;
    }
}


