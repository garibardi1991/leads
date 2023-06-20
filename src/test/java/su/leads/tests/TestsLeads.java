package su.leads.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestsLeads {

    @Test
    void AuthTest () {
        open("http://webmaster.dev-igort.leads/login");
        $("[placeholder='ваш email']").setValue("test@leads.su");
        $("[placeholder='пароль']").setValue("123456");
        $(".pull-right").click();

        open("http://webmaster.dev-igort.leads/app/showcase");
        $(".lds-btn_border").click();
        $("#vs1__combobox").click();
        $("[aria-autocomplete='list']").setValue("Тестовая");
        $(".lds-radio__text").shouldHave('SEO трафик').click();

    }
}
