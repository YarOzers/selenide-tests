package tests;

import basetests.BaseSelenideTest;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.*;

public class CreateProjectTest extends BaseSelenideTest {

    Faker faker = new Faker();
    private final String projectName = faker.funnyName().name();
    private ElementsCollection initialRowsCount;
    private ElementsCollection rowsAfterAddition;
    private SelenideElement cell;


    @Epic("Project")
    @Feature("Create")
    @Story("User can be create project")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The test shows the possibility of creating a test case")
    @Owner("Yaroslav Ozerskiy")
//    @Issue("BUG-123")
//    @TmsLink("TMS-456")
    @Test
    @Tag("2")
    @AllureId("2")
    public void createProject() {
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

        Allure.step("Find initial number of rows in the table", () -> {
            findInitialRowsInTable();
        });

        Allure.step("Click add project button", () -> {
            clickAddProjectBtn();
        });

        Allure.step("Enter project name", () -> {
            enterProjectName();
        });

        Allure.step("Click save button", () -> {
            clickSaveBtn();
        });

        Allure.step("Check that there are more rows", () -> {
            expectMoreRows();
        });

        Allure.step("Find last row and find a specific cell by column index", () -> {
            findLastRow();
        });

        Allure.step("Check that the cell contains expected project name", () -> {
            checkCell();
        });


        Allure.addAttachment("Page Screenshot", new ByteArrayInputStream(getScreenshotBytes()));
    }

    @Step("Check that the cell contains the expected text")
    private void checkCell() {
        Assertions.assertTrue(cell.getText().contains(projectName), "The cell must contain text: " + projectName);
    }


    @Step("Find last row")
    private void findLastRow() {
        SelenideElement lastRow = rowsAfterAddition.get(rowsAfterAddition.size() - 1);
        cell = lastRow.$(By.cssSelector(".mat-mdc-cell:nth-child(2)"));
    }

    @Step("Check that rows became more")
    private void expectMoreRows() {
       $$(".mat-mdc-row").shouldHave(CollectionCondition.sizeGreaterThan(initialRowsCount.size()));
    }

    @Step("Find initial count rows in table")
    private void findInitialRowsInTable() {
        this.initialRowsCount = $$(By.cssSelector(".mat-mdc-row"));

    }

    @Step("Click save btn")
    private void clickSaveBtn() {
        $(By.className("save-btn")).click();
        rowsAfterAddition = $$(".mat-mdc-row");
    }

    @Step("Enter project name")
    private void enterProjectName() {
        $(By.id("projectName")).setValue(projectName);
    }

    @Step("Click add project btn")
    private void clickAddProjectBtn() {
        $(By.cssSelector("button.mdc-button")).click();
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
        Assertions.assertEquals(title(), "DragonTms");
    }

    public byte[] getScreenshotBytes() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
