package ru.parsing.sevice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

public class LoadFromSiteSelenium {

    static Profile profile = new Profile();
    static String home = profile.getHome();
    static String dateLoad = profile.getDateText();

    public String getStore(String page) {
        String store = page.split("/")[2];
        return store;
    }

    public static String download(String page) throws IOException {
        String store = page.split("/")[2];

        System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(page);


        WebElement footer = driver.findElement(By.tagName("footer"));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(100, deltaY)
                .perform();
        Duration fromChar1 = Duration.parse("P2DT3H4M");
        driver.manage().timeouts().implicitlyWait(fromChar1);

        String pageSource = driver.getPageSource();
        driver.close();

        List<String> splitStr = List.of(page.split("/"));
        String endString = page.split("/")[splitStr.size() - 1].replace(".php", ".html");

        try (PrintWriter out = new PrintWriter(home + dateLoad + "." + store + endString)) {
            out.println(pageSource);
        }

        return pageSource;


    }
}
