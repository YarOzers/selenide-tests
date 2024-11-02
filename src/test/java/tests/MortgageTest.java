package tests;


import basetests.BaseSelenideTest;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.github.javafaker.Faker;
import efr.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Epic("Mortgage Tests")
@Feature("Mortgage functionality")
public class MortgageTest extends BaseSelenideTest {

    private static SelenideDriver userDriver;

    private static WebDriver originalDriver;
    private static String originalWindowHandle;
    private static List<String> windowHandles = new ArrayList<>();

    @Test
    @Story("Создание клиента")
    @Description("Создание клиента")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Создание клиента")
    @Disabled
    public void createClient() {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();


        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента" + uksDul, () -> {
            createClient.fillInCreateClientForm();
        });
        System.out.println(uksDul);
    }


    @Test
    @Story("И10")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Test login with valid credentials")
    @Disabled
    public void mortgage_I10() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Переход на страницу поиска клиентов", () -> {
            clientProfile.goToSearchClientPage();
        });

        int dulSeriaMain = faker.number().numberBetween(1000, 9999);
        int dulNumberMain = faker.number().numberBetween(100000, 999999);
        String dulMain = String.valueOf(dulSeriaMain) + String.valueOf(dulNumberMain);

        System.out.println(dulMain);
        step("Вводим дул нового клиента", () -> {
            findClientPage.newClient(dulMain);
        });
        step("Заполнение формы создания клиента " + dulMain, () -> {
            createClient.fillInCreateClientForm();
        });

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I10();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn();
        });

        step("Добавление созаемщика", () -> {
            productOffer.addUks(uksDul);
        });

        step("Заполнение персональной информации созаемщика", () -> {
            productOffer.setPersonalInfoTabUks();
        });

        step("Заполнение вкладки работа созаемщика", () -> {
            productOffer.setWorkTabUks();
        });

        step("Заполнение вкладки доходы и расходы созаемщика", () -> {
            productOffer.setRevenuesAndExpensesTabUks();
        });

        step("Заполнение вкладки иная информация созаемщика", () -> {
            productOffer.setOtherInformationTabUks();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

    }


    @RepeatedTest(1)   // несколько запусков
//    @Test   // Один запуск
    @Story("И5")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("И5")

    public void mortgage_I5() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);
        System.out.println(uksDul);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Переход на страницу поиска клиентов", () -> {
            clientProfile.goToSearchClientPage();
        });

        int dulSeriaMain = faker.number().numberBetween(1000, 9999);
        int dulNumberMain = faker.number().numberBetween(100000, 999999);
        String dulMain = String.valueOf(dulSeriaMain) + String.valueOf(dulNumberMain);

        System.out.println(dulMain);
        step("Вводим дул нового клиента", () -> {
            findClientPage.newClient(dulMain);
        });
        step("Заполнение формы создания клиента " + dulMain, () -> {
            createClient.fillInCreateClientForm();
        });

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I5();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn_I5();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn_I5();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn_I5();
        });

        step("Добавление созаемщика", () -> {
            productOffer.addUks(uksDul);
        });

        step("Заполнение персональной информации созаемщика", () -> {
            productOffer.setPersonalInfoTabUks();
        });

        step("Заполнение вкладки работа созаемщика", () -> {
            productOffer.setWorkTabUks();
        });

        step("Заполнение вкладки доходы и расходы созаемщика", () -> {
            productOffer.setRevenuesAndExpensesTabUks();
        });

        step("Заполнение вкладки иная информация созаемщика", () -> {
            productOffer.setOtherInformationTabUks();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm_I5();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

    }

    @RepeatedTest(1)   // несколько запусков
//    @Test   // Один запуск
    @Story("И34")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("И34")

    public void mortgage_I34() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);
        System.out.println(uksDul);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));

        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Верификация клиента через смс", ()->{
            createClient.verifySms();
        });

        step("Переход на страницу поиска клиентов", () -> {
            clientProfile.goToSearchClientPage();
        });

        int dulSeriaMain = faker.number().numberBetween(1000, 9999);
        int dulNumberMain = faker.number().numberBetween(100000, 999999);
        String dulMain = String.valueOf(dulSeriaMain) + String.valueOf(dulNumberMain);
        System.out.println(dulMain);

        step("Вводим дул нового клиента", () -> {
            findClientPage.newClient(dulMain);
        });
        step("Заполнение формы создания клиента " + dulMain, () -> {
            createClient.fillInCreateClientForm();
        });

        step("Верификация клиента через смс", ()->{
            createClient.verifySms();
        });

        Selenide.refresh();

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I34();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn_I34();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn_I34();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn();
        });

        step("Добавление созаемщика", () -> {
            productOffer.addUks(uksDul);
        });

        step("Заполнение персональной информации созаемщика", () -> {
            productOffer.setPersonalInfoTabUks();
        });

        step("Заполнение вкладки работа созаемщика", () -> {
            productOffer.setWorkTabUks();
        });

        step("Заполнение вкладки доходы и расходы созаемщика", () -> {
            productOffer.setRevenuesAndExpensesTabUks();
        });

        step("Заполнение вкладки иная информация созаемщика", () -> {
            productOffer.setOtherInformationTabUks();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        Thread.sleep(10000);

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm_I34();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

        step("Отправить на проверку мидлу", ()->{
            creditApplication.SendForInspection();
        });

    }

    @Test
    @Story("И10")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Test login with valid credentials")
    @Disabled("Отключенный тест")
    public void mortgage() throws Exception {
        // Arrange: открываем страницу
        open("https://test02.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        ClientProfile productProfile = new ClientProfile();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        ProductOffer productOffer = new ProductOffer();

        loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        findClientPage.findClient("9085 743434");
        productProfile.takeOutMortgage();
        processInitiationPage.continueProcess();
        printedFormsPage.uploadPersonalData();
        printedFormsPage.uploadBki();
        printedFormsPage.continueProcess();
        creditCalculator.I10();
        visualAssessment.clickContinueBtn();
        productOffer.personalDataTabFillIn();
        productOffer.workTabFillIn();
        productOffer.revenuesAndExpensesTabFillIn();
        productOffer.ndfl2FillInn();
        productOffer.otherInformationTabFillIn();
        productOffer.setProductOfferTabFillIn();
        productOffer.goToFullProfile();
    }


    @RepeatedTest(1)   // несколько запусков
//    @Test   // Один запуск
    @Story("И5")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("И5")
    public void mortgage_I5_1uks() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);
        System.out.println(uksDul);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I5();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn_I5();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn_I5();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn_I5();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm_I5();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

    }

    @Test
    @Story("И10")
    @Description("Какое то описание")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Test login with valid credentials")
    @Disabled
    public void mortgage_I10_1uks() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));
        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I10();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm_I10_1uks();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

    }

    @RepeatedTest(1)   // несколько запусков
