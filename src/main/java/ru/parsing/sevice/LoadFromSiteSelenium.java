package ru.parsing.sevice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoadFromSiteSelenium {

    static Profile profile = new Profile();
    static String home = profile.getHome();
    static String dateLoad = profile.getDateText();
    public static String pageSource= null;

    public String getStore(String page) {
        String store = page.split("/")[2];
        return store;
    }

    public static String download(String page, int ind) throws IOException {
        String store = page.split("/")[2];
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");


            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--headless", "--window-size=2560,1440", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
            options.addArguments("--log-level=3");
            driver = new ChromeDriver(options);
            Duration second = Duration.ofSeconds(15);
            Thread.sleep(15000);
            driver.manage().timeouts().pageLoadTimeout(second);
            driver.get(page);

            new Actions(driver)
                    .scrollByAmount(100, 100000)
                    .perform();

            Thread.sleep(15000);

            pageSource = driver.getPageSource();
            driver.quit();
        } catch (Exception e) {
           driver.quit();
        }

//        driver.close();


        try (PrintWriter out = new PrintWriter(home + dateLoad + "." + ind + store + ".html")) {
            out.println(pageSource);
        }
//        System.out.println(pageSource);
        return pageSource;


    }
}
