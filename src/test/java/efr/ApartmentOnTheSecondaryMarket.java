package efr;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class ApartmentOnTheSecondaryMarket {

    private SelenideElement costSupposedInput = $x("//input[@id='cost-supposed']");

    private SelenideElement costAssessedInput = $x("//input[@id='cost-assessed']");

    private SelenideElement ownerTypeInput = $x("//input[@id='owner-type']");
    private SelenideElement ownerTypeSelect = $x("//div[@class='bx--list-box__menu-item']//div[text()='Заемщик']");

    private SelenideElement fullAddressInput = $x("//input[@id='fullAddress']");

    private SelenideElement appraiserNameInput = $x("//input[@id='appraiser-name']");

    private SelenideElement evaluationReportNumberInput = $x("//input[@id='evaluation-report-number']");

    private SelenideElement evaluationReportDateInput = $x("//input[@id='evaluation-report-date']");

    private SelenideElement costDkpInput = $x("//input[@id='cost-dkp']");

    private SelenideElement purposeInput = $x("//input[@id='purpose']");

    private SelenideElement purposeSelect = $x("//div[@class='bx--list-box__menu-item']/div[text()='Жилое']");

    private SelenideElement cadastralNumberInput = $x("//input[@id='cadastral-number']");

    private SelenideElement totalAreaInput = $x("//input[@id='total-area']");

    private SelenideElement livingAreaInput = $x("//input[@id='living-area']");

    private SelenideElement roomsCountInput = $x("//input[@id='rooms-count']");

    private SelenideElement roomAreaInput = $x("//input[@id='room-area-0']");

    private SelenideElement floorInput = $x("//input[@id='floor']");
    private SelenideElement floorsTotalInput = $x("//input[@id='floors-total']");

    private SelenideElement wallMaterialInput = $x("//input[@id='wall-material']");
    private SelenideElement wallMaterialSelect = $x("//div[text()='Кирпич']");

    private SelenideElement floorMaterialInput = $x("//input[@id='floor-material']");
    private SelenideElement floorMaterialSelect = $x("//div[text()='Бетон']");

    private SelenideElement foundationMaterialInput = $x("//input[@id='foundation-material']");

    private SelenideElement foundationMaterialSelect = $x("//div[text()='Бетон']");

    private SelenideElement saveDataBtn = $x("//button[text()='Сохранить данные']");

    private SelenideElement bailSidenavBtn = $x("//span[contains(text(), 'Залог')]/parent::div");
    private SelenideElement addBailBtn = $x("//button[text()='Добавить']");

    private SelenideElement addBailSeelctBtn = $x("//div[@class='elem']/div[text()='Объект залога']");


    public void fillInBailForm() throws InterruptedException {

        bailSidenavBtn.click();
        Selenide.executeJavaScript("document.body.style.zoom='100%'");
        addBailBtn.click();
        addBailSeelctBtn.click();
        Thread.sleep(3000);
        if(!$x("//input[@id='cost-supposed']").isDisplayed()){
            addBailBtn.click();
            addBailSeelctBtn.click();
        }


        Faker faker = new Faker();
        int houseNumber = faker.number().numberBetween(1,500);
        int apartmentNumber = faker.number().numberBetween(1,500);
        String address = "г Саратов, ул Прессовая, д" + houseNumber + ", кв " + apartmentNumber;
        if (Objects.requireNonNull($x("//input[@id='flat']").getAttribute("value")).isEmpty()){
            $x("//input[@id='flat']").setValue(String.valueOf(faker.number().numberBetween(1,400))).pressEnter();

        }
        costSupposedInput.setValue("6000000");
        costAssessedInput.setValue("6000000");
        ownerTypeInput.click();
        ownerTypeSelect.click();
        fullAddressInput.setValue(address).pressEnter();
        appraiserNameInput.setValue("ООО \"Оценка\"");
        evaluationReportNumberInput.setValue(String.valueOf(faker.number().numberBetween(100000,99999999)));

        // Получение текущей даты
        LocalDate currentDate = LocalDate.now();
        // Задание формата
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Форматирование даты
        String formattedDate = currentDate.format(formatter);
        Selenide.executeJavaScript("document.body.style.zoom='33%'");
        evaluationReportDateInput.setValue(formattedDate).pressEnter();
        costDkpInput.setValue("6000000");
        purposeInput.click();
        purposeSelect.click();
        cadastralNumberInput.setValue(CadastralNumberGenerator.generateCadastralNumber()).pressEnter();
        int areaApartment = faker.number().numberBetween(50, 120);
        totalAreaInput.setValue(String.valueOf(areaApartment));
        livingAreaInput.setValue(String.valueOf(areaApartment));
        roomsCountInput.setValue("1");
        roomAreaInput.setValue(String.valueOf(areaApartment));
        floorInput.setValue(String.valueOf(faker.number().numberBetween(1,10)));
        floorsTotalInput.setValue(String.valueOf(faker.number().numberBetween(10,20)));
        wallMaterialInput.click();
        wallMaterialSelect.click();
        floorMaterialInput.click();
        floorMaterialSelect.click();
        foundationMaterialInput.click();
        foundationMaterialSelect.click();
        saveDataBtn.click();
    }
}
