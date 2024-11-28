import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.time.Duration;

public class MTSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver"); // Замените путь к вашему chromedriver
        driver = new ChromeDriver();
        int time = 10;
        Duration duration = Duration.ofSeconds(time);
        wait = new WebDriverWait(driver, duration);
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkBlockTitle() {
        String expectedTitle = "Онлайн пополнение без комиссии";
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        Assertions.assertEquals(expectedTitle, blockTitle.getText());
    }

    @Test
    public void checkPaymentLogos() {
        List<WebElement> paymentLogos = driver.findElements(By.cssSelector(".payment-logos img"));
        Assertions.assertFalse(paymentLogos.isEmpty());
        for (WebElement logo : paymentLogos) {
            Assertions.assertNotNull(logo.getAttribute("src"));
        }
    }

    @Test
    public void checkServiceLink() {
        WebElement serviceLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        Assertions.assertNotNull(serviceLink);
        serviceLink.click();
        wait.until(ExpectedConditions.urlContains("/service-details"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/service-details"));
    }

    @Test
    public void fillFormAndCheckContinueButton() {
        WebElement form = driver.findElement(By.id("services-form"));
        WebElement phoneField = form.findElement(By.name("phoneNumber"));
        phoneField.sendKeys("297777777");

        Select servicesDropdown = new Select(form.findElement(By.name("services")));
        servicesDropdown.selectByVisibleText("Услуги связи");

        WebElement continueButton = form.findElement(By.cssSelector("[type='submit']"));
        continueButton.click();

        wait.until(ExpectedConditions.urlContains("/payment-confirmation"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/payment-confirmation"));
    }
}