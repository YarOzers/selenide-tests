package efr;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductOffer {

    private SelenideElement header = $x("//h1[contains(text(), 'Продуктовое предложение')]");

    private SelenideElement addHelp = $x("//button[contains(text(), 'Добавить справку')]");

    private SelenideElement snilsInput = $("#snils");

    private SelenideElement educationInput = $("#informationSource");
    private SelenideElement educationSelect = $x("//div[contains(text(),'Высшее')]");

    //  срок проживания
    private SelenideElement residenceTermInput = $x("//input[@title='Введите срок']");
    private SelenideElement houseTypeInput = $("#houseType");
    private SelenideElement houseTypeSelect = $x("//div[contains(text(),'Собственность')]");
    private SelenideElement familyMemberCountInput = $("#familyMemberCount");
    private SelenideElement underageChildCountInput = $("#underageChildCount");
    private SelenideElement dependantCountInput = $("#dependantCount");

    private SelenideElement topBlock = $("div.top-block");

    private SelenideElement workTab = $x("(//ul[@role='tablist'])[2]//li[2]");

    private SelenideElement workAccordionHeader = $x("//h1[contains(text(), 'Информация о местах работы')]");

    private SelenideElement workAccordion = $x("//div[@class='bx--accordion__title']");

    private SelenideElement workAccordionIsExpanded = $x("(//button[@aria-expanded='false'])[2]");

    private SelenideElement organizationTypeInput = $("#organizationType");
    private SelenideElement organizationTypeSelect = $x("//div[contains(text(), 'Коммерческая')]");
    private SelenideElement organizationCategoryInput = $("#organizationCategory");

    private SelenideElement organizationCategorySelect = $x("//div[contains(text(), 'Российская')]");

    private SelenideElement opfInput = $("#opf");
    private SelenideElement opfSelect = $x("//div[contains(text(),'Общество с ограниченной ответственностью')]");
    private SelenideElement organizationMobilePhoneInput = $x("//input[@title='Введите номер телефона']");

    private SelenideElement employeeCountInput = $x("//input[@data-id='employee-count']");
    private SelenideElement employeeCountSelect = $x("//div[contains(text(), 'Более 500 чел')]");

    private SelenideElement employmentContractRadioBtn = $x("//span[contains(text(),'Бессрочный')]");

    private SelenideElement organizatonActivityTypeInput = $x("//input[@label='Вид деятельности']");

    private SelenideElement organizatonActivityTypeSelect = $x("//div[contains(text(),'Консалтинговые услуги')]");

    private SelenideElement jobPositionInput = $("#job-position");

    private SelenideElement jobPositionSelect = $x("//div[contains(text(),'Высококвалифицированный специалист')]");

    private SelenideElement jobStartDateInput = $("#job-start-date");
    private SelenideElement jobRegionInput = $("#job-region");

    private SelenideElement jobCityInput = $x("//input[@title='Введите город']");

    private SelenideElement jobStreetInput = $("#job-street");
    private SelenideElement jobNumberHomeInput = $("#job-number-home");
    private SelenideElement jobIndexHomeInput = $("#job-index-home");
    private SelenideElement totalExperienceYearsInput = $x("//input[@data-id='total-experience-years']");
    private SelenideElement fiveEearExperienceYearsInput = $x("//input[@data-id='five-year-experience-years']");
    private SelenideElement fiveEearsJobCountInput = $x("//input[@data-id='five-years-job-count']");
    private SelenideElement revenuesAndExpensesTab = $x("(//ul[@role='tablist'])[2]//li[3]");

    private SelenideElement mainIncomeConfirmDocument = $("#mainIncomeConfirmDocument");

    private SelenideElement mainIncomeSelect = $x("//div[contains(text(),'Справка по форме Банка с печатью')]");

    private SelenideElement avgIncome = $x("//input[@data-id='mainIncomeConfirmDocument-avgAmount']");

    private SelenideElement expencesInput = $x("//input[@data-id='required-payments']");

    private SelenideElement is2ndflIncomeConfirm = $x("//input[@data-id='mainIncomeConfirmDocument' and @value='Справка о доходах ФЛ с печатью']");

    private SelenideElement ndfl2Btn = $x("//button[contains(text(), 'Справка 2-НДФЛ')]");

    private SelenideElement addNre2NDFLBtn = $x("//button[contains(text(), 'Добавить справку 2-НДФЛ')]");
    private SelenideElement dateOfReference1 = $("#job-end-date");
    private SelenideElement referenceNumber1 = $("#reference-number");
    private SelenideElement referenceRowYear1 = $x("(//input[@id='reference-row-year'])[1]");

    private SelenideElement dateOfReference2 = $x("(//*[@id=\"job-end-date\"])[2]");

    private SelenideElement referenceNumber2 = $x("(//*[@id=\"reference-number\"])[2]");

    private SelenideElement referenceRowYear2 = $x("(//input[@id='reference-row-year'])[2]");

    private SelenideElement addBtn1 = $x("//div[@class='title' and contains(normalize-space(), 'Справка 2-НДФЛ | №1')]/ancestor::div[4]//button[contains(text(),'Добавить')]");
    private SelenideElement addBtn2 = $x("//div[@class='title' and contains(normalize-space(), 'Справка 2-НДФЛ | №2')]/ancestor::div[4]//button[contains(text(),'Добавить')]");

    private ElementsCollection monthList = $$("#month");

    private ElementsCollection articleIncomeInputList = $$("#incomeItemNumber");

    private ElementsCollection incomeInputList = $$x("//div[@class='title' and contains(normalize-space(), 'Справка 2-НДФЛ | №1')]/ancestor::div[4]//input[@title='Введите сумму']");

    private SelenideElement except2NDFLModalBtn = $x("(//button[contains(text(),'Подтвердить')])[1]");

    private SelenideElement otherInformationTab = $x("(//ul[@role='tablist'])[2]//li[4]");

    private SelenideElement militaryServiceRelationInput = $("#militaryServiceRelation");

    private SelenideElement militaryServiceRelationSelect = $x("//div[@title='Не военнослужащий']");

    private SelenideElement bankInfoSourcesInput = $("#bankInfoSources");

    private SelenideElement bankInfoSourcesSelect = $x("//div[contains(text(), 'Печатные материалы банка (буклеты, листовки и т.д.)')]");

    private SelenideElement consentPhotographedToggle = $x("//label[contains(text(), 'Согласие на осуществление АО «Россельхозбанк» фотографирования')]//span[@class='bx--toggle__switch']");

    private SelenideElement productOfferTab = $x("//button//span[contains(text(), 'Продуктовое предложение')]");

    private SelenideElement employeesInput = $("#employees");

    private SelenideElement employeesSelect = $x("//div[contains(text(), 'Клиентский менеджер Александров Иван Васильевия 23464')]");

    private SelenideElement loanSecurityInput = $("#loan-security");

    private SelenideElement loanSecuritySelect = $x("//div[contains(text(), 'Залог недвижимости')]");

    private SelenideElement repaymentSchemesInput = $("#repayment-schemes");

    private SelenideElement getRepaymentSchemesSelect = $x("//div[contains(text(),'Аннуитетные платежи')]");

    private SelenideElement requestBtn = $x("//button[contains(text(), 'Запросить')]");

    private SelenideElement goToFullProfileBtn = $x("//button[contains(text(),'Перейти на полную анкету')]");

    private SelenideElement addUksBtn = $x("(//button[@color='#fff'])[1]");
    private SelenideElement addUksContinueBtn = $x("//button[contains(text(),'Далее')]");

    private SelenideElement inputPassport = $x("//input[@id='passport']");

    private SelenideElement searchUksBtn = $x("//button[contains(text(),'Поиск')]");
    private SelenideElement searchUksContinueBtn = $x("//button[contains(text(),'Далее')]");
    private SelenideElement pringSoglBtn = $x("(//button[@data-id='undefined_print-button-undefined-button'])[1]");
    private SelenideElement pringBkiBtn = $x("(//button[@data-id='undefined_print-button-undefined-button'])[2]");
    private SelenideElement soglClientWasGottenSwitcher = $x("//label[contains(text(),'Согласие клиента получено')]//span");

    private SelenideElement soglLoadBtn = $x("(//input[@data-id='undefined_upload-button-undefined-input-file'])[1]");
    private SelenideElement bkiLoadBtn = $x("(//input[@data-id='undefined_upload-button-undefined-input-file'])[2]");

    private SelenideElement addUksPrintFormContinueBtn = $x("//button[contains(text(),'Далее')]");
    private SelenideElement confirmAddUksBtn = $x("(//button[contains(text(), 'Подтвердить')])[1]");

    private SelenideElement uksTabBtn = $x("//button//span[contains(text(),'Созаёмщик')]");

    private SelenideElement educationInputUks = $x("(//input[@id='informationSource' and @label='Образование'])[2]");
    private SelenideElement educationSelectUks = $x("//div[contains(text(), 'Высшее')]");

    private SelenideElement snilsUksInput = $x("(//input[@id='snils'])[2]");

    private SelenideElement relationToBorrowerUksInput = $x("//input[@id='relation-to-borrower']");
    private SelenideElement relationToBorrowerUksSelect = $x("//div[contains(text(),'Брат')]");
    private SelenideElement residenceTermUksInput = $x("(//input[@id='residence term'])[2]");
    private SelenideElement houseTypeUksInput = $x("(//input[@id='houseType'])[2]");
    private SelenideElement houseTypeUksSelect = $x("//div[contains(text(),'Собственность')]");
    private SelenideElement maritalStatusUksInput = $x("(//input[@label='Семейное положение'])[2]");
    private SelenideElement maritalStatusUksSelect = $x("//div[contains(text(),'Холост/Не замужем')]");
    private SelenideElement familyMemberUksCountInput = $x("(//input[@id='familyMemberCount'])[2]");
    private SelenideElement underageChildCountUksCountInput = $x("(//input[@id='underageChildCount'])[2]");
    private SelenideElement dependantCountUksCountInput = $x("(//input[@id='dependantCount'])[2]");

    private SelenideElement workTabUks = $x("(//button[@role='tab']//span[contains(text(), 'Работа')])[2]");
    private SelenideElement employmentUksInput = $x("(//input[@id='employment'])[2]");
    private SelenideElement employmentUksSelect = $x("//div[contains(text(), 'Работаю')]");
    private SelenideElement addWorkPlaceUksBtn = $x("(//button[contains(text(),'Добавить место работы')])[2]");

    private SelenideElement workPlaceUksAkordion = $x("(//div[@class='bx--accordion__footer']//span[contains(text(),'Заполните поля')])[2]");

    private SelenideElement organizationTypeUksInput = $x("(//input[@id='organizationType'])[2]");
    private SelenideElement organizationTypeUksSelect = $x("//div[contains(text(), 'Коммерческая')]");
    private SelenideElement organizationCategoryUksInput = $x("(//input[@id='organizationCategory'])[2]");
    private SelenideElement organizationCategoryUksSelect = $x("//div[contains(text(), 'Российская')]");

    private SelenideElement opfTypeUksInput = $x("(//input[@data-id='opf'])[2]");
    private SelenideElement organizationNameUksInput = $x("(//input[@id='organization name'])[2]");
    private SelenideElement organizationInnUksInput = $x("(//input[@id='organization inn'])[2]");
    private SelenideElement organizationPhoneUksInput = $x("(//input[@id='organization mobile-phone'])[2]");

    private SelenideElement employeeCountUksInput = $x("(//input[@data-id='employee-count'])[2]");
    private SelenideElement employeeCountUksSelect = $x("//div[contains(text(),'Более 500 чел')]");

    private SelenideElement employmentContractTypeRadioBtnUks = $x("(//span[contains(text(),'Бессрочный')])[2]");
    private SelenideElement organizationActivityTypeUksInput = $x("(//input[@id='organizaton activity type'])[2]");
    private SelenideElement organizationActivityTypeUksSelect = $x("//div[contains(text(),'Консалтинговые услуги')]");

    private SelenideElement jobPositionUksInput = $x("(//input[@id='job-position'])[2]");
    private SelenideElement jobPositionUksSelect = $x("//div[contains(text(), 'Высококвалифицированный специалист')]");
    private SelenideElement jobStartDateUksInput = $x("(//input[@id='job-start-date'])[2]");

    private SelenideElement jobRegionUksInput = $x("(//input[@id='job-region'])[2]");

    private SelenideElement jobCityUksInput = $x("(//input[@title='Введите город'])[2]");

    private SelenideElement jobStreetUksInput = $x("(//input[@id='job-street'])[2]");

    private SelenideElement jobNumberHomeUksInput = $x("(//input[@id='job-number-home'])[2]");

    private SelenideElement jobIndexHomeUksInput = $x("(//input[@id='job-index-home'])[2]");

    private SelenideElement totalExperienceYearsUksInput = $x("(//input[@data-id='total-experience-years'])[2]");
    private SelenideElement fiveYearExperienceUksInput = $x("(//input[@data-id='five-year-experience-years'])[2]");

    private SelenideElement fiveYearJobCountUksInput = $x("(//input[@data-id='five-years-job-count'])[2]");

    private SelenideElement revenuesAndExpensesTabUks = $x("(//button[@role='tab']//span[contains(text(), 'Доходы и расходы')])[2]");

    private SelenideElement mainIncomeConfirmDocumentUksInput = $x("(//input[@id='mainIncomeConfirmDocument'])[2]");

    private SelenideElement avgIncomeUksInput = $x("(//input[@data-id='mainIncomeConfirmDocument-avgAmount'])[2]");

    private SelenideElement requiresPaymentUksInput = $x("(//input[@data-id='required-payments'])[2]");

    private SelenideElement otherInformationTabUks = $x("(//button[@role='tab']//span[contains(text(), 'Иная информация')])[2]");

    private SelenideElement militaryServiceRelationUksInput = $x("(//input[@id='militaryServiceRelation'])[2]");
    private SelenideElement militaryServiceRelationUksSelect = $x("//div[contains(text(),'Не военнослужащий')]");

    private SelenideElement bankInfoSourcesUksInput = $x("(//input[@id='bankInfoSources'])[2]");
    private SelenideElement bankInfoSourcesUksSelect = $x("//div[contains(text(), 'Печатные материалы банка (буклеты, листовки и т.д.)')]");

    private SelenideElement photoConcentUksSwitcher = $x("(//label[contains(text(),'Согласие на осуществление АО «Россельхозбанк» фотографирования')])[2]");

    private SelenideElement goToFullAccount = $x("//button[contains(text(),'Перейти на полную анкету')]");

    public void personalDataTabFillIn() {
        Selenide.executeJavaScript("document.body.style.zoom='33%'");  // Масштаб 33%

        educationInput.setValue("Высшее").pressEnter();
        snilsInput.setValue(SnilsGenerator.generateSnils()).pressEnter();
//        educationSelect.scrollTo().click();
        executeJavaScript("arguments[0].click();", residenceTermInput);
        residenceTermInput.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(true);", residenceTermInput);
        residenceTermInput.shouldBe(visible);
        residenceTermInput.scrollTo().setValue("7");
        executeJavaScript("arguments[0].scrollIntoView(true);", houseTypeInput);
        houseTypeInput.shouldBe(visible);
        houseTypeInput.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", houseTypeSelect);
        houseTypeSelect.shouldBe(visible);
        houseTypeSelect.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", familyMemberCountInput);
        familyMemberCountInput.shouldBe(visible);
        familyMemberCountInput.scrollTo().setValue("1");
        executeJavaScript("arguments[0].scrollIntoView(true);", underageChildCountInput);
        underageChildCountInput.shouldBe(visible);
        underageChildCountInput.scrollTo().setValue("0");
        executeJavaScript("arguments[0].scrollIntoView(true);", dependantCountInput);
        dependantCountInput.shouldBe(visible);
        dependantCountInput.scrollTo().setValue("0");
    }

    public void personalDataTabFillIn_I5() throws InterruptedException {
        Selenide.executeJavaScript("document.body.style.zoom='33%'");  // Масштаб 33%

        educationInput.setValue("Высшее").pressEnter();
        snilsInput.setValue(SnilsGenerator.generateSnils()).pressEnter();
//        educationSelect.scrollTo().click();
        executeJavaScript("arguments[0].click();", residenceTermInput);
        residenceTermInput.shouldBe(visible);
        executeJavaScript("arguments[0].scrollIntoView(true);", residenceTermInput);
        residenceTermInput.shouldBe(visible);
        residenceTermInput.scrollTo().setValue("7");
        executeJavaScript("arguments[0].scrollIntoView(true);", houseTypeInput);
        houseTypeInput.shouldBe(visible);
        houseTypeInput.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", houseTypeSelect);
        houseTypeSelect.shouldBe(visible);
        houseTypeSelect.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", familyMemberCountInput);
        familyMemberCountInput.shouldBe(visible);
        familyMemberCountInput.scrollTo().setValue("3");
        executeJavaScript("arguments[0].scrollIntoView(true);", underageChildCountInput);
        underageChildCountInput.shouldBe(visible);
        underageChildCountInput.scrollTo().setValue("2");
        $x("//input[@id='kid-0']").setValue("10.10.2020").pressEnter();
        Thread.sleep(5000);
        executeJavaScript("arguments[0].scrollIntoView(true);", dependantCountInput);
        dependantCountInput.shouldBe(visible);
        dependantCountInput.scrollTo().setValue("0");
        $x("//input[@id='kid-1']").setValue("10.10.2022").pressEnter();
    }

    public void workTabFillIn() {
        topBlock.scrollTo();
        executeJavaScript("arguments[0].scrollIntoView(true);", workTab);
        workTab.shouldBe(visible);
        executeJavaScript("arguments[0].click();", workTab);
        if (workAccordionIsExpanded.exists()) {
            workAccordion.shouldBe(visible);
            workAccordion.click();
        }

        executeJavaScript("arguments[0].scrollIntoView(true);", organizationTypeInput);
//        organizationTypeInput.shouldBe(clickable);
        organizationTypeInput.setValue("Коммерческая").pressEnter();
//        executeJavaScript("arguments[0].scrollIntoView(true);", organizationTypeSelect);
//        organizationTypeSelect.shouldBe(visible);
//        organizationTypeSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationCategoryInput.shouldBe(visible);
        organizationCategoryInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationCategorySelect.shouldBe(visible);
        organizationCategorySelect.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        opfInput.shouldBe(visible);
        opfInput.setValue("Общество с ограниченной ответственностью").pressEnter();
//        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
//        opfSelect.shouldBe(visible);
//        opfSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationMobilePhoneInput.shouldBe(visible);
        organizationMobilePhoneInput.setValue("9085458685");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employeeCountInput.shouldBe(visible);
        employeeCountInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employeeCountSelect.shouldBe(visible);
        employeeCountSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employmentContractRadioBtn.shouldBe(visible);
        employmentContractRadioBtn.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizatonActivityTypeInput.shouldBe(visible);
        organizatonActivityTypeInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizatonActivityTypeSelect.shouldBe(visible);
        organizatonActivityTypeSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobPositionInput.shouldBe(visible);
        jobPositionInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobPositionSelect.shouldBe(visible);
        jobPositionSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobStartDateInput.shouldBe(visible);
        jobStartDateInput.setValue("01.01.2014").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobRegionInput.shouldBe(visible);
        jobRegionInput.setValue("Коми");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobCityInput.shouldBe(visible);
        jobCityInput.setValue("Сыктывкар").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobStreetInput.shouldBe(visible);
        jobStreetInput.setValue("Панева");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobNumberHomeInput.shouldBe(visible);
        jobNumberHomeInput.setValue("14");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobIndexHomeInput.shouldBe(visible);
        jobIndexHomeInput.setValue("167000");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        totalExperienceYearsInput.shouldBe(visible);
        totalExperienceYearsInput.setValue("9");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        fiveEearExperienceYearsInput.shouldBe(visible);
        fiveEearExperienceYearsInput.setValue("5");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        fiveEearsJobCountInput.shouldBe(visible);
        fiveEearsJobCountInput.setValue("1");

    }

    public void workTabFillIn_I34() {
        topBlock.scrollTo();
        executeJavaScript("arguments[0].scrollIntoView(true);", workTab);
        workTab.shouldBe(visible);
        executeJavaScript("arguments[0].click();", workTab);
        if (workAccordionIsExpanded.exists()) {
            workAccordion.shouldBe(visible);
            workAccordion.click();
        }

        executeJavaScript("arguments[0].scrollIntoView(true);", organizationTypeInput);
//        organizationTypeInput.shouldBe(clickable);
        organizationTypeInput.click();
        $x("//div[text()='IT-компания']").click();
//        executeJavaScript("arguments[0].scrollIntoView(true);", organizationTypeSelect);
//        organizationTypeSelect.shouldBe(visible);
//        organizationTypeSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationCategoryInput.shouldBe(visible);
        organizationCategoryInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationCategorySelect.shouldBe(visible);
        organizationCategorySelect.scrollTo().click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        opfInput.shouldBe(visible);
        opfInput.setValue("Общество с ограниченной ответственностью").pressEnter();
//        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
//        opfSelect.shouldBe(visible);
//        opfSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizationMobilePhoneInput.shouldBe(visible);
        organizationMobilePhoneInput.setValue("9085458685");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employeeCountInput.shouldBe(visible);
        employeeCountInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employeeCountSelect.shouldBe(visible);
        employeeCountSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        employmentContractRadioBtn.shouldBe(visible);
        employmentContractRadioBtn.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizatonActivityTypeInput.shouldBe(visible);
        organizatonActivityTypeInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        organizatonActivityTypeSelect.shouldBe(visible);
        organizatonActivityTypeSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobPositionInput.shouldBe(visible);
        jobPositionInput.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobPositionSelect.shouldBe(visible);
        jobPositionSelect.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobStartDateInput.shouldBe(visible);
        jobStartDateInput.setValue("01.01.2014").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobRegionInput.shouldBe(visible);
        jobRegionInput.setValue("Самарская обл");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobCityInput.shouldBe(visible);
        jobCityInput.setValue("Самара").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobStreetInput.shouldBe(visible);
        jobStreetInput.setValue("Ново-Садовая");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobNumberHomeInput.shouldBe(visible);
        jobNumberHomeInput.setValue("21");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        jobIndexHomeInput.shouldBe(visible);
        jobIndexHomeInput.setValue("443110");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        totalExperienceYearsInput.shouldBe(visible);
        totalExperienceYearsInput.setValue("9");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        fiveEearExperienceYearsInput.shouldBe(visible);
        fiveEearExperienceYearsInput.setValue("5");
        executeJavaScript("arguments[0].scrollIntoView(true);", workAccordionHeader);
        fiveEearsJobCountInput.shouldBe(visible);
        fiveEearsJobCountInput.setValue("1");

    }

    public void revenuesAndExpensesTabFillIn() throws InterruptedException {
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(300);
        revenuesAndExpensesTab.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(1000);
        mainIncomeConfirmDocument.setValue("Справка по форме Банка с печатью").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        avgIncome.click();
        avgIncome.sendKeys("220 000");
        avgIncome.sendKeys(Keys.HOME);
        executeJavaScript("arguments[0].scrollIntoView(true);", addHelp);
        expencesInput.scrollTo().click();
        expencesInput.sendKeys(Keys.HOME);
        expencesInput.sendKeys("100");

    }

    public void revenuesAndExpensesTabFillIn_I34() throws InterruptedException {
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(300);
        revenuesAndExpensesTab.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(1000);
        mainIncomeConfirmDocument.setValue("Справка о доходах ФЛ с печатью").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
//        avgIncome.click();
//        avgIncome.sendKeys("220 000");
//        avgIncome.sendKeys(Keys.HOME);
        executeJavaScript("arguments[0].scrollIntoView(true);", addHelp);
        expencesInput.scrollTo().click();
        expencesInput.sendKeys(Keys.HOME);
        expencesInput.sendKeys("100");

    }

    public void revenuesAndExpensesTabFillIn_I5() throws InterruptedException {
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(300);
        revenuesAndExpensesTab.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(1000);
        mainIncomeConfirmDocument.setValue("Справка по форме Банка с печатью").pressEnter();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        avgIncome.click();
        avgIncome.sendKeys("220 000");
        avgIncome.sendKeys(Keys.HOME);
        executeJavaScript("arguments[0].scrollIntoView(true);", addHelp);
        expencesInput.scrollTo().click();
        expencesInput.sendKeys(Keys.HOME);
        expencesInput.sendKeys("100");
        $x("(//input[@data-id='living-wage'])[1]").setValue("1000");

    }


    public void ndfl2FillInn() {
        Faker faker = new Faker();
        if (is2ndflIncomeConfirm.isDisplayed()) {
            int monthNumber = 0;
            List<String> months = new ArrayList<>();
            months.add("Январь");
            months.add("Февраль");
            months.add("Март");
            months.add("Апрель");
            months.add("Май");
            months.add("Июнь");
            months.add("Июль");
            months.add("Август");
            months.add("Сентябрь");
            months.add("Октябрь");
            months.add("Ноябрь");
            months.add("Декабрь");
            months.add("Январь");
            months.add("Февраль");
            months.add("Март");
            months.add("Апрель");
            months.add("Май");
            months.add("Июнь");
            months.add("Июль");
            months.add("Август");
            months.add("Сентябрь");

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedDate = currentDate.format(formatter);

            ndfl2Btn.click();
            dateOfReference1.scrollTo().setValue(formattedDate).pressEnter();
            referenceNumber1.scrollTo().setValue(String.valueOf(faker.number().numberBetween(1,9999)));
            referenceRowYear1.scrollTo().setValue("2023");
            addNre2NDFLBtn.scrollTo().click();
            dateOfReference2.scrollTo().setValue(formattedDate).pressEnter();
            referenceNumber2.scrollTo().setValue(String.valueOf(faker.number().numberBetween(1,9999)));
            referenceRowYear2.scrollTo().setValue("2024");
            for (int i = 0; i < 9; i++) {
                addBtn1.scrollIntoView(true).click();
            }
            for (int i = 0; i < 7; i++) {
                addBtn2.scrollIntoView(true).click();
            }

            $x("(//input[@id='month'])[1]").click();
            $x("//div[text()='Январь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[2]").click();
            $x("//div[text()='Февраль']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[3]").click();
            $x("//div[text()='Март']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[4]").click();
            $x("//div[text()='Апрель']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[5]").click();
            $x("//div[text()='Май']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[6]").click();
            $x("//div[text()='Июнь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[7]").click();
            $x("//div[text()='Июль']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[8]").click();
            $x("//div[text()='Август']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[9]").click();
            $x("//div[text()='Сентябрь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[10]").click();
            $x("//div[text()='Октябрь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[11]").click();
            $x("//div[text()='Ноябрь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[12]").click();
            $x("//div[text()='Декабрь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[13]").click();
            $x("//div[text()='Январь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[14]").click();
            $x("//div[text()='Февраль']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[15]").click();
            $x("//div[text()='Март']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[16]").click();
            $x("//div[text()='Апрель']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[17]").click();
            $x("//div[text()='Май']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[18]").click();
            $x("//div[text()='Июнь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[19]").click();
            $x("//div[text()='Июль']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[20]").click();
            $x("//div[text()='Август']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[21]").click();
            $x("//div[text()='Сентябрь']").scrollIntoView(true).click();

            $x("(//input[@id='month'])[22]").click();
            $x("//div[text()='Октябрь']").scrollIntoView(true).click();

//////////////////////////// Income Article List

            $x("(//input[@id='incomeItemNumber'])[1]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[2]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[3]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[4]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[5]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[6]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[7]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[8]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[9]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[10]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[11]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[12]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[13]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[14]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[15]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[16]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[17]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[18]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[19]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[20]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[21]").scrollIntoView(true).setValue("2000").pressEnter();
            $x("(//input[@id='incomeItemNumber'])[22]").scrollIntoView(true).setValue("2000").pressEnter();

            //Income sum

            $x("(//input[@id='00-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='01-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='02-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='03-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='04-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='05-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='06-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='07-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='08-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='09-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='010-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='011-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='10-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='11-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='12-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='13-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='14-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='15-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='16-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='17-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='18-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();
            $x("(//input[@id='19-amount'])[1]").scrollIntoView(true).setValue("220000").pressEnter();

            except2NDFLModalBtn.scrollTo().click();
        }

    }

    public void otherInformationTabFillIn() throws InterruptedException {
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        otherInformationTab.click();
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        militaryServiceRelationInput.click();
        Thread.sleep(500);
        if (!militaryServiceRelationSelect.exists()) {
            executeJavaScript("arguments[0].scrollIntoView(true);", header);
            militaryServiceRelationInput.click();

        }
        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        militaryServiceRelationSelect.click();
//        executeJavaScript("arguments[0].scrollIntoView(true);", header);
        Selenide.executeJavaScript("window.scrollBy(0, -500);");
        bankInfoSourcesInput.setValue("Печатные материалы банка (буклеты, листовки и т.д.)").pressEnter();
        Thread.sleep(500);

//        int i = 0;
//        while (!bankInfoSourcesSelect.exists()) {
//            Selenide.executeJavaScript("window.scrollBy(0, -500);");
//            bankInfoSourcesSelect.scrollTo().click();
//            Thread.sleep(500);
//            i++;
//            if (i == 10) break;
//        }
//        bankInfoSourcesSelect.scrollTo().click();
        consentPhotographedToggle.scrollTo().click();
    }

    public void setProductOfferTabFillIn() {
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        productOfferTab.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        employeesInput.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        employeesSelect.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        loanSecurityInput.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        loanSecuritySelect.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        repaymentSchemesInput.click();
        getRepaymentSchemesSelect.click();
    }

    public void setProductOfferTabFillIn_I5() {
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        productOfferTab.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        employeesInput.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        employeesSelect.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        loanSecurityInput.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        loanSecuritySelect.click();
        Selenide.executeJavaScript("window.scrollBy(0, -1000);");
        repaymentSchemesInput.click();
        getRepaymentSchemesSelect.click();
        $x("//input[@id='pay-form']").click();
        $x("//div[text()='Аккредитив до гос. регистрации']").click();
    }

    public void addUks(String dulUks) throws InterruptedException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        addUksBtn.click();
        addUksContinueBtn.click();
        inputPassport.setValue(dulUks);
        searchUksBtn.click();
        searchUksContinueBtn.click();
        searchUksContinueBtn.click();
        pringSoglBtn.click();
        pringBkiBtn.click();
        soglClientWasGottenSwitcher.click();
        soglLoadBtn.uploadFile(file);
        bkiLoadBtn.uploadFile(file);
        addUksPrintFormContinueBtn.click();
        confirmAddUksBtn.click();
        Thread.sleep(500);
        if (confirmAddUksBtn.isDisplayed()) {
            confirmAddUksBtn.click();
        }
    }

    public void setPersonalInfoTabUks() {
        uksTabBtn.click();
        educationInputUks.click();
        educationSelectUks.click();
        snilsUksInput.setValue(SnilsGenerator.generateSnils()).pressEnter();
        relationToBorrowerUksInput.click();
        relationToBorrowerUksSelect.click();
        residenceTermUksInput.setValue("6");
        houseTypeUksInput.click();
        houseTypeUksSelect.click();
        maritalStatusUksInput.click();
        maritalStatusUksSelect.click();
        familyMemberUksCountInput.setValue("1");
        underageChildCountUksCountInput.setValue("0");
        dependantCountUksCountInput.setValue("0");
    }

    public void setWorkTabUks() {
        workTabUks.click();
        employmentUksInput.click();
        employmentUksSelect.click();
        addWorkPlaceUksBtn.click();
        workPlaceUksAkordion.click();
        organizationTypeUksInput.click();
        organizationTypeUksSelect.click();
        organizationCategoryUksInput.click();
        organizationCategoryUksSelect.click();
        opfTypeUksInput.setValue("Общество с ограниченной ответственностью").pressEnter();
        organizationNameUksInput.setValue("ООО \"ПИП\"");
        organizationInnUksInput.setValue("0258951603");
        organizationPhoneUksInput.sendKeys("9094784875");
        employeeCountUksInput.click();
        employeeCountUksSelect.click();
        employmentContractTypeRadioBtnUks.click();
        organizationActivityTypeUksInput.click();
        organizationActivityTypeUksSelect.click();
        jobPositionUksInput.click();
        jobPositionUksSelect.click();
        jobStartDateUksInput.setValue("01.01.2014").pressEnter();
        jobRegionUksInput.setValue("Самарская обл");
        jobCityUksInput.setValue("Самара").pressEnter();
        jobStreetUksInput.setValue("Ново-Садовая");
        jobNumberHomeUksInput.setValue("21");
        jobIndexHomeUksInput.setValue("443110");
        totalExperienceYearsUksInput.setValue("10");
        fiveYearExperienceUksInput.setValue("5");
        fiveYearJobCountUksInput.setValue("1");
    }

    public void setRevenuesAndExpensesTabUks() {
        revenuesAndExpensesTabUks.click();
        mainIncomeConfirmDocumentUksInput.setValue("Справка по форме Банка с печатью").pressEnter();
        avgIncomeUksInput.setValue("189000");
        requiresPaymentUksInput.setValue("100");
    }

    public void setOtherInformationTabUks() {
        otherInformationTabUks.click();
        militaryServiceRelationUksInput.click();
        militaryServiceRelationUksSelect.click();
        bankInfoSourcesUksInput.click();
        bankInfoSourcesUksSelect.click();
        photoConcentUksSwitcher.click();
    }

    public void goToFullProfile() throws InterruptedException {
        requestBtn.scrollTo().click();
//        goToFullProfileBtn.scrollTo().click();
        goToFullAccount.shouldBe(visible, Duration.ofSeconds(640)).click();
        Thread.sleep(3000);
        if (goToFullAccount.isDisplayed()) {
            goToFullAccount.click();
        }
    }

}