//    @Test   // Один запуск
    @Story("И34")
    @Description("Ипотека И34 без созаема")
    @Severity(SeverityLevel.CRITICAL)
    @Step("И34")
    @Tag("3")
    @AllureId("3")

    public void mortgage_I34_1uks() throws Exception {
        // Arrange: открываем страницу
        open("https://test03.rshb.ru/efr");

        LoginPage loginPage = new LoginPage();
        FindClientPage findClientPage = new FindClientPage();
        CreateClient createClient = new CreateClient();
        ClientProfile clientProfile = new ClientProfile();
        ProductOffer productOffer = new ProductOffer();
        ProcessInitiationPage processInitiationPage = new ProcessInitiationPage();
        PrintedFormsPage printedFormsPage = new PrintedFormsPage();
        CreditCalculator creditCalculator = new CreditCalculator();
        VisualAssessment visualAssessment = new VisualAssessment();
        CreditApplication creditApplication = new CreditApplication();
        ApartmentOnTheSecondaryMarket apartmentOnTheSecondaryMarket = new ApartmentOnTheSecondaryMarket();

        Faker faker = new Faker(new Locale("ru"));
        int dulSeria = faker.number().numberBetween(1000, 9999);
        int dulNumber = faker.number().numberBetween(100000, 999999);
        String uksDul = String.valueOf(dulSeria) + String.valueOf(dulNumber);
        System.out.println(uksDul);

        step("открыть ефр", () -> {
            loginPage.login(System.getenv("EFR_CLIENT_MNG_USER_LOGIN"), System.getenv("EFR_CLIENT_MNG_USER_PASSWORD"));

        });

        step("Поиск нового клиента " + uksDul, () -> {
            findClientPage.newClient(uksDul);
        });

        step("Заполнение формы создания нового клиента (созаем)", () -> {
            createClient.fillInCreateClientForm();
        });

        step("Верификация клиента через смс", ()->{
            createClient.verifySms();
        });

        step("Инициация ипотеки", () -> {
            clientProfile.takeOutMortgage();
        });

        step("Переход на форму печати документов", () -> {
            processInitiationPage.continueProcess();
        });

        step("Загрузка документов", () -> {
            printedFormsPage.uploadPersonalData();
            printedFormsPage.uploadBki();
        });

        step("Переход на кредитный калькулятор", () -> {
            printedFormsPage.continueProcess();
        });

        step("Заполнение формы кредитного калькулятора", () -> {
            creditCalculator.I34();
        });

        step("Визуальная оценка, переход на продуктовое предложение", () -> {
            visualAssessment.clickContinueBtn();
        });

        step("Заполнение персональной информации заемщика", () -> {
            productOffer.personalDataTabFillIn();
        });

        step("Заполнение работы заемщика", () -> {
            productOffer.workTabFillIn_I34();
        });

        step("Заполнение доходов и расходов заемщика", () -> {
            productOffer.revenuesAndExpensesTabFillIn_I34();
            productOffer.ndfl2FillInn();
        });


        step("Заполнение иная информация заемщика", () -> {
            productOffer.otherInformationTabFillIn();
        });

        step("Заполнение вкладки продуктовое предложение", () -> {
            productOffer.setProductOfferTabFillIn();
        });

        step("Переход на полную анкету", () -> {
            productOffer.goToFullProfile();
        });

        Thread.sleep(10000);

        step("Заполнение поленй полной анкеты", () -> {
            creditApplication.fillInCreditApplicationForm_I34();
        });

        step("Заполнение залога", () -> {
            apartmentOnTheSecondaryMarket.fillInBailForm();
        });

//        step("Отправить на проверку мидлу", ()->{
//            creditApplication.SendForInspection();
//        });

    }
}
