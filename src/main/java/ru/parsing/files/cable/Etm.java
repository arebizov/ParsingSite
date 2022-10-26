package ru.parsing.files.cable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parsing.SourceData;
import ru.parsing.sevice.LoadFromSite;
import ru.parsing.sevice.LoadFromSiteSelenium;
import ru.parsing.sevice.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etm {

    public List<SourceData> listSource = new ArrayList<>();

    private static int category = 1389;
    private static String unit = "М";
    public List<SourceData> parsData() throws IOException {


        List<String> pagesList = new ArrayList();
        pagesList.add("https://www.etm.ru/cat/nn/2126656");
        pagesList.add("https://www.etm.ru/cat/nn/452373");


        List<SourceData> listSource = parsing(pagesList);
        return listSource;

    }

    public List<SourceData> parsing(List<String> ll) throws IOException {
        for (String url : ll) {
            LoadFromSite loadFromSite = new LoadFromSite();
            String store = loadFromSite.getStore(url);
            String page = LoadFromSite.download(url, ll.indexOf(url));
            Date date = Profile.getDate();
            Document doc = Jsoup.parse(page);
            Element pr = doc.getElementsByClass("jss195").get(0);
            Double price = Double.valueOf(String.valueOf(pr.childNode(0)));
            Elements hTags = doc.select("h1");
            Elements h1Tags = hTags.select("h1");
            String offers = h1Tags.html();
//        System.out.println(offers);
//        System.out.println(price);
            listSource.add(new SourceData(store, offers, unit, price, date));
        }
        return listSource;
    }

}