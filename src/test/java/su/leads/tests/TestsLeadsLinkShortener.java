package su.leads.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import su.leads.config.TestBase;
import su.leads.pages.PageObjectsLeads;


import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("testLeads")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestsLeadsLinkShortener extends TestBase {
    PageObjectsLeads pageObjectsLeads= new PageObjectsLeads();

    @Test
    @Order(1)
    @Feature("Проверка правильной работы сокрощатора")
    @Story("Авторизуемся и сокрощаем корректную ссылку")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://webmaster.leads.su/app/linkShortener")
    @DisplayName("Сократить и проверить ссылку на странице")
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

    }

    @Test
    @Order(2)
    @Feature("Проверка ссылок")
    @Story("Копируем ссылку и проверяем её ли сократили")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://webmaster.leads.su/app/linkShortener")
    @DisplayName("Открываем новую вкладку и проверяем ссылку")
    void LinkShorteningTest2 () {
        step("Копируем сокращенную ссылку и проверяем вывод сообщения что она скопированна", () ->
                pageObjectsLeads.copyTheLink());
        step("Вставляем в браузер ссылку и проверяем, что она верна", () ->
                pageObjectsLeads.checkTheLink());
    }

    @Test
    @Order(3)
    @Feature("Отрицательная проверка ссылок")
    @Story("Проверяем некорректную ссылку")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://webmaster.leads.su/app/linkShortener")
    @DisplayName("Открываем сокращатор и сокращаем ссылки без https и неразрешенные")
    void LinkShorteningTest3 () {
        step("Проверка сокращения ссылки без https", () ->
                pageObjectsLeads.checkingTheLinkShortening());

        step("Проверка сокращения ссылки нарушая требования Разрешенные ссылки", () ->
                pageObjectsLeads.checkForShorteningOfBannedLinks());
    }
}
