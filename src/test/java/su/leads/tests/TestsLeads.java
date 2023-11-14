package su.leads.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestsLeads {



    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void LinkShorteningTest () {
        open("https://webmaster.leads.su/login");
        $("[placeholder='ваш email']").setValue("trubikhov.i@leads.su");
        $("[placeholder='пароль']").setValue("Oly05041987!");
        $(".pull-right").click();

        String expectedText = "https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ";
        open("https://webmaster.leads.su/app/linkShortener");
        $("[placeholder='Вставьте сюда ссылку']").setValue("https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".link-shortener-list-row-left-side__origin-link").shouldHave(visible).shouldHave(Condition.text(expectedText));
        $("#shorted-url").setValue("");

        String expectedText2 = "Невозможно сократить эту ссылку";
        $("[placeholder='Вставьте сюда ссылку']").setValue("//pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".lds-control__message").shouldHave(visible).shouldHave(Condition.text(expectedText2));

    }
}
