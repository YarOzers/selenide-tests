package efr;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;

public class CreditApplication {
    private SelenideElement estimatedFormPaymentInput = $x("//input[@id='estimated-form-payment']");

    private SelenideElement estimatedFormPaymentSelect_I34 = $x("//div[contains(text(),'Перевод на счет после регистрации')]");
    private SelenideElement estimatedFormPaymentSelect = $x("//div[contains(text(),'Перевод на счет до регистрации')]");
    private SelenideElement estimatedFormPaymentSelect_I5 = $x("//div[contains(text(),'Аккредитив до гос. регистрации')]");

    private SelenideElement creditPaymentDayInput = $x("//input[@id='creditPaymentDay']");
    private SelenideElement creditPaymentDaySelect = $x("//div[contains(text(), '5 числа месяца')]");

    private SelenideElement personalInsuranceSwitcher = $x("(//div[@id='personalInsurance']/parent::div//span)[3]");
    private SelenideElement insuranceTabBtn = $x("//button[@id='insurance-tab']");
    private SelenideElement accordionWrap1 = $x("(//div[@class='bx--accordion__wrap'])[1]");
    private SelenideElement accordionWrap2 = $x("(//div[@class='bx--accordion__wrap'])[2]");
    private SelenideElement insuranceProgramInput1 = $x("(//input[@id='insurance-program'])[1]");
    private SelenideElement insuranceProgramInput2 = $x("(//input[@id='insurance-program'])[2]");
    private SelenideElement insuranceProgramSelect = $x("//div[text()='ПКС №6 (страхование от НС заёмщиков/созаёмщиков ипотечных кредитов)  (колл.)']");

    private SelenideElement insurancePayInput1 = $x("(//input[@id='insurance-pay'])[1]");
    private SelenideElement insurancePayInput2 = $x("(//input[@id='insurance-pay'])[2]");
    private SelenideElement bankRemunerationInput1 = $x("(//input[@id='bank-remuneration'])[1]");
    private SelenideElement bankRemunerationInput2 = $x("(//input[@id='bank-remuneration'])[2]");

    private SelenideElement insurancePremiumInput1 = $x("(//input[@id='insurance-premium'])[1]");
    private SelenideElement insurancePremiumInput2 = $x("(//input[@id='insurance-premium'])[2]");


    private SelenideElement documentsTab = $x("//button[@id='documents-tab']");

    private SelenideElement loadDocumentsBtn = $x("(//input[@type='file'])[1]");
    private SelenideElement loadAdditionalServicesBtn = $x("(//input[@type='file'])[17]");
    private SelenideElement borrowerSidenavBtn = $x("//div/span[contains(text(), 'Заемщик')]");

    private SelenideElement borrowerOtherInformationTab = $x("//button//span[contains(text(), 'Иная информация')]");

    private SelenideElement printAdditionalServicesBtn = $x("//button[contains(text(),'Дополнительные услуги')]");

    private SelenideElement participantsSidenavBtn = $x("//div/span[contains(text(),'Участники кредитной сделки')]");
    private SelenideElement editParticipant = $x("//span[contains(text(),'Редактировать')]/parent::button");
    private SelenideElement otherInformationTabParticipant = $x("//div/span[contains(text(),'Иная информация')]");

    private SelenideElement printAdditionalServicesParticipantBtn = $x("//button[contains(text(),'Дополнительные услуги')]");

    private SelenideElement saveParticipantDataBtn = $x("//button[contains(text(),'Сохранить данные')]");

    private SelenideElement bailSidenavBtn = $x("//span[contains(text(), 'Залог')]/parent::div");
    private SelenideElement addBailBtn = $x("//button[text()='Добавить']");

    private SelenideElement addBailSeelctBtn = $x("//div[@class='elem']/div[text()='Объект залога']");


    public void fillInCreditApplicationForm() throws InterruptedException {
        String mortgageNumber = $x("(//a[@class='bx--link'])[2]").getText();
        System.out.println("Номер заявки :" + mortgageNumber);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        estimatedFormPaymentInput.click();
        estimatedFormPaymentSelect.click();
        creditPaymentDayInput.click();
        creditPaymentDaySelect.click();
        personalInsuranceSwitcher.click();
        insuranceTabBtn.click();
        accordionWrap1.click();
        insuranceProgramInput1.click();
        insuranceProgramSelect.click();
        insurancePayInput1.sendKeys(Keys.HOME);
        insurancePayInput1.sendKeys("10");
        bankRemunerationInput1.sendKeys(Keys.HOME);
        bankRemunerationInput1.sendKeys("10");
        insurancePremiumInput1.sendKeys(Keys.HOME);
        insurancePremiumInput1.sendKeys("10");
        accordionWrap2.click();
        insuranceProgramInput2.click();
        insuranceProgramSelect.click();
        insurancePayInput2.sendKeys(Keys.HOME);
        insurancePayInput2.sendKeys("10");
        bankRemunerationInput2.sendKeys(Keys.HOME);
        bankRemunerationInput2.sendKeys("10");
        insurancePremiumInput2.sendKeys(Keys.HOME);
        insurancePremiumInput2.sendKeys("10");
        documentsTab.click();
        loadDocumentsBtn.uploadFile(file);
        loadAdditionalServicesBtn.uploadFile(file);
        borrowerSidenavBtn.click();
        borrowerOtherInformationTab.click();
        printAdditionalServicesBtn.click();
        participantsSidenavBtn.click();
        editParticipant.click();
        otherInformationTabParticipant.click();
        printAdditionalServicesParticipantBtn.click();
        saveParticipantDataBtn.click();
        bailSidenavBtn.click();
        Selenide.executeJavaScript("document.body.style.zoom='100%'");
        addBailBtn.click();
        addBailSeelctBtn.click();
        Thread.sleep(3000);
        if(!$x("//input[@id='cost-supposed']").isDisplayed()){
            addBailBtn.click();
            addBailSeelctBtn.click();
        }
    }

