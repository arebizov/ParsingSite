package ru.parsing.files.crushedStone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EcoCrushedStone {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1453;
    private static String unit = "М3";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://xn----btblbvwc3g5ab.xn--p1ai/ceny#price-21");
        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {
                String page = LoadFromSiteSelenium.download(url, ll.indexOf(url));
                store = Profile.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                Element table = doc.getElementsByClass("price_common price_full table-responsive").select("table").get(0);
//                System.out.println(table);
                Elements rows = table.select("tr");
                for (int i = 2; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String offers = null;
                    String priceStr = null;
                    if (cols.size() > 5 && cols.get(2).text().length()>2) {
//
                        offers = cols.get(1).text();
                        priceStr = cols.get(2).text();

                    } else {
                        offers = cols.get(0).text();
                        priceStr = cols.get(1).text();
                    }
//                    System.out.println(offers);
                    Double price = Double.valueOf(priceStr);
//                    System.out.println(price);

                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }

            } catch (IOException  e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Изменился формат данных " + url);
            }
//        } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (NumberFormatException e) {
//                throw new RuntimeException(e);
//            }



    }
        return listSourceAll;
}
}
