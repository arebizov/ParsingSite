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
        pagesList.add("https://tskdiplomat.ru/catalog/izolyatsionnye_materialy/waterproofing/pvkh_membrana/gidroizolyatsionnaya_pvkh_membrana_tekhnonikol_logicbase_v_st_1_6_mm_2_05x20_m.html");

//        pagesList.add("https://www.etm.ru/cat/nn/452373");
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
            String priceStr  = doc.getElementsByClass("smallElementToolsContainer").get(0).getElementsByClass("fullPrice_number").text();//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
            Double price = Double.valueOf(priceStr.replace(" ",""));
            System.out.println(price);
              System.out.println(offers);
//            Elements items = doc.getElementsByClass("col-lg-6 pl-md-0");
//            String offers = items.get(0).getElementsByAttributeValue("itemprop", "name").html();
//            String priceStr = items.get(0).getElementsByAttributeValue("class", "current_price").html();
//            Double price = Double.valueOf(priceStr);
//            System.out.println(items);
//            System.out.println(priceStr);
//            String item = doc.getElementsByTag("h1").html();
//            String priceStr = doc.getElementsByClass("product-prices clearfix").get(0).getElementsByAttributeValue("itemprop","price").attr("content");
//            Double price = Double.valueOf(priceStr);
//            System.out.println(item);
//            System.out.println(price);
//
//            String pr =  doc.getElementsByAttributeValueContaining("title","Цена с учетом акций и скидок от розничной цены").get(0).parentNode().parentNode().childNodes().get(1).childNodesCopy().get(0).toString();
////            Double price = Double.valueOf(String.valueOf(pr.childNode(0)));
//
//            Elements pr2 =  doc.select("p:contains(<!-- -->₽)");
////
//            Elements hTags = doc.select("h1");
//            Elements h1Tags = hTags.select("h1");
//            String offers = h1Tags.html();
//            System.out.println(pr2);

//            for (Element item : items) {
//                String pricetr = item.getElementsByClass("pay").get(0).getElementsByClass("price").get(0).childNode(0).toString();
//                Double price = Double.valueOf(pricetr.split(" ")[0]);
//                System.out.println(price);
//                String offers = doc.select("h1").html();
//                System.out.println(offers);
//            }
////                System.out.println(item.text());
//
//                Elements offers = item.getElementsByTag("h1");
////                Elements priceStr = item.getElementsByAttributeValue("class","price-value");
////                Double price = Double.valueOf(priceStr.split(" ")[0]);
//
////                System.out.println(offers);
////                System.out.println(priceStr);
//
//
//          }


        }

//        }
        return null;
    }


}

