package tests;

import basetests.BaseSelenideTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationTest extends BaseSelenideTest {

    @BeforeEach
    void setupTest() {
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }

    @Epic("Authentication")
    @Feature("Login Feature")
    @Story("User can login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies login with valid credentials")
    @Owner("Yaroslav Ozerskiy")
//    @Issue("BUG-123")
//    @TmsLink("TMS-456")
    @Test
    @Tag("1")
    @AllureId("1")
    public void loginWithValidCredentialsTest() {
        Allure.step("Open login page", () -> {
            openLoginPage();
        });

        Allure.step("Enter username", () -> {
            enterUsername("yaroslav");
        });

        Allure.step("Enter password", () -> {
            enterPassword("333");
        });

        Allure.step("Click login button", () -> {
            clickLogin();
        });

        Allure.step("Verify login success", () -> {
            verifyLoginSuccess();
        });

        Allure.addAttachment("Page Screenshot", new ByteArrayInputStream(getScreenshotBytes()));
    }

    @Step("Open login page")
    public void openLoginPage() {
        open("http://188.235.130.37:4200");
    }

    @Step("Enter username {username}")
    public void enterUsername(String username) {
        $(By.id("username")).setValue(username);
    }

    @Step("Enter password {password}")
    public void enterPassword(String password) {
        $(By.id("password")).setValue(password);
    }

    @Step("Click login button")
    public void clickLogin() {
        $(By.id("kc-login")).click();
    }

    @Step("Verify login success")
    public void verifyLoginSuccess() {
        Assertions.assertEquals(title(),"DragonTms");
    }

    public byte[] getScreenshotBytes() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
