package org.example.basetests;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        // Настройка WebDriver
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.startMaximized = true; // Открывать браузер в полноэкранном режиме
        Configuration.timeout = 10000; // Таймаут ожидания (мс)
    }
}
