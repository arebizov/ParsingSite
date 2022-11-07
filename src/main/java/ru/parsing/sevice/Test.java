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
        pagesList.add("https://neotreid.ru/bazaltovaya-teploizolyaciya/rockwool/rokvul-venti-batts");
        test.parsing(pagesList);
    }

    public static List<SourceData> listSource = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";


    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {

            try {


                String store = Profile.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));

                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                System.out.println(doc);
                Elements table = doc.getElementsByClass("tab-content").select("table").get(0).getElementsByAttributeValue("border", "1");
                Elements rows = table.select("tr");
                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    if (cols.get(1).text().length() > 1) {

                        String offers = cols.get(1).text();
                        String priceStr = cols.get(7).text().replace(" ", "").replace(",", ".");
                        Double price = Double.valueOf(priceStr);
//
                        System.out.println(offers);
                        System.out.println(price);
                    }
                }
            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
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

