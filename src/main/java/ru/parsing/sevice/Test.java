package ru.parsing.sevice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.openqa.selenium.json.JsonOutput;
import ru.parsing.SourceData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Test {


    public static void main(String[] args) throws IOException {

        Test test = new Test();

        List<String> pagesList = new ArrayList();
        pagesList.add("https://msk.estima.ru/catalog/gabbro/gb03_60x60x10_nepol_rekt_keramicheskiy_granit/");

        http:
//www.cbr.ru/scripts/XML_daily.asp
//        pagesList.add("http://s-paritet.ru/price/");


        test.parsing(pagesList);
    }

    public static List<SourceData> listSource = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";


    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {


                LoadFromSite loadFromSite = new LoadFromSite();
                String store = loadFromSite.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));

                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
//            System.out.println(doc);
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("product-price-value").get(0).child(0).child(0).text();//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr.replace(" ", ""));
                System.out.println(offers);
                System.out.println(price);
//            System.out.println(course);
//                Elements items = doc.getElementsByClass("tab-content").select("table").get(0).getElementsByAttributeValue("border","1");
                //
//
//                String prEuroString = doc.getElementsByClass("_specs_item").get(4).getElementsByClass("_specs_val").text().split("")[1];
//                Double priceEuro = Double.valueOf(prEuroString);
//                Double price = course * priceEuro;
//                System.out.println(price);
//                System.out.println(offers);
            } catch (IOException | NumberFormatException e) {
                System.out.println("jkjgkjgjkg");
                ;
            }
//            String priceStr = doc.getElementsByAttributeValue("itemprop", "price").attr("content");
//            Double price = Double.valueOf(priceStr);
//            System.out.println(offers);
//            System.out.println(priceStr);


        }

//        }
        return null;
    }


}