    public void fillInCreditApplicationForm_I10_1uks() throws InterruptedException {
        String mortgageNumber = $x("(//a[@class='bx--link'])[2]").getText();
        System.out.println("Номер заявки :" + mortgageNumber);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        estimatedFormPaymentInput.click();
        estimatedFormPaymentSelect.click();
        creditPaymentDayInput.click();
        creditPaymentDaySelect.click();
        personalInsuranceSwitcher.click();
        insuranceTabBtn.click();
        accordionWrap1.click();
        insuranceProgramInput1.click();
        insuranceProgramSelect.click();
        insurancePayInput1.sendKeys(Keys.HOME);
        insurancePayInput1.sendKeys("10");
        bankRemunerationInput1.sendKeys(Keys.HOME);
        bankRemunerationInput1.sendKeys("10");
        insurancePremiumInput1.sendKeys(Keys.HOME);
        insurancePremiumInput1.sendKeys("10");
        accordionWrap2.click();
        insuranceProgramInput2.click();
        insuranceProgramSelect.click();
        insurancePayInput2.sendKeys(Keys.HOME);
        insurancePayInput2.sendKeys("10");
        bankRemunerationInput2.sendKeys(Keys.HOME);
        bankRemunerationInput2.sendKeys("10");
        insurancePremiumInput2.sendKeys(Keys.HOME);
        insurancePremiumInput2.sendKeys("10");
        documentsTab.click();
        loadDocumentsBtn.uploadFile(file);
        loadAdditionalServicesBtn.uploadFile(file);
        borrowerSidenavBtn.click();
        borrowerOtherInformationTab.click();
        printAdditionalServicesBtn.click();
        participantsSidenavBtn.click();
        editParticipant.click();
        otherInformationTabParticipant.click();
        printAdditionalServicesParticipantBtn.click();
        saveParticipantDataBtn.click();
        bailSidenavBtn.click();
        Selenide.executeJavaScript("document.body.style.zoom='100%'");
        addBailBtn.click();
        addBailSeelctBtn.click();
        Thread.sleep(3000);
        if(!$x("//input[@id='cost-supposed']").isDisplayed()){
            addBailBtn.click();
            addBailSeelctBtn.click();
        }
    }

    public void fillInCreditApplicationForm_I34() throws InterruptedException {
        String mortgageNumber = $x("(//a[@class='bx--link'])[2]").getText();
        System.out.println("Номер заявки :" + mortgageNumber);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        estimatedFormPaymentInput.click();
        estimatedFormPaymentSelect_I34.click();
        creditPaymentDayInput.click();
        creditPaymentDaySelect.click();
        documentsTab.click();
        loadDocumentsBtn.uploadFile(file);
        loadAdditionalServicesBtn.uploadFile(file);
        borrowerSidenavBtn.click();
        borrowerOtherInformationTab.click();
        printAdditionalServicesBtn.click();
        participantsSidenavBtn.click();
        editParticipant.click();
        otherInformationTabParticipant.click();
        printAdditionalServicesParticipantBtn.click();
        saveParticipantDataBtn.click();
        bailSidenavBtn.click();
        Selenide.executeJavaScript("document.body.style.zoom='100%'");

    }

    public void fillInCreditApplicationForm_I5() throws InterruptedException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        estimatedFormPaymentInput.click();
        estimatedFormPaymentSelect_I5.click();
        creditPaymentDayInput.click();
        creditPaymentDaySelect.click();
        personalInsuranceSwitcher.click();
        insuranceTabBtn.click();
        accordionWrap1.click();
        insuranceProgramInput1.click();
        insuranceProgramSelect.click();
        insurancePayInput1.sendKeys(Keys.HOME);
        insurancePayInput1.sendKeys("10");
        bankRemunerationInput1.sendKeys(Keys.HOME);
        bankRemunerationInput1.sendKeys("10");
        insurancePremiumInput1.sendKeys(Keys.HOME);
        insurancePremiumInput1.sendKeys("10");
        accordionWrap2.click();
        insuranceProgramInput2.click();
        insuranceProgramSelect.click();
        insurancePayInput2.sendKeys(Keys.HOME);
        insurancePayInput2.sendKeys("10");
        bankRemunerationInput2.sendKeys(Keys.HOME);
        bankRemunerationInput2.sendKeys("10");
        insurancePremiumInput2.sendKeys(Keys.HOME);
        insurancePremiumInput2.sendKeys("10");
        documentsTab.click();
        loadDocumentsBtn.uploadFile(file);
        loadAdditionalServicesBtn.uploadFile(file);
        borrowerSidenavBtn.click();
        borrowerOtherInformationTab.click();
        printAdditionalServicesBtn.click();
        participantsSidenavBtn.click();
        editParticipant.click();
        otherInformationTabParticipant.click();
        printAdditionalServicesParticipantBtn.click();
        saveParticipantDataBtn.click();
        bailSidenavBtn.click();
        Selenide.executeJavaScript("document.body.style.zoom='100%'");
        addBailBtn.click();
        addBailSeelctBtn.click();
        Thread.sleep(3000);
        if(!$x("//input[@id='cost-supposed']").isDisplayed()){
            addBailBtn.click();
            addBailSeelctBtn.click();
        }
    }

    public void SendForInspection() throws InterruptedException {
        $x("//button[text()='Отправить на проверку']").scrollIntoView(true).click();
        Thread.sleep(5000);
        if($x("//button[text()='Отправить на проверку']").isDisplayed()){
            $x("//button[text()='Отправить на проверку']").scrollIntoView(true).click();
        }
    }

}
