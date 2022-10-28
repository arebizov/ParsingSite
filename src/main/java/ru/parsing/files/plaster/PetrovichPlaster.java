package ru.parsing.files.plaster;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetrovichPlaster {
    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1447;
    private static String unit = "кг";

    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://moscow.petrovich.ru/catalog/1447/106958/");

        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            try {

                LoadFromSite loadFromSite = new LoadFromSite();
                String store = loadFromSite.getStore(url);
                String page = LoadFromSite.download(url, ll.indexOf(url));
                Date date = Profile.getDate();
                Document doc = Jsoup.parse(page);
                String offers = doc.getElementsByTag("h1").html();
                String priceStr = doc.getElementsByClass("price-details").get(0).getElementsByAttributeValue("data-test", "product-gold-price").get(0).childNodes().get(0).toString();
                Double price = Double.valueOf(priceStr.replace(" ", ""));
                System.out.println(priceStr);

                listSource.add(new SourceData(store, offers, unit, price, date, category));
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка обработки PetrovichPlaster");
            }
        }

        return listSource;
    }

}
