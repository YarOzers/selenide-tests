package basetests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BaseSelenideTest {

    public static void setupFolderDownload() throws IOException {
        String downloadDir = System.getProperty("user.dir") + "/downloads";
        Path downloadPath = Paths.get(downloadDir);

        if (!Files.exists(downloadPath)) {
            Files.createDirectories(downloadPath);
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir); // Установка директории для загрузок
        prefs.put("plugins.always_open_pdf_externally", true); // Отключает просмотр PDF в браузере
        prefs.put("download.prompt_for_download", false); // Отключение запроса при скачивании файла
        prefs.put("download.directory_upgrade", true); // Обновление директории для загрузок
        prefs.put("safebrowsing.enabled", true); // Включение безопасного просмотра (по желанию)

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions"); // Отключение расширений
        options.addArguments("--disable-popup-blocking"); // Отключение блокировки всплывающих окон
        options.addArguments("--disable-infobars"); // Отключение информационных панелей

        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = downloadDir;
        Configuration.browserSize = "1920x1080";

//        Configuration.headless = true;
//        Configuration.timeout = 600000; // Таймаут ожидания (мс)

    }

    @BeforeAll
    public static void setUp() throws IOException {
        // Настройка WebDriver
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().driverVersion("127.0.6533.400").setup();
        Configuration.browser = "chrome";
        Configuration.remote = "http://188.235.130.37:4444/wd/hub";
//        Configuration.browserCapabilities.setCapability("enableVNC", true);  // Включение VNC (по желанию)
//        Configuration.browserCapabilities.setCapability("enableVideo", false);  // Включение записи видео (по желанию)
//        Configuration.startMaximized = true; // Открывать браузер в полноэкранном режиме
        Configuration.timeout = 40000; // Таймаут ожидания (мс)
        BaseSelenideTest.setupFolderDownload();
        // Добавление Allure Selenide Listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    public void closeDriver(){
        Selenide.closeWebDriver();
    }
}
