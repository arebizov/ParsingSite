package ru.parsing.files.cable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CableRu {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://cable.ru/cable/marka-vvgng_ls_3x2_5_0_66.php");
        pagesList.add("https://cable.ru/cable/marka-vvgng_ls_3x1_5_0_66.php");
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
                Element pr = doc.getElementsByClass("product-config__new").first();
                String priceStr = pr.getElementsByAttributeValue("name", "item_price").val().replace(",", ".");
                if (priceStr.trim().length() > 0) {
                    Double price = Double.valueOf(priceStr);
                    String offers = pr.getElementsByAttributeValue("name", "item_name").val();
                    listSourceAll.add(new SourceData(store, offers, unit, price, date, category));
                }
            } catch (IOException | NumberFormatException |NullPointerException| IndexOutOfBoundsException e) {
                System.out.println("ошибка обработки " + store + " " + category+" "+ url);
            }
        }

        return listSourceAll;

    }

}
