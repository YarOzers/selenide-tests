package tests;

import basetests.BaseTest;
import com.codeborne.selenide.Selenide;
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
    @Step("Search in Google test 1")
    void test1() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }

    @Test
    @Tag("5")
    @Step("Search in Google test 5")
    void test5() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }

    @Test
    @Tag("8")
    @Step("Search in Google test 8")
    void test8() {
        // Exercise
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = title();

        // Verify
        assertThat(title).contains("Selenium WebDriver");
    }
}
