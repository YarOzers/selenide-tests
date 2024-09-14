package tests;

import basetests.BaseAppiumTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMobileTest extends BaseAppiumTest {
//    @Test
    public void testButtonClick() {
        // Поиск кнопки по id с помощью нового локатора AppiumBy
        var button = driver.findElement(AppiumBy.id("com.example.android:id/button"));
        button.click();

        // Поиск элемента с результатом и проверка его текста
        var resultText = driver.findElement(AppiumBy.id("com.example.android:id/resultText"));
        Assertions.assertEquals("Expected Result", resultText.getText(), "Текст результата не совпадает!");
    }
}
