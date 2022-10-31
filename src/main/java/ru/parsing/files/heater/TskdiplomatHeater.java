package ru.parsing.files.heater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TskdiplomatHeater {
    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1285;
    private static String unit = "М3";

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        // different coeficient
        pagesList.add("https://tskdiplomat.ru/catalog/izolyatsionnye_materialy/heat_insulation/mineral_wool_on_the_basis_of_basalt_/rockwool_venti_batts_1000kh600kh50.html");
        pagesList.add("https://tskdiplomat.ru/catalog/izolyatsionnye_materialy/heat_insulation/mineral_wool_on_the_basis_of_basalt_/rockwool-venti-batts-n-optima-1000x600x110.html");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;
    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {


                LoadFromSite loadFromSite = new LoadFromSite();
                String page = LoadFromSite.download(url, ll.indexOf(url));
                String store = loadFromSite.getStore(url);
                Date date = Profile.getDate();

                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByTag("h1").html();
                Double price = null;
                String priceStr = doc.getElementsByClass("smallElementToolsContainer").get(0).getElementsByClass("fullPrice_number").text();//.get(0).getElementsByClass("price").get(0).getElementsByAttributeValue("itemprop", "price").attr("content");
                if (ll.indexOf(url) == 0) {
                    price = Double.valueOf(priceStr.replace(" ", "")) * 4.166666;
                } else {
                    price = Double.valueOf(priceStr.replace(" ", "")) * 3.0303;
                }
//                System.out.println(price);
//                System.out.println(offers);
                listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки TskdiplomatHeater");
                ;
            }
        }
        return listSourceAll;
    }
}
