package su.leads.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsLeads {

    private final SelenideElement
            clickEntrance = $(".pull-right"),
            insert = $("[placeholder='Вставьте сюда ссылку']"),
            click = $(".lds-btn.link-shortener-create-form__form-send-btn"),
            url = $(".link-shortener-list-row-left-side__origin-link"),
            copy = $(By.cssSelector(".link-shortener-create-form__form-result-control-symbol")),
            checking =  $(".notify__text"),
            clickonthecross =$(By.cssSelector(".lds-control__input-symbol")),
            message = $(".lds-control__message"),
            clearfield = $("#input-url")
                    ;

    public PageObjectsLeads openPage() {
        open("https://webmaster.leads.su/login");
        return this;
    }
    public PageObjectsLeads loginClick() {
        clickEntrance.click();

        return this;
    }
    public PageObjectsLeads openPageLinkShortener() {
    open("https://webmaster.leads.su/app/linkShortener");
        return this;
    }
    public PageObjectsLeads insertLinkShortener() {
        insert.setValue("https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        return this;
    }

    public PageObjectsLeads clickreduce() {
        click.click();
        return this;
    }

    public PageObjectsLeads shouldHaveUrl() {
    String expectedText = "https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ";
        url.shouldHave(visible).shouldHave(Condition.text(expectedText));
        return this;
    }

    public PageObjectsLeads copyTheLink() {
        String expectedText4 = " Ссылка скопирована ";
        copy.click();
        checking.shouldHave(visible).shouldHave(Condition.text(expectedText4));
        return this;
    }


    public PageObjectsLeads checkTheLink() {
        Selenide.switchTo().newWindow(WindowType.TAB);
        Selenide.open(Selenide.clipboard().getText());
        String currentUrl = WebDriverRunner.url();
        String expectedUrl = "https://pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ";
        Assertions.assertEquals(expectedUrl, currentUrl, "URL не совпадает");
        switchTo().window(0);
        return this;
    }
    public PageObjectsLeads checkingTheLinkShortening() {
        String expectedText2 = " Невозможно сократить эту ссылку ";
        clickonthecross.$$(By.cssSelector(".link-shortener-create-form__form-result-control-symbol")).get(1).click();
        insert.setValue("//pxl.leads.su/click/1e5864cf2d28b6006a8213414921b89d?erid=LjN8KP7zQ");
        click.click();
        message.shouldHave(visible).shouldHave(Condition.text(expectedText2));
        return this;
    }
    public PageObjectsLeads checkForShorteningOfBannedLinks() {
        String expectedText3 = " Невозможно сократить эту ссылку ";
        clearfield.setValue("");
        insert.setValue("https://vk.com/");
        click.click();
        message.shouldHave(visible).shouldHave(Condition.text(expectedText3));
        return this;
    }


}
