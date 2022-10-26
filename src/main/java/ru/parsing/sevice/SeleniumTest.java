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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    public static void main(String[] args) throws IOException {

//        Document doc = Jsoup.parse(download());
//        System.out.println(doc);
        Document doc = Jsoup.connect("https://www.sdvor.com/moscow/product/kirpich-odinarnyj-polnotelyj-rjadovoj-m150-vorotynsk-71136").get();
        String offers = doc.getElementsByTag("h1").html().replace("&nbsp;","");
        System.out.println(offers);
        String priceStr = doc.getElementsByAttributeValue("aria-label", "Product price").first().html();
        Double price = Double.valueOf(priceStr.split(" ")[0]);
        System.out.println(price);
//        Element pr = doc.getElementsByClass("price-value").first();
//        System.out.println("________________________");
//        System.out.println(pr);
//        System.out.println(pr.childNode(0));
//        Elements hTags = doc.select("h1");
//        Elements h1Tags = hTags.select("h1");
//        String offers = h1Tags.html();
//        System.out.println(offers);




    }

    public static String download(){

        System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--headless", "--window-size=2560,1440","--ignore-certificate-errors","--disable-extensions","--disable-dev-shm-usage");
        options.addArguments("--log-level=3");

        driver.get("https://www.sdvor.com/moscow/product/kirpich-odinarnyj-polnotelyj-rjadovoj-m150-vorotynsk-71136");

        WebElement footer = driver.findElement(By.tagName("footer"));
        Duration fromChar1 = Duration.parse("P2DT3H4M");
        driver.manage().timeouts().implicitlyWait(fromChar1);
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(100, deltaY)
                .perform();
        driver.manage().timeouts().implicitlyWait(fromChar1);

        String pageSource = driver.getPageSource();
        driver.close();
        return pageSource;
    }

}
