package su.leads.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestConstructor {
    @BeforeAll
    static void configure() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void AuthTest () {
        open("https://webmaster.leads.su/login");
        $("[placeholder='ваш email']").setValue("trubikhov.i@leads.su");
        $("[placeholder='пароль']").setValue("Oly05041987!");
        $(".pull-right").click();

        open("http://webmaster.dev-igort.leads/app/showcase");
        $(".lds-btn_border").click();
        $("#vs1__combobox").click();
        $("[aria-autocomplete='list']").setValue("Тестовая");
        }
}
