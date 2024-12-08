package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MtsHomePage {
    public WebDriver driver;

    public MtsHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Определение локатора для кнопки отмены сохранения куков
     */
    @FindBy(css = "div.cookie.show button.cookie__cancel")
    private WebElement cookieCancelBtn;

    /**
     * Определение локатора для поля "Услуга связи -> Номер телефона"
     */
    @FindBy(css = "section.pay input#connection-phone")
    private WebElement connectionPhone;

    /**
     * Определение локатора для поля "Услуга связи -> Сумма"
     */
    @FindBy(css = "section.pay input#connection-sum")
    private WebElement connectionSum;

    /**
     * Определение локатора для поля "Услуга связи -> E-mail""
     */
    @FindBy(css = "section.pay input#connection-email")
    private WebElement connectionEmail;

    /**
     * Определение локатора для поля "Домашний интернет -> Номер телефона"
     */
    @FindBy(css = "section.pay input#internet-phone")
    private WebElement internetPhone;

    /**
     * Определение локатора для поля "Домашний интернет -> Сумма"
     */
    @FindBy(css = "section.pay input#internet-sum")
    private WebElement internetSum;

    /**
     * Определение локатора для поля "Домашний интернет -> E-mail"
     */
    @FindBy(css = "section.pay input#internet-email")
    private WebElement internetEmail;

    /**
     * Определение локатора для поля "Рассрочка -> Номер счета"
     */
    @FindBy(css = "section.pay input#score-instalment")
    private WebElement instalmentScore;

    /**
     * Определение локатора для  поля "Рассрочка -> Сумма"
     */
    @FindBy(css = "section.pay input#instalment-sum")
    private WebElement instalmentSum;

    /**
     * Определение локатора для поля "Рассрочка -> E-mail"
     */
    @FindBy(css = "section.pay input#instalment-email")
    private WebElement instalmentEmail;

    /**
     * Определение локатора для поля "Задолженность -> Номер счета"
     */
    @FindBy(css = "section.pay input#score-arrears")
    private WebElement arrearsScore;

    /**
     * Определение локатора для поля "Задолженность -> Сумма"
     */
    @FindBy(css = "section.pay input#arrears-sum")
    private WebElement arrearsSum;

    /**
     * Определение локатора для поля "Задолженность -> E-mail"
     */
    @FindBy(css = "section.pay input#arrears-email")
    private WebElement arrearsEmail;

    /**
     * Определение локатора для кнопки пополнить
     */
    @FindBy(css = "section.pay button[type='submit']")
    private WebElement payBtn;

    /**
     * Определение локатора для окна ввода реквизитов пополнения
     */
    @FindBy(css = "iframe.bepaid-iframe")
    public WebElement payFrame;

    /**
     * Метод нажатия на кнопку отмены сохранения куков
     */
    public void clickCookieCancelBtn() {
        cookieCancelBtn.click();
    }

    /**
     * Метод возращает текст плейсхолдера поля "Услуга связи -> Номер телефона"
     *
     * @return текст плейсхолдера
     */
    public String getConnectionPhonePlaceholder() throws NoSuchElementException {
        return connectionPhone.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Услуга связи -> Сумма"
     *
     * @return текст плейсхолдера
     */
    public String getConnectionSumPlaceholder() throws NoSuchElementException {
        return connectionSum.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Услуга связи -> E-mail"
     *
     * @return текст плейсхолдера
     */
    public String getConnectionEmailPlaceholder() throws NoSuchElementException {
        return connectionEmail.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Домашний интернет -> Номер телефона"
     *
     * @return текст плейсхолдера
     */
    public String getInternetPhonePlaceholder() throws NoSuchElementException {
        return internetPhone.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Домашний интернет -> Cумма"
     *
     * @return текст плейсхолдера
     */
    public String getInternetSumPlaceholder() throws NoSuchElementException {
        return internetSum.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Домашний интернет -> E-mail"
     *
     * @return текст плейсхолдера
     */
    public String getInternetEmailPlaceholder() throws NoSuchElementException {
        return internetEmail.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Рассрочка -> Номер счета"
     *
     * @return текст плейсхолдера
     */
    public String getInstalmentScorePlaceholder() throws NoSuchElementException {
        return instalmentScore.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Рассрочка -> Cумма"
     *
     * @return текст плейсхолдера
     */
    public String getInstalmentSumPlaceholder() throws NoSuchElementException {
        return instalmentSum.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Рассрочка -> E-mail"
     *
     * @return текст плейсхолдера
     */
    public String getInstalmentEmailPlaceholder() throws NoSuchElementException {
        return instalmentEmail.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Задолженность -> Номер счета"
     *
     * @return текст плейсхолдера
     */
    public String getArrearsScorePlaceholder() throws NoSuchElementException {
        return arrearsScore.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Задолженность -> Сумма"
     *
     * @return текст плейсхолдера
     */
    public String getArrearsSumPlaceholder() throws NoSuchElementException {
        return arrearsSum.getAttribute("placeholder");
    }

    /**
     * Метод возращает текст плейсхолдера поля "Задолженность -> E-mail"
     *
     * @return текст плейсхолдера
     */
    public String getArrearsEmailPlaceholder() throws NoSuchElementException {
        return arrearsEmail.getAttribute("placeholder");
    }

    /**
     * Метод заполняет поле "Услуга связи -> Номер телефона"
     *
     * @param phoneNumber номер телефона
     * @throws NoSuchElementException поле не найдено
     */
    public void setConnectionPhone(String phoneNumber) throws NoSuchElementException {
        connectionPhone.sendKeys(phoneNumber);
    }

    /**
     * Метод заполняет поле "Услуга связи -> Сумма"
     *
     * @param sum сумма пополнения
     * @throws NoSuchElementException поле не найдено
     */
    public void setConnectionSum(String sum) throws NoSuchElementException {
        connectionSum.sendKeys(sum);
    }

    /**
     * Метод нажимает на кнопку оплаты
     *
     * @throws NoSuchElementException кнопка не найдена
     */
    public void clickPayBtn() throws NoSuchElementException {
        payBtn.click();
    }
}
