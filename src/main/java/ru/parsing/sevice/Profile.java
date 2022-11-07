package ru.parsing.sevice;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Profile {

    public static String getHome() {
        String home = "d:\\download\\";
        return home;
    }

    public static String getStore(String page) {
        String store = page.split("/")[2];
        return store;
    }


    public String getExt() {
        String home = ".html";
        return home;
    }


    public static String getDateText() {
        LocalDate date = LocalDate.now();
        String dateText = date.toString();
        return dateText;
    }
    public static Date getDate() {
        LocalDate date2 = LocalDate.now();
        Date date = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());LocalDate.now();

        return date;
    }



}
