package ru.parsing.files.drywall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetrovichDrywall {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 113714477;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://petrovich.ru/catalog/113714477/101931/");

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
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("price-details").get(0).getElementsByAttributeValue("data-test", "product-gold-price").get(0).childNodes().get(0).toString();
                Double price = Double.valueOf(priceStr.replace(" ", "")) / 3.33333;

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }

        return listSource;
    }

}
