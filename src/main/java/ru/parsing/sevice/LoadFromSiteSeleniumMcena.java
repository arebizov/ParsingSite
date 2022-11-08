package ru.parsing.sevice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

public class LoadFromSiteSeleniumMcena {

    static Profile profile = new Profile();
    static String home = profile.getHome();
    static String dateLoad = profile.getDateText();
    public static String pageSource = null;

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
            int elemements = driver.findElements(By.className("price-table__button")).size();
            if (elemements>0){

                //driver.findElement(By.className("prices-more")).getAccessibleName().equals("СМОТРЕТЬ ЕЩЕ") == true
                while (!driver.findElement(By.className("prices-more")).getAttribute("style").equals("display: none;")) {
//            System.out.println(driver.findElement(By.className("prices-more")).toString());
                    driver.findElement(By.className("price-table__button")).click();
                    Thread.sleep(3000);}
            }
            else {
                Thread.sleep(3000);
            }
            pageSource = driver.getPageSource();
            driver.quit();
        } catch (Exception e) {
            driver.quit();
        }

        try (PrintWriter out = new PrintWriter(home + dateLoad + "." + ind + store + ".html")) {
            out.println(pageSource);
        }
//        System.out.println(pageSource);
        return pageSource;


    }
}
