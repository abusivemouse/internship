package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MTSPage {
    public WebDriver driver;

    public MTSPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Определение локатора для заголовка формы быстрой оплаты
     */
    @FindBy(css = "section.pay h2")
    private WebElement paySectionTitle;

    /**
     * Определение локатора для ссылки "Подробнее о сервисе"
     */
    @FindBy(xpath = "//section[@class='pay']//a[contains(text(),'Подробнее о сервисе')]")
    private WebElement link;

    /**
     * Определение локатора для поля телефон
     */
    @FindBy(css = "#connection-phone")
    private WebElement phoneField;

    /**
     * Определение локатора для поля сумма пополнения
     */
    @FindBy(css = "#connection-sum")
    private WebElement sumField;

    /**
     * Определение локатора для кнопки пополнить
     */
    @FindBy(css = "section.pay button[type='submit']")
    private WebElement payBtn;

    /**
     * Определение локатора для окна ввода реквизитов пополнения
     */
    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    public WebElement payFrame;

    /**
     * Определение локаторов для картинок в форме пополнения
     */
    @FindBy(xpath = "//section[@class='pay']//img")
    private List<WebElement> payImgages;

    /**
     * Метод возвращает заголовок формы оплаты
     *
     * @return заголовок формы оплаты
     */
    public String getPaySectionTitle() {
        return paySectionTitle.getText().replaceAll("\n|\r\n", " ");
    }

    /**
     * Метод возвращает url ссылки "Подробнее о сервисе"
     *
     * @return url ссылки "Подробнее о сервисе"
     */
    public String getLinkUrl() throws NoSuchElementException {
        return link.getAttribute("href");
    }

    /**
     * Метод возвращает код ответа ссылки
     *
     * @param urlLink ссылка
     * @return код ответа на ссылку
     * @throws MalformedURLException ссылка имеет не корректный формат
     * @throws IOException           не удается созадть коннект
     */
    public int getRespCode(String urlLink) throws MalformedURLException, IOException {
        URL url = new URL(urlLink);
        HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
        httpURLConnect.setConnectTimeout(5000);
        httpURLConnect.connect();
        return httpURLConnect.getResponseCode();
    }

    /**
     * Метод заполняет поле телефона формы пополнения счета
     *
     * @param phoneNumber номер телефона
     * @throws NoSuchElementException поле не найдено
     */
    public void setPhoneField(String phoneNumber) throws NoSuchElementException {
        phoneField.sendKeys(phoneNumber);
    }

    /**
     * Метод заполняет поле суммы формы пополнения счета
     *
     * @param sum сумма пополнения
     * @throws NoSuchElementException поле не найдено
     */
    public void setSumField(String sum) throws NoSuchElementException {
        sumField.sendKeys(sum);
    }

    /**
     * Метод нажимает на кнопку оплаты
     *
     * @throws NoSuchElementException кнопка не найдена
     */
    public void clickPayBtn() throws NoSuchElementException {
        payBtn.click();
    }

    /**
     * Возвращает ссылку на фрейм
     *
     * @return ссылка на фрейм
     * @throws NoSuchElementException фрейм не найден
     */
    public String getFrameLink() {
        try {
            return payFrame.getAttribute("src");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Элемент не найден", e);
        }
    }

    /**
     * Возвращает отображается ли картинка именем src
     * @param scr имя картинки
     * @return отображатся ли картинка
     */
    public boolean isDisplayedImg(String scr) {
        for (WebElement payImage : payImgages) {
            if (payImage.getAttribute("src").contains(scr)) {
                return payImage.isDisplayed();
            }
        }
        return false;
    }
}