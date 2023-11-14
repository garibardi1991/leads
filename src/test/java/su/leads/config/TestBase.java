package su.leads.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.eshoprzd.helpers.Attach;

public class TestBase {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        WebDriverRunner.setWebDriver(driver);

        Selenide.open("https://webmaster.leads.su/app/linkShortener");
    }

    @BeforeAll
    static void configure() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        SelenideLogger.addListener("allure", new AllureSelenide());


        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
//        Configuration.browserCapabilities = capabilities;
//        Configuration.browser = Property.browser();
//        Configuration.browserVersion = Property.browserVersion();
//        Configuration.browserSize = Property.browserSize();
//        if (!Property.remoteUrl().equals("")) {
//            Configuration.remote = Property.remoteUrl();
//        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
