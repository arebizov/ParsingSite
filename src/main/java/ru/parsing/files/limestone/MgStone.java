package ru.parsing.files.limestone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MgStone {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 46554423;
    private static String unit = "m2";
    public String store;

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://mg-stone.ru/catalog/izvestnyak/saint-raphael-dore/");


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
                Document doc = Jsoup.connect(url).get();

                Document docCourse = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();
                String courseStr = docCourse.getElementsByAttributeValue("id", "R01239").get(0).childNode(4).childNode(0).toString().replace(",", ".").trim();
                Double course = Double.valueOf(courseStr);
//            System.out.println(course);
                String offers = doc.getElementsByTag("h1").get(0).getElementsByAttributeValue("class", "uzor_zag").text();
                String prEuroString = doc.getElementsByClass("_specs_item").get(4).getElementsByClass("_specs_val").text().split(" ")[1];
                Double priceEuro = Double.valueOf(prEuroString);
                Double price = course * priceEuro;
//                System.out.println(price);
//                System.out.println(offers);

                listSource.add(new SourceData(store, offers, unit, price, date, category));

            } catch (IOException | NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category);
                ;
            }
        }
        return listSource;
    }
}
