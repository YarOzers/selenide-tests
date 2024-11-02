package efr;

import com.codeborne.selenide.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CreateClient {

    private static WebDriver originalDriver;
    private static String originalWindowHandle;
    private static List<String> windowHandles = new ArrayList<>();

    static SelenideConfig adminConfig = new SelenideConfig()
            .timeout(600000)
            .browser("chrome")
            .baseUrl("https://your-application-url.com")
            .headless(false);
    public static ThreadLocal<SelenideDriver> adminDriver = ThreadLocal.withInitial(()->new SelenideDriver(adminConfig));

    Faker faker = new Faker(new Locale("ru"));

    String phoneNumPart1 = String.valueOf(faker.number().numberBetween(1000, 9999));
    String phoneNumPart2 = String.valueOf(faker.number().numberBetween(10000, 99999));

    String phoneNum = phoneNumPart1 + phoneNumPart2;

    private SelenideElement lastName = $x("//input[@data-id='field-group-clientData-field-name-surName']");

    private SelenideElement firsrName = $x("//input[@data-id='field-group-clientData-field-name-firstName']");
    private SelenideElement middleName = $x("//input[@data-id='field-group-clientData-field-name-middleName']");

    private SelenideElement birthDate = $x("//input[@data-id='field-group-generalInformation-field-name-birthDate-input']");

    private SelenideElement sex = $x("//div[contains(text(), 'Мужской')]");

    private SelenideElement nationalities = $x("//a[@data-id='client-nationalities-link-add']");

    private SelenideElement country = $x("(//a[@data-id='link-country'])[1]");

    private SelenideElement birthPlane = $x("//input[@data-id='field-group-client-data-field-name-birthplace']");
    private SelenideElement mailChecktox = $x("//span[contains(text(),'E-mail отсутствует')]");

    private SelenideElement divisionCod = $x("//input[@data-id='field-group-documentsBlock0-field-name-divisionCode']");
    private SelenideElement issuedBy = $x("//input[@data-id='field-group-documentsBlock0-field-name-issuedBy']");
    private SelenideElement issuedDate = $x("//input[@data-id='field-group-generalInformation0-field-name-issuedDate-input']");

    private SelenideElement scanDocumentInput = $x("//input[@data-id='documentsBlock-field-name-scan-0-file-upload']");
    private SelenideElement phoneType = $x("//div[@data-id='field-group-phonesBlock0-field-name-phonetype']");
    private SelenideElement phoneTypeSelect = $x("//div[contains(text(), 'Мобильный')]");

    private SelenideElement forNatificationCheckbox = $x("//span[contains(text(), 'Для оповещений')]");

    private SelenideElement phoneNumber = $x("//input[@data-id='field-group-phonesBlock-field-name-phoneNumber-0']");

    private SelenideElement verifiedBtn = $x("//a[@data-id='field-group-phonesBlock0-field-name-verifiedButton']");

    private SelenideElement verificationCodeInput = $x("//input[@data-id='input-verification-code']");

    private SelenideElement confirmCodeBtn = $x("//div[@class='popup-background']//button[@data-id='button-ok']");

    private SelenideElement registrationAddressInput = $x("(//input[@placeholder='Введите адрес для поиска'])[1]");

    private SelenideElement registrationAddressSelect = $x("(//div[contains(@class, 'search-option')])[1]");
    private SelenideElement registrationAddressApartmentInput = $x("(//input[@data-id='field-group-residential-address0-field-apartment'])[1]");
    private SelenideElement startRegistrationInput = $x("(//input[@data-id='field-registrationAddress-reg-period-start-input'])[1]");
    private SelenideElement residenceCheckBox = $x("//button[@data-id='checkbox-residenceSwitcher0']");
    private SelenideElement matchesWithRegistrationCheckBox = $x("//button[@data-id='checkbox-mailSwitcher0']");
    private SelenideElement codeWorld = $x("//input[@data-id='otherInformation-field-codeWord']");
    private SelenideElement saveBtn = $x("//span[contains(text(), 'Сохранить')]");

    private SelenideElement yesBtn = $x("//div[@class='popup-background']//span[contains(text(),'Да')]");
    //div[@class='switcher-item-text']//b[contains(text(), 'Нет')]
    private SelenideElement switcher1 = $x("(//div[@class='switcher-item-text']//b[contains(text(), 'Нет')])[1]");
    private SelenideElement switcher2 = $x("(//div[@class='switcher-item-text']//b[contains(text(), 'Нет')])[2]");
    private SelenideElement switcher3 = $x("(//div[@class='switcher-item-text']//b[contains(text(), 'Нет')])[3]");
    private SelenideElement switcher4 = $x("(//div[@class='switcher-item-text']//b[contains(text(), 'Нет')])[4]");
    private SelenideElement switcher5 = $x("(//div[@class='switcher-item-text']//b[contains(text(), 'Нет')])[5]");
    private SelenideElement continueFactaBtn = $x("//button[@data-id='continue-button']//span[contains(text(),'Продолжить')]");

    private SelenideElement printBtn = $x("//button[@data-id='buttonPrint-']");
    private SelenideElement signedDocumentsBtn = $x("//button[@data-id='continue-button']//span[contains(text(),'Документы подписаны')]");

    private SelenideElement okBtn = $x("//div[@class='popup-background']//button[@data-id='alertCloseButton']");

    public void fillInCreateClientForm() throws InterruptedException {

        lastName.setValue(faker.name().lastName());
        firsrName.setValue(faker.name().firstName());
        Selenide.executeJavaScript("document.body.style.zoom='33%'");
//        middleName.setValue(faker.name().nameWithMiddle());
        int birthD = faker.number().numberBetween(10, 29);
        int birthM = faker.number().numberBetween(10, 12);
        int birthY = faker.number().numberBetween(1989, 1999);
        String birth = String.valueOf(birthD) + String.valueOf(birthM) + String.valueOf(birthY);
        birthDate.setValue(birth);
        sex.click();
        nationalities.click();
        country.click();
        birthPlane.setValue("410000, Саратовская обл, г Саратов").pressEnter();
        mailChecktox.click();
        divisionCod.setValue("110002");
        issuedBy.setValue("ОТДЕЛЕНИЕ УФМС РОССИИ ПО РЕСПУБЛИКЕ КОМИ В ЭЖВИНСКОМ Р-НЕ Г. СЫКТЫВКАРА");
        String issueDt = String.valueOf(birthD) + String.valueOf(birthM) + String.valueOf(birthY + 20);
        issuedDate.setValue(issueDt);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/test-document.pdf").getFile());
        // Делаем элемент input видимым с помощью JavaScript
        executeJavaScript("arguments[0].style.display = 'block';", scanDocumentInput);
        // Убедитесь, что input для файла доступен и видим
//        scanDocumentInput.shouldBe(visible);
        // Загружаем заранее подготовленный файл
        scanDocumentInput.uploadFile(file);

        phoneType.click();
        phoneTypeSelect.click();
        forNatificationCheckbox.click();

        phoneNumber.setValue("9" + phoneNum);
        verifiedBtn.click();

        // Сохраняем драйвер и идентификатор текущего окна
        originalDriver = WebDriverRunner.getWebDriver();
        originalWindowHandle = originalDriver.getWindowHandle();
        windowHandles.add(originalWindowHandle);

//        String code = getCode();
        String code = getCodeFroSystemJournal();
        verificationCodeInput.setValue(code);
        confirmCodeBtn.click();
        List<String> streets = new ArrayList<>();
        streets.add("Рабочая");
        streets.add("Садовая");
        streets.add("Депутатская");
        streets.add("Бабушкина");
        streets.add("Банбана");
        streets.add("Береговая");
        streets.add("Весенняя");
        streets.add("Ветеранов");
        streets.add("Горького");
        streets.add("Ельная");
        String apartmentNumber = String.valueOf(faker.number().numberBetween(1,200));
        String address = "г Сыктывкар, ул " + streets.get(faker.number().numberBetween(0,streets.size()-1)) + ", д " + apartmentNumber;
        registrationAddressInput.sendKeys(address);
        Thread.sleep(2500);
        registrationAddressSelect.click();
        registrationAddressApartmentInput.setValue(String.valueOf(faker.number().numberBetween(1,300)));
//
//        registrationAddressApartmentInput.setValue(String.valueOf(faker.number().numberBetween(1,500)));
        startRegistrationInput.setValue(issueDt);
        residenceCheckBox.click();
        matchesWithRegistrationCheckBox.click();
        codeWorld.setValue("СЛОВО");
        saveBtn.click();
        yesBtn.shouldBe(visible, Duration.ofSeconds(80)).click();
        switcher1.click();
        switcher2.click();
        switcher3.click();
        switcher4.click();
        switcher5.click();
        continueFactaBtn.click();
        printBtn.click();
        signedDocumentsBtn.click();
        okBtn.click();
        okBtn.click();
    }

    public void verifySms() throws InterruptedException {
        if($x("//p[contains(text(), 'Потребуется подтвердить доступ с помощью СМС-кода, который клиент получит на верифицированный номер мобильного телефона, или через контролирующего работника')]").isDisplayed()) {
            $x("//p[contains(text(), 'Потребуется подтвердить доступ с помощью СМС-кода, который клиент получит на верифицированный номер мобильного телефона, или через контролирующего работника')]").click();
            $x("//button[@data-id='start-btn' and contains(text(),'Продолжить')]").click();
            $x("//p[contains(text(),'Подтвердить доступ с помощью СМС-кода, который клиент получит на верифицированный номер мобильного телефона в составе СМС-сообщения')]").click();
            $x("//button[@data-id='start-btn' and contains(text(),'Продолжить')]").click();

            String code = getCodeFroSystemJournal();

            $x("//input[@id='undefined-text-input']").setValue(code).pressEnter();
            SelenideElement submitBtn = $x("//button[@data-id='start-btn' and contains(text(),'Подтвердить')]");
            int i = 0;
            do {
                i++;
                submitBtn.click();
                System.out.println(i);
                Thread.sleep(1000);
            } while (submitBtn.isDisplayed());
        }
    }

    private String getCode() throws InterruptedException {
        SystemJournal systemJournal = new SystemJournal();
        String code = "";

        // Открываем новое окно для админа
        adminDriver.get().open("https://test03.rshb.ru/efr/");
        adminDriver.get().$("span.Select-arrow-zone").click();
        adminDriver.get().$x("//div[text()='GO']").click();
        adminDriver.get().$("input.input[type='text'][data-id='field-login']").setValue(System.getenv("EFR_ADMIN_USER_LOGIN"));
        adminDriver.get().$("input.input[type='password'][data-id='field-password']").setValue(System.getenv("EFR_ADMIN_USER_PASSWORD"));
        adminDriver.get().$("button[data-id='button-save']").click();
        adminDriver.get().$x("(//a[@aria-haspopup='menu'])[2]").click();
        Thread.sleep(2000);
        if(!adminDriver.get().$x("//span[contains(text(), 'Системный журнал')]").isDisplayed()){
            adminDriver.get().$x("(//a[@aria-haspopup='menu'])[2]").click();
        }
        adminDriver.get().$x("//span[contains(text(), 'Системный журнал')]").click();
        adminDriver.get().$x("//div[@id='addFilterConditionButton']").click();///
        adminDriver.get().$x("//div[@id='filterContainer']//input").setValue("*" + phoneNumPart2);
        adminDriver.get().$x("//div[@id='sjSearchButton']").click();
        adminDriver.get().$x("(//td[contains(text(), 'SendSMS')])[1]").click();
        adminDriver.get().$x("//div[@title='Скопировать в буфер обмена']").click();
        // Получаем скопированное сообщение (например, через вызов буфера обмена)
        String copiedMessage = getCopiedTextFromClipboard();

        // Регулярное выражение для поиска кода
        Pattern pattern = Pattern.compile("сообщите код сотруднику Банка - (\\d{4})");
        Matcher matcher = pattern.matcher(copiedMessage);

        if (matcher.find()) {
            // Извлекаем код
            code = matcher.group(1);
            System.out.println("Извлеченный код: " + code);
        } else {
            System.out.println("Код не найден");
        }

        adminDriver.get().close();
        return code;
    }

    private String getCodeFroSystemJournal() throws InterruptedException {
        SystemJournal systemJournal = new SystemJournal();
        String code = "";

        // Открываем новое окно для админа
        adminDriver.get().open("https://test03.rshb.ru/b1/ib6/wf2/iclientadm/panels/journal/systemjournalpanel");

        adminDriver.get().$x("//select[@id='selectorForMethod']").click();
        adminDriver.get().$x("//*[contains(text(),'Логин/Пароль')]").click();
        adminDriver.get().$x("//input[@id='textfield']").setValue("EFR_SJ_LOGIN");
        adminDriver.get().$x("//input[@id='passwordfield']").setValue("EFR_SJ_PASSWORD");
        adminDriver.get().$x("//div[@class='ib-button-text']").click();

        adminDriver.get().$x("//div[@id='addFilterConditionButton']").click();///
        adminDriver.get().$x("//div[@id='filterContainer']//input").setValue("*" + phoneNumPart2);
        adminDriver.get().$x("//div[@id='sjSearchButton']").click();
        adminDriver.get().$x("(//td[contains(text(), 'SendSMS')])[1]").click();
        adminDriver.get().$x("//div[@title='Скопировать в буфер обмена']").click();
        // Получаем скопированное сообщение (например, через вызов буфера обмена)
        String copiedMessage = getCopiedTextFromClipboard();

        // Регулярное выражение для поиска кода
        Pattern pattern = Pattern.compile("сообщите код сотруднику Банка - (\\d{4})");
        Matcher matcher = pattern.matcher(copiedMessage);

        if (matcher.find()) {
            // Извлекаем код
            code = matcher.group(1);
            System.out.println("Извлеченный код: " + code);
        } else {
            System.out.println("Код не найден");
        }

        adminDriver.get().close();
        return code;
    }

    public static String getCopiedTextFromClipboard() {
        // Получаем системный буфер обмена
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
            // Извлекаем текст из буфера обмена, если там есть текст
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            // Обработка возможных исключений
            System.err.println("Не удалось получить данные из буфера обмена: " + e.getMessage());
            return null;
        }
    }


}
