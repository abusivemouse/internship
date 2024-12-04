import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class MTS {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mts.by");
        acceptCookies(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static void acceptCookies(WebDriver driver) {
        try {
            WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[contains(text(), 'Принять')]"));
            acceptCookiesButton.click();
            System.out.println("Cookies accepted.");
        } catch (Exception e) {
            System.err.println("Cookies acceptance button not found or could not be clicked.");
        }
    }

    @Test(priority = 1)
    public void checkBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//h2"));
        if (blockTitle != null) {
            // Прокрутка к элементу
            Actions actions = new Actions(driver);
            actions.moveToElement(blockTitle).perform();

            // Проверка текста элемента
            String expectedTextPart1 = "Онлайн пополнение";
            String expectedTextPart2 = "без комиссии";
            String actualText = blockTitle.getText().replace("\n", " ");

            if (actualText.contains(expectedTextPart1) && actualText.contains(expectedTextPart2)) {
                System.out.println("Block title is correct: " + actualText);
            } else {
                System.err.println("Block title is incorrect. Found: " + actualText);
            }
        } else {
            System.err.println("Block title not found.");
        }
    }

    @Test(priority = 2)
    public void checkPaymentSystemLogos() {
        List<String> expectedLogos = Arrays.asList(
                "Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"
        );
        List<WebElement> logos = driver.findElements(By.xpath("//div[contains(@class, 'pay__partners')]//img"));

        if (logos != null && logos.size() == expectedLogos.size()) {
            System.out.println("Payment system logos are present.");
            boolean allLogosCorrect = true;
            for (int i = 0; i < expectedLogos.size(); i++) {
                String expectedLogo = expectedLogos.get(i);
                WebElement logo = logos.get(i);
                String actualLogoAlt = logo.getAttribute("alt");
                if (!expectedLogo.equals(actualLogoAlt)) {
                    allLogosCorrect = false;
                    System.err.println("Logo in position " + (i + 1) + " is incorrect. Expected: " + expectedLogo + ", Found: " + actualLogoAlt);
                } else {
                    System.out.println("Logo in position " + (i + 1) + " is correct: " + actualLogoAlt);
                }
            }
            if (allLogosCorrect) {
                System.out.println("All payment system logos are in the correct order.");
            } else {
                System.err.println("Some payment system logos are not in the correct order.");
            }
        } else {
            System.err.println("Payment system logos are missing or the count is incorrect.");
        }
    }

    @Test(priority = 3)
    public void checkMoreInfoLink() {
        // Находим ссылку "Подробнее о сервисе" используя XPath
        WebElement moreInfoLink = driver.findElement(By.xpath("//div[contains(@class, 'pay__partners')]/following-sibling::a[contains(text(), 'Подробнее о сервисе')]"));

        if (moreInfoLink != null && moreInfoLink.isDisplayed()) {
            System.out.println("The 'Подробнее о сервисе' link is present.");
            // Прокрутка к элементу
            Actions actions = new Actions(driver);
            actions.moveToElement(moreInfoLink).perform();
            // Клик на ссылку
            moreInfoLink.click();

            // Ожидание перехода на новую страницу
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

            // Проверка, что переход был выполнен
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/")) {
                System.out.println("Successfully navigated to 'Подробнее о сервисе' page.");
            } else {
                System.err.println("Failed to navigate to 'Подробнее о сервисе' page. Current URL: " + currentUrl);
            }
        } else {
            System.err.println("The 'Подробнее о сервисе' link is not present or not displayed.");
        }
    }

    @Test(priority = 4)
    public void checkContinueButtonEmptyFields() {
        driver.get("https://mts.by");
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        WebElement phoneField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-phone']"));
        continueButton.click();
        // Проверка, что фокус находится на поле phoneField
        WebElement activeElement = driver.switchTo().activeElement();
        if (phoneField.equals(activeElement)) {
            System.out.println("Test Passed: Impossible continue without entering required fields");
        } else {
            System.err.println("Test Failed: Focus is not on the phone required field.");
        }

    }

    @Test(priority = 5)
    public void checkContinueButtonOnlyEmail() {
        WebElement phoneField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-phone']"));
        WebElement emailField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-email']"));
        emailField.sendKeys("test@example.com");
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        continueButton.click();

        // Проверка, что фокус находится на поле phoneField
        WebElement activeElement = driver.switchTo().activeElement();
        if (phoneField.equals(activeElement)) {
            System.out.println("Test Passed: Impossible continue without entering all required fields");
        } else {
            System.err.println("Test Failed: Focus is not on the phone field.");
        }
        // Очищаем поле ввода emailField
        emailField.clear();
    }

    @Test(priority = 6)
    public void checkContinueButtonValidData() {
        WebElement phoneField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-phone']"));
        WebElement sumField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-sum']"));

        phoneField.sendKeys("297777777");
        sumField.sendKeys("10");

        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        continueButton.click();
// Ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals("https://mts.by")) {
            System.out.println("Test Passed: Transition occurred with required filled.");
        } else {
            System.err.println("Test Failed: No transition occurred without required fields filled.");
        }

    }

    @Test(priority = 7)
    public void checkContinueButtonAllFields() {
        // Переход обратно на главную страницу
        driver.get("https://mts.by");

        WebElement service = driver.findElement(By.xpath("//span[text()='Услуги связи']"));
        // Перемещение к блоку "Услуги связи" (скроллинг)
        // Прокрутка к элементу
        Actions actions = new Actions(driver);
        actions.moveToElement(service).perform();

        WebElement phoneField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-phone']"));
        WebElement sumField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-sum']"));
        WebElement emailField = driver.findElement(By.xpath("//form[@id='pay-connection']//input[@id='connection-email']"));

        phoneField.sendKeys("297777777");
        sumField.sendKeys("10");
        emailField.sendKeys("test@test.com");

        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        continueButton.click();
// Ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals("https://mts.by")) {
            System.out.println("Test Passed: Transition occurred with all fields filled.");
        } else {
            System.err.println("Test Failed: No transition occurred with all fields filled.");
        }
    }
}

