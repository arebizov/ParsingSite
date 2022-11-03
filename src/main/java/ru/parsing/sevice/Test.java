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
        pagesList.add("https://rdstroy.ru/catalog/knauf_3/gipsokarton_gkl_12_5_1200_3000_knauf_list_274979/");
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


                Document doc = Jsoup.connect(url).get();
//                System.out.println(doc);
                String offers = doc.getElementsByTag("h1").text();
                System.out.println(offers);
                String priceStr = doc.getElementsByClass("ga-item-price-value").get(0).getElementsByAttributeValue("itemprop", "lowPrice").text();
                Double price = Double.valueOf(priceStr) / 3.33333;
                System.out.println(price);

            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка парсинга Test");
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

