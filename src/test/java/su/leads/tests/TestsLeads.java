package su.leads.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import su.leads.config.TestBase;
import su.leads.pages.PageObjectsLeads;


import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("testLeads")
public class TestsLeads extends TestBase {
    PageObjectsLeads pageObjectsLeads= new PageObjectsLeads();



//    @BeforeAll
//    static void configure() {
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = false;
//        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
//    }

    @Test
    @Feature("Проверка сокрощатора ссылок")
    @Story("Проверяем общую работу сокрощатора")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://webmaster.leads.su/app/linkShortener")
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

        step("Проверка сокращения ссылки без https", () ->
        pageObjectsLeads.checkingTheLinkShortening());

        step("Проверка сокращения ссылки нарушая требования Разрешенные ссылки", () ->
                pageObjectsLeads.checkForShorteningOfBannedLinks());
    }
}
