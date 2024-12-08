package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PayFrame {

    public WebDriver driver;

    public PayFrame(WebDriver driver, WebElement frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        // Ждем полной загрузки окна по одному из элементов формы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=creditCard] + label")));
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Определение локатора для пополняемой суммы в описаниии оплаты
     */
    @FindBy(css = "div.pay-description__cost span:nth-child(1)")
    private WebElement payFrameDescriptionCost;

    /**
     * Определение локатора для кнопки оплаты
     */
    @FindBy(css = "div.card-page__card button")
    private WebElement payFrameBtn;

    /**
     * Определение локатора для номера телефона в описании оплаты
     */
    @FindBy(css = "div.pay-description__text span:nth-child(1)")
    private WebElement payFrameDescriptionPhone;

    /**
     * Определение локаторов для картинок платежных систем в форме ввода номера карты
     */
    @FindBy(css = "div.cards-brands img")
    private List<WebElement> payImgages;

    /**
     * Определение локатора для плейсхолдера поля ввода номера карты
     */
    @FindBy(css = "input[formcontrolname=creditCard] + label")
    private WebElement creditCardPlaceholder;

    /**
     * Определение локатора для плейсхолдера поля ввода даты истечения карты
     */
    @FindBy(css = "input[formcontrolname=expirationDate] + label")
    private WebElement expirationDatePlaceholder;

    /**
     * Определение локатора для плейсхолдера поля ввода CVC
     */
    @FindBy(css = "input[formcontrolname=cvc] + label")
    private WebElement cvcPlaceholder;

    /**
     * Определение локатора плейсхолдера для поля ввода имени держателя карты
     */
    @FindBy(css = "input[formcontrolname=holder] + label")
    private WebElement cardHolderPlaceholder;

    /**
     * Возвращает текст пополняемой суммы в описаниии оплаты
     *
     * @return текст пополняемой суммы
     * @throws NoSuchElementException объект не найден
     */
    public String getPayFrameDescriptionCost() throws NoSuchElementException {
        return payFrameDescriptionCost.getText();
    }

    /**
     * Возвращает текст кнопки оплаты
     *
     * @return текст кнопки оплаты
     * @throws NoSuchElementException объект не найден
     */
    public String getPayFrameBtnText() throws NoSuchElementException {
        return payFrameBtn.getText();
    }

    /**
     * Возвращает текст номера телефона в описании оплаты
     *
     * @return текст номера телефона в описании оплаты
     * @throws NoSuchElementException объект не найден
     */
    public String getPayFrameDescriptionPhone() throws NoSuchElementException {
        return payFrameDescriptionPhone.getText();
    }

    /**
     * Возвращает отображается ли картинка с именем src
     * @param scr имя картинки
     * @return отображатся ли картинк
     * @throws NoSuchElementException объект не найден
     */
    public boolean isDisplayedImg(String scr) throws NoSuchElementException {
        for (WebElement payImage : payImgages) {
            if (payImage.getAttribute("src").contains(scr)) {
                return payImage.isDisplayed() || payImage.getAttribute("style").contains("opacity: 0") ;
            }
        }
        return false;
    }
    /**
     * Возвращает текст плейсхолдера поля ввода номера карты
     *
     * @return текст плейсхолдера поля ввода номера карты
     * @throws NoSuchElementException объект не найден
     */
    public String getCreditCardPlaceholder() throws NoSuchElementException {
        return creditCardPlaceholder.getText();
    }

    /**
     * Возвращает текст плейсхолдера поля ввода даты истечения карты
     *
     * @return текст плейсхолдера поля ввода даты истечения карты
     * @throws NoSuchElementException объект не найден
     */
    public String getExpirationDatePlaceholder() throws NoSuchElementException {
        return expirationDatePlaceholder.getText();
    }

    /**
     * Возвращает текст плейсхолдера поля ввода CVC
     *
     * @return текст плейсхолдера поля ввода CVC
     * @throws NoSuchElementException объект не найден
     */
    public String getCvcPlaceholder() throws NoSuchElementException {
        return cvcPlaceholder.getText();
    }

    /**
     * Возвращает текст плейсхолдера поля ввода имени держателя карты
     *
     * @return текст плейсхолдера поля ввода имени держателя карты
     * @throws NoSuchElementException объект не найден
     */
    public String getCardHolderPlaceholder() throws NoSuchElementException {
        return cardHolderPlaceholder.getText();
    }
}
