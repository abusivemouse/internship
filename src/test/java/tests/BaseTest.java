package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.apache.commons.io.FileUtils;
import pages.MTSPage;
import pages.MtsHomePage;
import pages.PayFrame;

import java.io.File;
import java.io.IOException;
import java.util.List;


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

    static void after() {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logEntries = browserLogs.getAll();
        if (logEntries.size() > 0) {
            driver.quit();
        }
    }
    public static class ScreeenshotMaker {
        public static void makeScreenshot(WebDriver driver, String fileName) {
            File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("./target" + fileName);
            try {
                FileUtils.copyFile(temp, destination);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}

