package su.leads.api;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import su.leads.config.AuthConfig;
import su.leads.config.TestBaseApi;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.http.ContentType.JSON;
import static su.leads.helpers.CustomApiListener.withCustomTemplates;

@Tag("testEshop")
public class TestAuth extends TestBaseApi {


    @Test
    public void authTestEshop() {
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        HashMap<String, Object> body = new HashMap<>();
        body.put("login", config.login());
        body.put("password", config.password());

        var response = RestAssured.given()
                .filter(withCustomTemplates())
                .filter(new AllureRestAssured())
                .relaxedHTTPSValidation()
                .contentType(JSON)
                .header("webmaster_models_web_LoginForm[email]", "rzd")
                .body(body)
                .log().uri().log().body()
                .post("http://webmaster.dev-igort.leads/login")
                .then()
                .log().body()
                .extract()
                .response();

        var token = response.jsonPath().get("result.tokenId").toString();

        open("http://webmaster.dev-igort.leads/app");
        Selenide.sessionStorage().setItem("key", token);
        Selenide.refresh();
        $("[title='Аналитическая сводка']").should(appear);

    }
}
