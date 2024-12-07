package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MTSPage;
import pages.MtsHomePage;
import pages.PayFrame;

public class BaseTest {
    public static WebDriver driver;
    public static MtsHomePage mtsHomePage;
    public static PayFrame payFrame;
    public static MTSPage mtsPage;
    public static final String PAGE_URL = "https://mts.by";

    static void before() {
        driver = new ChromeDriver();
        mtsHomePage = new MtsHomePage(driver);
        mtsPage = new MTSPage(driver);
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
        try {
            mtsHomePage.clickCookieCancelBtn();
        } catch (Exception e) {
        }
    }

    static void after () {
            driver.quit();
    }
}
