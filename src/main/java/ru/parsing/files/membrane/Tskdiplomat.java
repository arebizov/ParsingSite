package ru.parsing.files.membrane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tskdiplomat {
    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 224461903;
    private static String unit = "М";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://tskdiplomat.ru/catalog/izolyatsionnye_materialy/waterproofing/pvkh_membrana/gidroizolyatsionnaya_pvkh_membrana_tekhnonikol_logicbase_v_st_1_6_mm_2_05x20_m.html");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {


                LoadFromSite loadFromSite = new LoadFromSite();
                String page = LoadFromSite.download(url, ll.indexOf(url));
                store = loadFromSite.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("smallElementToolsContainer").get(0).getElementsByClass("fullPrice_number").text();//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                Double price = Double.valueOf(priceStr.replace(" ", ""));
//                System.out.println(price);
//                System.out.println(offers);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка чтения данных (time out)" + url);

            } catch ( NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Изменился формат данных " + url);
            }
        }
        return listSourceAll;
    }
}
