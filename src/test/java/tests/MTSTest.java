package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class MTSTest extends BaseTest {

    @BeforeAll
    static void setup() {
        before();
    }

    @AfterAll
    static void teardown() {
        after();
    }

    @Test
    @DisplayName("Заголовок формы")
    public void title() {
        String name = "Заголовок";
        try {
            String actualValue = mtsPage.getPaySectionTitle();
            assertEquals("Онлайн пополнение без комиссии", actualValue, name + " не совпадает");
            System.out.println(name + " совпадает: " + actualValue);
        } catch (NoSuchElementException e) {
            assertTrue(false, name + " не найден");
        }
    }

    @ParameterizedTest
    @DisplayName("Картинки платежных систем")
    @ValueSource(strings = {"visa.svg", "visa-verified.svg", "mastercard.svg", "mastercard-secure.svg", "belkart.svg"})
    void payPics(String src) {
        try {
            assertTrue(mtsPage.isDisplayedImg(src), "Картинка " + src + " не отображается");
            System.out.println("Картинка " + src + " отображается");
        } catch (NoSuchElementException e) {
            assertTrue(false, "Картинка " + src + " не найдена");
        }
    }

    @Test
    @DisplayName("Ссылка 'Подробнее о сервисе'")
    void detailLink() {
        String urlLink = null;
        try {
            urlLink = mtsPage.getLinkUrl();
            int linkResponseCode = mtsPage.getRespCode(urlLink);
            assertTrue(linkResponseCode < 400, "Ссылка " + urlLink + " битая (код: " + linkResponseCode + ")");
            System.out.println("Ссылка " + urlLink + " рабочая (код: " + linkResponseCode + ")");
        } catch (NoSuchElementException e) {
            assertTrue(false, "Нет ссылки");
        } catch (MalformedURLException e) {
            assertTrue(false, "Не корректный url: " + urlLink);
        } catch (IOException e) {
            assertTrue(false, "Проблема с соединением");
        }
    }

    @Test
    @DisplayName("Работа кнопки 'Продолжить'")
    void payForm() {
        String name = "Окно оплаты";
        mtsPage.setPhoneField("297777777");
        mtsPage.setSumField("77");
        mtsPage.clickPayBtn();

        String expectedUrl = "https://checkout.bepaid.by/widget_v2/index.html";
        String actualValue = null;

        try {
            actualValue = mtsPage.getFrameLink();
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }

        assertEquals(expectedUrl, actualValue, name + " не открылось");
        System.out.println(name + " открылось");
    }
}