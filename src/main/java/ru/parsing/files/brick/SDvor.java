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

public class SDvor {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1557;
    private static String unit = "шт";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.sdvor.com/moscow/product/kirpich-odinarnyj-polnotelyj-rjadovoj-m150-vorotynsk-71136");
        pagesList.add("https://www.sdvor.com/moscow/product/kirpich-odinarnyj-polnotelyj-rjadovoj-m150-bolohov-74414");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                store = Profile.getStore(url);
                Date date = Profile.getDate();
                Document doc = Jsoup.connect(url).get();
                String offers = doc.getElementsByTag("h1").html().replace("&nbsp;", "");
                String priceStr = doc.getElementsByAttributeValue("aria-label", "Product price").first().html();
                Double price = Double.valueOf(priceStr.split(" ")[0]);
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
