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
        pagesList.add("https://shop.tn.ru/tehnojelast-jepp-10h1-m");

        test.parsing(pagesList);
    }

    public static List<SourceData> listSource = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";


    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            LoadFromSite loadFromSite = new LoadFromSite();
            String store = loadFromSite.getStore(url);
            String page = LoadFromSite.download(url, ll.indexOf(url));

            Date date = Profile.getDate();

            Document doc = Jsoup.parse(page);
//            System.out.println(doc);
            String offers = doc.getElementsByTag("h1").html();
            String priceStr = doc.getElementsByAttributeValue("class", "js-first-unit-price").text();//.get(0).childNodes().get(0).childNode(0).toString();
            Double price = Double.valueOf(priceStr.replace(" ", ""));
            System.out.println(offers);
            System.out.println(priceStr);
//            Elements items = doc.getElementsByClass("col-lg-6 pl-md-0");
//            String offers = items.get(0).getElementsByAttributeValue("itemprop", "name").html();
//            String priceStr = items.get(0).getElementsByAttributeValue("class", "current_price").html();
//            Double price = Double.valueOf(priceStr);


        }

//        }
        return null;
    }


}

