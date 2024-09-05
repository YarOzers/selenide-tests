package tests;

import basetests.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchTest extends BaseTest {

    @BeforeEach
    void setupTest() {
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }

    @Test
    @Tag("1")
    @AllureId("1")
    @Step("Search in Google test 1")
    void test1() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
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
}
