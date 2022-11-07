package ru.parsing.files.duct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.LoadFromSiteSeleniumMcena;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class McenaDuct {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 94777249;
    private static String unit = "тонна";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.mcena.ru/metalloprokat/list/ocinkovannyj-gost-14918_ceny");


        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                String page = LoadFromSiteSeleniumMcena.download(url, ll.indexOf(url));
                store = Profile.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                Elements table = doc.getElementsByAttributeValue("class", "prices__body");
                Elements rows = table.select("tr");
                for (int i = 0; i < rows.size(); i++) {
                    Element row = rows.get(i);

                    String offers = "Лист оцинкованный " + rows.get(i).select("td").get(0).text();
                    Elements cols = row.select("td");
                    Double sum = 0.0;
                    int cnt = 0;
                    for (int j = 3; j < cols.size(); j++) {

                        String priceStr = cols.get(j).text().replace(" ", "").replace(" ", "");
                        if (priceStr.length() > 1) {
//                    System.out.println(j);
                            Double price = Double.valueOf(priceStr);
//                    System.out.println(price);
                            sum = sum + price;
                            cnt = cnt + 1;
                        }

                    }
                    Double avgPrice = sum / cnt;
                    listSourceAll.add(new SourceData(store, offers, unit, avgPrice, date, category));
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSourceAll;
    }

}
