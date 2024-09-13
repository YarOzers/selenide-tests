package tests;

import basetests.BaseSelenideTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchTest extends BaseSelenideTest {

    @BeforeEach
    void setupTest() {
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Tag("2")
    @AllureId("2")
    @Step("Search in Google test 2")
    void test2() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }

    @Test
    @Tag("3")
    @AllureId("3")
    @Step("Search in Google test 3")
    void test3() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
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
            enterUsername("testuser");
        });

        Allure.step("Enter password", () -> {
            enterPassword("password123");
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
        $(By.id("username")).setValue("ysroslav");
    }

    @Step("Enter password {password}")
    public void enterPassword(String password) {
        $(By.id("password")).setValue("333");
    }

    @Step("Click login button")
    public void clickLogin() {
        $(By.id("ck-login")).click();
    }

    @Step("Verify login success")
    public void verifyLoginSuccess() {
        Assertions.assertEquals(title(),"DragonTms");
    }

    public byte[] getScreenshotBytes() {
        // получение скриншота
        return new byte[0];
    }

}
