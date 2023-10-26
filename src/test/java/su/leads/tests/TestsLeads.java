package su.leads.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestsLeads {


    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.browser = "chrome";
        }

    @Test
    void AuthTest () {
        open("https://webmaster.leads.su/login");
        $("[placeholder='ваш email']").setValue("trubikhov.i@leads.su");
        $("[placeholder='пароль']").setValue("Oly05041987!");
        $(".pull-right").click();

//        open("http://webmaster.dev-igort.leads/app/showcase");
//        $(".lds-btn_border").click();
//        $("#vs1__combobox").click();
//        $("[aria-autocomplete='list']").setValue("Тестовая");
//        $(".lds-radio__text").shouldHave('SEO трафик').click();
        String expectedText = "https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ";
        open("https://webmaster.leads.su/app/linkShortener");
        $("[placeholder='Вставьте сюда ссылку']").setValue("https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        $(".lds-btn.link-shortener-create-form__form-send-btn").click();
        $(".link-shortener-list-row-left-side__origin-link").shouldHave(visible).shouldHave(Condition.text(expectedText));

    }
}
