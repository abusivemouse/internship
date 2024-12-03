package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//button[contains(text(), 'Принять')]")
    WebElement acceptCookiesButton;

    @FindBy(xpath = "//form[@id='pay-connection']//button[@type='submit']")
    WebElement continueButton;

    @FindBy(xpath = "//form[@id='pay-connection']//input[@id='connection-phone']")
    WebElement phoneField;

    @FindBy(xpath = "//form[@id='pay-connection']//input[@id='connection-email']")
    WebElement emailField;

    @FindBy(xpath = "//form[@id='pay-connection']//input[@id='connection-sum']")
    WebElement sumField;

    // Планы оплаты
    @FindBy(xpath = "//button/span[@class='select__now'][text()='Услуги связи']")
    WebElement selectedServicesPlan;

    @FindBy(xpath = "//input[@id='connection-phone'][@placeholder='Номер телефона']")
    WebElement numberPhonePlaceholderServices;

    @FindBy(xpath = "//input[@id='connection-sum'][@placeholder='Сумма']")
    WebElement summaPlaceholderServices;

    @FindBy(xpath = "//input[@id='connection-email'][@placeholder='E-mail для отправки чека']")
    WebElement emailPlaceholderServices;

    @FindBy(xpath = "//button/span[@class='select__now'][text()='Домашний интернет']")
    WebElement selectedInternetHomePlan;

    @FindBy(xpath = "//input[@id='internet-phone'][@placeholder='Номер абонента']")
    WebElement numberInternetHomePlaceholder;

    @FindBy(xpath = "//input[@id='internet-sum'][@placeholder='Сумма']")
    WebElement summaInternetHomePlaceholder;

    @FindBy(xpath = "//input[@id='internet-email'][@placeholder='E-mail для отправки чека']")
    WebElement emailInternetHomePlaceholder;

    @FindBy(xpath = "//button/span[@class='select__now'][text()='Рассрочка']")
    WebElement selectedPaymentPlan;

    @FindBy(xpath = "//input[@id='score-instalment'][@placeholder='Номер счета на 44']")
    WebElement numberAccountPlaceholder;

    @FindBy(xpath = "//input[@id='instalment-sum'][@placeholder='Сумма']")
    WebElement summaPlaceholder;

    @FindBy(xpath = "//input[@id='instalment-email'][@placeholder='E-mail для отправки чека']")
    WebElement emailPlaceholder;

    @FindBy(xpath = "//button/span[@class='select__now'][text()='Задолженность']")
    WebElement selectedArrearsPlan;

    @FindBy(xpath = "//input[@id='score-arrears'][@placeholder='Номер счета на 2073']")
    WebElement numberAccountPlaceholderArrears;

    @FindBy(xpath = "//input[@id='arrears-sum'][@placeholder='Сумма']")
    WebElement summaArrearsPlaceholder;

    @FindBy(xpath = "//input[@id='arrears-email'][@placeholder='E-mail для отправки чека']")
    WebElement emailArrearsPlaceholder;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            acceptCookiesButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickContinueButton() {
        scrollToElement(continueButton);
        continueButton.click();
    }

    public void enterPhoneNumber(String phone) {
        phoneField.sendKeys(phone);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterSum(String sum) {
        sumField.sendKeys(sum);
    }

    private void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //-- Услуги связи --
    public boolean isServicesPlanSelectedDisplayed() {
        return selectedServicesPlan.isDisplayed();
    }

    public boolean isNumberPhonePlaceholderServicesDisplayed() {
        return numberPhonePlaceholderServices.isDisplayed();
    }

    public boolean isNumberInternetHomePlaceholderDisplayed() {
        return numberInternetHomePlaceholder.isDisplayed();
    }

    public boolean isSummaInternetHomePlaceholderDisplayed() {
        return summaInternetHomePlaceholder.isDisplayed();
    }

    public boolean isEmailInternetHomePlaceholderDisplayed() {
        return emailInternetHomePlaceholder.isDisplayed();
    }

    // -- Рассрочка --
    public boolean isNumberAccountPlaceholderDisplayed() {
        return numberAccountPlaceholder.isDisplayed();
    }

    public boolean isSummaPlaceholderDisplayed() {
        return summaPlaceholder.isDisplayed();
    }

    public boolean isEmailPlaceholderDisplayed() {
        return emailPlaceholder.isDisplayed();
    }

    //-- Задолженность --
    public boolean isNumberAccountArrearsPlaceholderDisplayed() {
        return numberAccountPlaceholderArrears.isDisplayed();
    }

    public boolean isSummaArrearsPlaceholderDisplayed() {
        return summaArrearsPlaceholder.isDisplayed();
    }

    public boolean isEmailArrearsPlaceholderDisplayed() {
        return emailArrearsPlaceholder.isDisplayed();
    }


}