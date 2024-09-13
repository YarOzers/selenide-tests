package basetests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseAppiumTest {
    protected static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        // Настройки для подключения к Android устройству или эмулятору
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("13");
        options.setDeviceName("emulator-5554");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
//        options.setApp("C:\\androidApp\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        options.setAdbExecTimeout(Duration.ofSeconds(60)); // увеличиваем таймаут до 60 секунд

        // Подключение к серверу Appium
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }


    @AfterAll
    public static void tearDown() {
        // Завершение работы драйвера
        if (driver != null) {
            driver.quit();
        }
    }
}
