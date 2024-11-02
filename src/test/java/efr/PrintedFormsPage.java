package efr;

import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PrintedFormsPage {

    private SelenideElement printPersonalDataButton = $x("//div[contains(@class, 'document-handler--default')]" +
            "[.//div[@class='title' and contains(text(), 'Согласие на обработку персональных данных')]]" +
            "//button[starts-with(@data-id, 'withdrawal-of-funds_print-button')]");

    private SelenideElement dataUploadButton = $x("//div[contains(@class, 'document-handler--default')]" +
            "[.//div[@class='title' and contains(text(), 'Согласие на обработку персональных данных')]]" +
            "//button[starts-with(@data-id, 'withdrawal-of-funds_upload-button')]");

    private SelenideElement printReportFromBkiButton = $x("//div[contains(@class, 'document-handler--default')]" +
            "[.//div[@class='title' and contains(text(), 'Заявление на предоставление ипотечного кредита')]]" +
            "//button[starts-with(@data-id, 'withdrawal-of-funds_print-button')]");

    private SelenideElement reportFromBkiUploadButton = $x("//div[contains(@class, 'document-handler--default')]" +
            "[.//div[@class='title' and contains(text(), 'Заявление на предоставление ипотечного кредита')]]" +
            "//button[starts-with(@data-id, 'withdrawal-of-funds_upload-button')]");

    private SelenideElement fileDataInput = $x("//*[@id=\"root\"]/div/div/div[3]/section/div/div[1]/div[2]/div/div/div/div[1]/div/div[2]/div[2]/input");

    private SelenideElement fileBkiInput = $x("//*[@id=\"root\"]/div/div/div[3]/section/div/div[1]/div[2]/div/div/div/div[2]/div/div[2]/div[2]/input");

    private SelenideElement continueButton = $x("//button[text()='Следующий этап']");


    /**
     * Метод для загрузки заранее подготовленного файла.
     *
     * @param filePath относительный путь к файлу внутри папки ресурсов.
     * @throws Exception если файл не найден.
     */
    public void uploadDataFile(String filePath) throws Exception {
        // Получаем файл из classpath
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        if (!file.exists()) {
            throw new Exception("Предварительно подготовленный файл не найден: " + filePath);
        }

        System.out.println("Файл для загрузки: " + file.getAbsolutePath());

        // Делаем элемент input видимым с помощью JavaScript
        executeJavaScript("arguments[0].style.display = 'block';", fileDataInput);

        // Нажмите на кнопку для загрузки файла
//        dataUploadButton.click();

        // Убедитесь, что input для файла доступен и видим
        fileDataInput.shouldBe(visible);

        // Загружаем заранее подготовленный файл
        fileDataInput.uploadFile(file);

        System.out.println("Файл загружен: " + file.getAbsolutePath());
    }

    /**
     * Метод для загрузки заранее подготовленного файла.
     *
     * @param filePath относительный путь к файлу внутри папки ресурсов.
     * @throws Exception если файл не найден.
     */
    public void uploadBkiFile(String filePath) throws Exception {
        // Получаем файл из classpath
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        if (!file.exists()) {
            throw new Exception("Предварительно подготовленный файл не найден: " + filePath);
        }

        System.out.println("Файл для загрузки: " + file.getAbsolutePath());

        // Делаем элемент input видимым с помощью JavaScript
        executeJavaScript("arguments[0].style.display = 'block';", fileBkiInput);

        // Нажмите на кнопку для загрузки файла
//        printReportFromBkiButton.click();

        // Убедитесь, что input для файла доступен и видим
        fileDataInput.shouldBe(visible);

        // Загружаем заранее подготовленный файл
        fileBkiInput.uploadFile(file);

        System.out.println("Файл загружен: " + file.getAbsolutePath());
    }

    /**
     * Метод для загрузки файла согласия на обработку персональных данных.
     *
     * @throws Exception если файл не найден или загрузка не удалась.
     */
    public void uploadPersonalData() throws Exception {
        // Нажмите на кнопку печати (если требуется)
         printPersonalDataButton.shouldBe(visible, Duration.ofSeconds(120)).click();

        // Загружаем заранее подготовленный PDF файл
        uploadDataFile("files/test-document.pdf");
    }

    /**
     * Метод для загрузки файла заявления на предоставление ипотечного кредита.
     *
     * @throws Exception если файл не найден или загрузка не удалась.
     */
    public void uploadBki() throws Exception {
        // Нажмите на кнопку печати отчёта из БКИ (если требуется)
         printReportFromBkiButton.click();

        // Загружаем заранее подготовленный PDF файл
        uploadBkiFile("files/test-document.pdf");
    }

    /**
     * Метод для перехода на следующий этап.
     */
    public void continueProcess(){
        continueButton.click();
    }

}


