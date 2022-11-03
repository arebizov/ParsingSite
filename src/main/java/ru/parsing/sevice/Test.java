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
        pagesList.add("https://a-b-z.ru/asfalt-s-dostavkoj-cena-za-tonnu/index.html");
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
                Element table = doc.select("table").get(3);
                System.out.println(table);
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");

                    String offers = cols.get(0).text();
                    String priceStr = cols.get(1).text();
                    Double price = Double.valueOf(priceStr);
                }

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

