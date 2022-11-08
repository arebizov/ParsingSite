package ru.parsing.sevice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.parsing.SourceData;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Document doc = Jsoup.parse(download());
//        System.out.println(doc);
//        System.out.println(doc);
        Elements table = doc.getElementsByAttributeValue("class", "prices__body");
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);

            String offers = "Трубы оцинкованные " + rows.get(i).select("td").get(0).text();

            Elements cols = row.select("td");
            Double sum = 0.0;
            int cnt = 0;
            for (int j = 3; j < cols.size(); j++) {

                String priceStr = cols.get(j).text().replace(" ", "").replace(" ", "");
                int w = priceStr.length();
                if (priceStr.length() > 1) {
//                    System.out.println(j);
                    Double price = Double.valueOf(priceStr);
//                    System.out.println(price);
                    sum = sum + price;
                    cnt = cnt + 1;
                }

            }
            Double avgPrice = sum / cnt;
            System.out.println(offers);
            System.out.println(avgPrice);


        }
    }


    public static String download() throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--headless", "--window-size=2560,1440", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        options.addArguments("--log-level=3");
        WebDriver driver = new ChromeDriver();

        Duration seconds = Duration.ofSeconds(15);
//        WebDriverWait wait = new WebDriverWait(driver,fromChar1);
//        wait.until(ExpectedConditions.visibilityOfAllElements());
        driver.manage().timeouts().implicitlyWait(seconds);
        driver.manage().timeouts().pageLoadTimeout(seconds);
        driver.get("https://www.mcena.ru/metalloprokat/polosa/polosa-gost-103_ceny");
        Thread.sleep(3000);
//        new Actions(driver)
//                .scrollByAmount(100, 100000)
//                .perform();
//        Thread.sleep(5000);
        int elemements = driver.findElements(By.className("price-table__button")).size();
        System.out.println(elemements);
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



        String pageSource = driver.getPageSource();
//        System.out.println(pageSource);
        driver.quit();
        return pageSource;
    }

}
