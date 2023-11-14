package su.leads.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class TestsLeads {
        public static void main(String[] args) {

            WebDriver driver = new ChromeDriver();


            WebDriverRunner.setWebDriver(driver);


            Selenide.open("https://webmaster.leads.su/app/linkShortener");
        }

    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void LinkShorteningTest () {
            //Авторизация
        open("https://webmaster.leads.su/login");
        $("[placeholder='ваш email']").setValue("trubikhov.i@leads.su");
        $("[placeholder='пароль']").setValue("Oly05041987!");
        $(".pull-right").click();

        //Корректная проверка работы ссылки
        String expectedText = "https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ";
        open("https://webmaster.leads.su/app/linkShortener");
        $("[placeholder='Вставьте сюда ссылку']").setValue("https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".link-shortener-list-row-left-side__origin-link").shouldHave(visible).shouldHave(Condition.text(expectedText));

        //Проверка копирования сокращенной ссылки
        $(By.cssSelector(".link-shortener-create-form__form-result-control-symbol")).click();
        String expectedText2 = " Ссылка скопирована ";
        $(".notify__text").shouldHave(visible).shouldHave(Condition.text(expectedText2));

        //Проверка некорректной ссылки
        $(By.cssSelector(".lds-control__input-symbol")).$$(By.cssSelector(".link-shortener-create-form__form-result-control-symbol")).get(1).click();
        String expectedText3 = " Невозможно сократить эту ссылку ";
        $("[placeholder='Вставьте сюда ссылку']").setValue("//pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".lds-control__message").shouldHave(visible).shouldHave(Condition.text(expectedText3));

    }
}
