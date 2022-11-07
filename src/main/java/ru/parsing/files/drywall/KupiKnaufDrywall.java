package ru.parsing.files.drywall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KupiKnaufDrywall {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 113714477;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://msk.kupi.knauf.ru/catalog/listovye-materialy/gipsokarton-gkl/knauf-list-vlagostoykiy-1200x2500x12-5mm/");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                store = Profile.getStore(url);
                String page = LoadFromSiteSelenium.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByClass("product").get(0).getElementsByTag("h1").html().replace("&nbsp;", " ");
                String priceStr = doc.getElementsByClass("product").get(0).getElementsByAttributeValue("itemprop", "lowPrice").get(0).attr("content");
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
