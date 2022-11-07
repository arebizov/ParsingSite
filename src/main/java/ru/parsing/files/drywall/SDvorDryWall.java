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

public class SDvorDryWall {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 113714477;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
   pagesList.add("https://www.sdvor.com/moscow/product/list-gipsokartonnyj-vlagostojkij-knauf-2500h1200h125-mm-5581?utm_campaign=99614117&utm_content=price_10612_gipsokarton&utm_medium=referral&utm_source=www.pulscen.ru&utm_term=5581&utm_terms=79686793_20220722132753");
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

            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSource;
    }
}
