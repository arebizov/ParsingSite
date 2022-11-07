package ru.parsing.files.pipes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Teremonline {

    public List<SourceData> listSourceAll = new ArrayList<>();

    private static int category = 7189;
    private static String unit = "М";
    public String store;

    public List<SourceData> parsData() throws IOException {

        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.teremonline.ru/catalog/inzhenernaya-santekhnika/truby/3601_iz-sshitogo-polietilena/rehau-rautitan-pink-truba-universalnaya-25kh35-mm/");
        pagesList.add(" https://www.teremonline.ru/catalog/inzhenernaya-santekhnika/truby/3601_iz-sshitogo-polietilena/rehau-rautitan-pink-truba-otopitelnaya-32kh44-mm-bukhta-50-m/");


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
                String priceStr = doc.getElementsByClass("tedprices").get(0).getElementsByAttributeValue("itemprop", "price").html();
                Double price = Double.valueOf(priceStr.replace("₽", "").replace(" ", ""));
//            System.out.println(price);
//            System.out.println(offers);
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
