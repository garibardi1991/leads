package su.leads.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import su.leads.pages.PageObjectsLeads;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("testLeads")
public class TestsLeads {
    PageObjectsLeads pageObjectsLeads= new PageObjectsLeads();

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

        step("Открываем сайт leads.su", () ->
        pageObjectsLeads.openPage());
        step("Вводим логин и пароль", () ->{
        $("[placeholder='ваш email']").setValue("trubikhov.i@leads.su");
        $("[placeholder='пароль']").setValue("Oly05041987!");
        });
        step("Авторизуемся", () ->
        pageObjectsLeads.loginClick());

        step("Открываем сокращатор ссылок", () ->
        pageObjectsLeads.openPageLinkShortener());
        step("Вставляем ссылку в поле сокрощатора", () ->
                pageObjectsLeads.insertLinkShortener());
        step("Нажимаем на кнопку сократить", () ->
        pageObjectsLeads.clickreduce());
        step("Проверяем верную ли ссылку мы сократили", () ->
        pageObjectsLeads.shouldHaveUrl());


        step("Копируем сокращенную ссылку и проверяем ввод сообщения что она сокращена", () ->
        pageObjectsLeads.copyTheLink());


        step("Вставляем в браузер ссылку и проверяем, что она верна", () ->
        pageObjectsLeads.checkTheLink());

        //Вставить ссылку без https в поле "Вставьте сюда ссылку" и нажать на кнопку "Сократить"
        $(By.cssSelector(".lds-control__input-symbol")).$$(By.cssSelector(".link-shortener-create-form__form-result-control-symbol")).get(1).click();
        String expectedText2 = " Невозможно сократить эту ссылку ";
        $("[placeholder='Вставьте сюда ссылку']").setValue("//pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".lds-control__message").shouldHave(visible).shouldHave(Condition.text(expectedText2));

        //Вставить ссылку с https в поле "Вставьте сюда ссылку" нарушая требования "Разрешенные ссылки"
        $("#input-url").setValue("");
        String expectedText3 = " Невозможно сократить эту ссылку ";
        $("[placeholder='Вставьте сюда ссылку']").setValue("https://vk.com/");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".lds-control__message").shouldHave(visible).shouldHave(Condition.text(expectedText3));

    }
}
