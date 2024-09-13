package basetests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseSelenideTest {

    @BeforeAll
    public static void setUp() {
        // Настройка WebDriver
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().driverVersion("127.0.6533.400").setup();
        Configuration.browser = "chrome";
        Configuration.remote = "http://188.235.130.37:4444/wd/hub";
//        Configuration.browserCapabilities.setCapability("enableVNC", true);  // Включение VNC (по желанию)
//        Configuration.browserCapabilities.setCapability("enableVideo", false);  // Включение записи видео (по желанию)
//        Configuration.startMaximized = true; // Открывать браузер в полноэкранном режиме
        Configuration.timeout = 10000; // Таймаут ожидания (мс)

        // Добавление Allure Selenide Listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }
}
