package ru.parsing.sevice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class LoadFromSite {

    static String home = Profile.getHome();
    static String dateLoad = Profile.getDateText();

    public String getStore(String page) {
        String store = page.split("/")[2];
        return store;
    }


    public static  void copy() throws IOException {
        OutputStream target = null;

        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL("https://st-par.ru/catalog/kirpich-oblicovochnyj/2652/").openConnection();
        urlConnection.addRequestProperty("User-Agent", "Chrome");
        urlConnection.setReadTimeout(5000);
        urlConnection.setConnectTimeout(5000);
        InputStream is = urlConnection.getInputStream();
        String data = "This is a line of text inside the file.";

        try {
            // Creates a FileOutputStream
            FileOutputStream file = new FileOutputStream("d:\\download\\output.txt");

            // Creates an OutputStreamWriter
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(is.toString()));

            // Writes string to the file
            output.write(data);

            // Closes the writer
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static  String download(String page, int ind) throws IOException {

        String store = page.split("/")[2];
        String ext = ".html";

        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(page).openConnection();
        urlConnection.addRequestProperty("User-Agent", "Chrome");
        urlConnection.setReadTimeout(5000);
        urlConnection.setConnectTimeout(5000);

        String numberPage = String.valueOf(ind);

        try (InputStream is = urlConnection.getInputStream();
             BufferedWriter writer =
                     new BufferedWriter(new FileWriter(home + dateLoad + "." + numberPage+store+ext));

             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            while ((line = br.readLine()) != null) {
                result.append(line);
                writer.append(line);
            }

        }

        return result.toString();

    }
}
