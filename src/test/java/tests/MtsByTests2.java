package tests;

import pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class MtsByTests2 {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait wait;
    private WebDriverWait wait_standart;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait_standart = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://mts.by");
        waitForPageLoad(driver);
        homePage = new HomePage(driver);
        homePage.acceptCookies();

    }

    private void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void getPaymentFrame() {
        homePage.enterPhoneNumber("297777777");
        homePage.enterSum("10");
        homePage.enterEmail("test@test.com");
        homePage.clickContinueButton();

        // Ожидание загрузки фрейма
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@style='visibility: visible;'][1]")));
        //  System.out.println("Перешли на фрейм оплаты");
    }

    @Test(priority = 2)
    public void checkSumma() {
        WebElement summa = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay-description__cost']/span[text()='10.00 BYN']")));
        Assert.assertTrue(summa.isDisplayed(), "Сумма оплаты не соответствует введенной");
    }

    @Test(priority = 3)
    public void checkSummaButton() {
        // 2. Проверка, что сумма на кнопке присутсвует и что сумма равна сумме введенной на предыдущей форме)
        WebElement summaButton = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='colored disabled'][contains(text(), 'Оплатить  10.00 BYN')]")));
        Assert.assertTrue(summaButton.isDisplayed(), "Сумма на кнопке не соответствует введенной");
    }

    @Test(priority = 4)
    public void checkPhoneNumber() {
        // 3. Проверка, что номер телефона на форме оплаты присутсвует и он равен ("297777777")
        WebElement phoneNumber = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay-description__text']/span[contains(text(), 'Номер:375297777777')]")));
        Assert.assertTrue(phoneNumber.isDisplayed(), "Номер телефона не соответствует");
    }

    @Test(priority = 5)
    public void checkCardNumberPlaceholder() {
        // 4. Проверка, что плейсхолдер "Номер Карточки" присутствует в поле для ввода кредитной карты
        WebElement cardNumberPlaceholder = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted'][text()='Номер карты']")));
        Assert.assertTrue(cardNumberPlaceholder.isDisplayed(), "Плейсхолдер 'Номер Карты' не отображается");
    }

    @Test(priority = 6)
    public void checkCardExpirationPlaceholder() {
        // 5. Проверка, что плейсхолдер "Срок Действия" присутствует в поле для ввода срока кредитной карты
        WebElement cardExpirationPlaceholder = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted'][text()='Срок действия']")));
        Assert.assertTrue(cardExpirationPlaceholder.isDisplayed(), "Плейсхолдер 'Срок Действия' не отображается");
    }

    @Test(priority = 7)
    public void checkCVCPlaceholder() {
        // 6. Проверка, что плейсхолдер "CVC" присутствует в поле для ввода
        WebElement cvcPlaceholder = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted'][text()='CVC']")));
        Assert.assertTrue(cvcPlaceholder.isDisplayed(), "Плейсхолдер 'CVC' не отображается");
    }

    @Test(priority = 8)
    public void checkCardholderNamePlaceholder() {
        // 7. Проверка, что плейсхолдер "Имя как на карте" присутствует в поле для ввода имени держателя карты
        WebElement cardholderNamePlaceholder = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted'][text()='Имя держателя (как на карте)']")));
        Assert.assertTrue(cardholderNamePlaceholder.isDisplayed(), "Плейсхолдер 'Имя держателя (как на карте)' не отображается");
    }

    @Test(priority = 9)
    public void checkMastercardIcon() {
        // 8. Проверка, что иконка "МастрКард" присутствует на форме оплаты
        WebElement mastercardIcon = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted'][@src='assets/images/payment-icons/card-types/mastercard-system.svg']")));
        Assert.assertTrue(mastercardIcon.isDisplayed(), "Иконка 'Mastercard' не отображается");
    }

    @Test(priority = 10)
    public void checkVisaIcon() {
        // 9. Проверка, что иконка "Visa" присутствует на форме оплаты
        WebElement visaIcon = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted'][@src='assets/images/payment-icons/card-types/visa-system.svg']")));
        Assert.assertTrue(visaIcon.isDisplayed(), "Иконка 'Visa' не отображается");
    }

    @Test(priority = 11)
    public void checkBelCardIcon() {
        // 10. Проверка, что иконка "BelCard" присутствует на форме оплаты
        WebElement belCardIcon = wait_standart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='ng-tns-c61-0 ng-star-inserted'][@src='assets/images/payment-icons/card-types/belkart-system.svg']")));
        Assert.assertTrue(belCardIcon.isDisplayed(), "Иконка 'BelCard' не отображается");
    }

    @Test(priority = 12)
    public void checkMirOrMaestroIcon() {
        // 11. Проверка, что иконка "MIR" или "Маестро" присутствует на форме оплаты
        try {
            // Ожидаем появления иконки "MIR"
            WebElement mirIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[@class='ng-tns-c61-0 ng-trigger ng-trigger-randomCardState ng-star-inserted'][@src='assets/images/payment-icons/card-types/mir-system.svg']")
            ));

            if (mirIcon.isDisplayed()) {
                System.out.println("Иконка 'MIR' отображена");
                return; // Если иконка MIR найдена, то выходим из метода
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            // Если иконки MIR нет, ничего не делаем и продолжаем дальше
        }

        try {
            // Ожидаем появления иконки "Маестро"
            WebElement maestroIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//img[@src='assets/images/payment-icons/card-types/maestro-system.svg']")
            ));

            if (maestroIcon.isDisplayed()) {
                System.out.println("Иконка 'Маестро' отображена");
                return; // Если иконка Маестро найдена, то выходим из метода
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            // Если иконки Маестро нет, ничего не делаем и продолжаем дальше
        }

        // Если ни одна из иконок не найдена, тогда сообщаем об ошибке
        Assert.fail("Ни иконка 'MIR', ни иконка 'Маестро' не отображаются");
    }
}
