package efr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditCalculator {


    //Кредитный продукт селект
    private SelenideElement creditProductInput = $("#credit-product");

    //Выбор кредитного продукта
    private SelenideElement creditProductSelect_I10 = $x("//div[contains(text(),'Ипотека МСХ')]");
    private SelenideElement creditProductSelect_I5 = $x("//div[contains(text(),'Ипотечный кредит для граждан РФ, имеющих детей')]");
    private SelenideElement creditProductSelect_I34 = $x("//div[contains(text(),'Ипотека для IT-специалистов')]");

    //Цель кредита селект
    private SelenideElement creditPurposeInput = $("#credit-purpose");


    //Выбор цели кредита
    private SelenideElement creditPurposeSelect_I10 = $x("//div[contains(text(), '9525 Приобретение ДКП квартиры в многоквартирном доме высотой не более 5 этажей в опорном населенном пункте  у ИП')]");
    private SelenideElement creditPurposeSelect_I5 = $x("//div[contains(text(), '9532 Приобр. квартиры у ЮЛ/ИП, являющихся первыми собственниками, по ДКП')]");
    private SelenideElement creditPurposeSelect_I34 = $x("//div[contains(text(), '9532 Приобр. квартиры у ЮЛ/ИП, являющихся первыми собственниками, по ДКП')]");


    //Инпут регион
    private SelenideElement regionInput = $("#region");

    //Выбор региона селект
    private SelenideElement regionSelect = $x("//div[contains(text(), 'Респ Коми')]/parent::li");

    private SelenideElement propertyTypeInput = $("#propertyType");
    private SelenideElement propertyTypeSelect = $x("//div[contains(text(),'Квартира на вторичном рынке')]");

    private SelenideElement developerInput = $("#developer");

    private SelenideElement discountSchemesInput = $x("//input[@id='discount schemes']");
    private SelenideElement discountSchemesSelect = $x("//div[text()='Схема 302 \"А101\"(КВ 9,10%)']");

    private SelenideElement developerSelect = $("ul.options li.option:nth-child(3) div");

    private SelenideElement creditSum = $("#price");

    private SelenideElement downPayment = $("#downpayment");

    private SelenideElement creditTerm = $("#creditTerm");

    private SelenideElement totalIncome = $("#totalIncome");

    private SelenideElement organizationNameInput = $("input[title='Введите название организации']");

    private SelenideElement organizationNameSelect = $("ul.options li.option:nth-child(1) div");

    private SelenideElement maritalStatus = $("#downshift-4-toggle-button");

    private SelenideElement maritalSelect = $("#downshift-4-item-0 > div");

    private SelenideElement payFormInput = $("#pay-form");

    private SelenideElement payFormSelect = $("#downshift-5-item-5 > div");
    private SelenideElement payFormSelect_I5 = $x("//div[text()='Аккредитив до гос. регистрации']");
    private SelenideElement payFormSelect_I34 = $x("//div[text()='Перевод на счет после регистрации']");

    private SelenideElement calculateBtn = $x("//button[text()='Рассчитать']");

    private SelenideElement makeAnApplicationBtn = $x("//button[contains(text(), 'Оформить заявку')]");

    public void I10() throws InterruptedException {
        creditProductInput.click();
        creditProductSelect_I10.click();
        creditPurposeInput.click();
        creditPurposeSelect_I10.click();
        regionInput.click();
        regionInput.setValue("Коми");
        regionSelect.click();
        propertyTypeInput.click();
        propertyTypeSelect.click();
        developerInput.setValue("строй");
        developerSelect.click();
        creditSum.setValue("6 000 000").pressEnter();
        Thread.sleep(500);
        downPayment.click();
        downPayment.sendKeys(Keys.HOME);
        downPayment.sendKeys("300000");

        creditTerm.setValue("300");
        totalIncome.setValue("250 000");
        organizationNameInput.setValue("РО");
        organizationNameSelect.click();
        maritalStatus.click();
        maritalSelect.scrollTo().click();
        if (payFormInput.exists()) {
            payFormInput.click();
            payFormSelect.click();
        }
        calculateBtn.scrollTo().click();
        makeAnApplicationBtn.shouldBe(Condition.visible, Duration.ofSeconds(640)).click();

    }

    public void I5() throws InterruptedException {
        creditProductInput.click();
        creditProductSelect_I5.click();
        creditPurposeInput.click();
        creditPurposeSelect_I5.click();
        regionInput.click();
        regionInput.setValue("Коми");
        regionSelect.click();
        propertyTypeInput.click();
        propertyTypeSelect.click();
        developerInput.setValue("строй");
        developerSelect.click();
        creditSum.setValue("6 000 000").pressEnter();
        Thread.sleep(500);
        downPayment.click();
        downPayment.sendKeys(Keys.HOME);
        downPayment.sendKeys("300000");

        creditTerm.setValue("300");
        totalIncome.setValue("250 000");
        organizationNameInput.setValue("РО");
        organizationNameSelect.click();
        maritalStatus.click();
        maritalSelect.scrollTo().click();
        if (payFormInput.isDisplayed()) {
            payFormInput.click();
            payFormSelect_I5.click();
        }
        calculateBtn.scrollTo().click();
        makeAnApplicationBtn.shouldBe(Condition.visible, Duration.ofSeconds(640)).click();

    }

    public void I34() throws InterruptedException {
        creditProductInput.click();
        creditProductSelect_I34.click();
        creditPurposeInput.click();
        creditPurposeSelect_I34.click();
        regionInput.click();
        regionInput.setValue("Коми");
        regionSelect.click();
        propertyTypeInput.click();
        propertyTypeSelect.click();
        developerInput.setValue("ООО CЗ \"А101\"").pressEnter();
//        developerSelect.click();
        discountSchemesInput.click();
        discountSchemesSelect.click();
        creditSum.setValue("6 000 000").pressEnter();
        Thread.sleep(500);
        downPayment.click();
        downPayment.sendKeys(Keys.HOME);
        downPayment.sendKeys("300000");

        creditTerm.setValue("300");
        totalIncome.setValue("250 000");
        organizationNameInput.setValue("ООО \"СМ\"").pressEnter();
        $x("//input[contains(@id,'organization-inn')]").setValue("6316126113").pressEnter();
//        organizationNameSelect.click();
        maritalStatus.click();
        maritalSelect.scrollTo().click();
        if (payFormInput.isDisplayed()) {
            payFormInput.click();
            payFormSelect_I34.click();
        }
        calculateBtn.scrollTo().click();
        makeAnApplicationBtn.shouldBe(Condition.visible, Duration.ofSeconds(640)).click();

    }
}



