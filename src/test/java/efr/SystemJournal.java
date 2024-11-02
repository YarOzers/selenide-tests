package efr;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$x;

public class SystemJournal {
    private SelenideElement addFilterConditionBtn = $x("//div[@id='addFilterConditionButton']");
    private SelenideElement filterInput = $x("//div[@id='filterContainer']//input");
    private SelenideElement searchBtn = $x("//div[@id='sjSearchButton']");

    private SelenideElement tableRow = $x("(//td[contains(text(), 'SendSMS')])[1]");
    private SelenideElement copyBtn = $x("//div[@title='Скопировать в буфер обмена']");

    public String getCode(String phoneNumber, SelenideDriver driver) {
        String code = "";
        addFilterConditionBtn.click();
        filterInput.setValue(phoneNumber);
        searchBtn.click();
        tableRow.click();
        copyBtn.click();

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
        return code;
    }

    // Метод для получения текста из буфера обмена (простой пример)
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

