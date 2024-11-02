package efr;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class VisualAssessment {

    private SelenideElement continueBtn = $x("//button[contains(text(), 'Продолжить')]");

    public void clickContinueBtn() throws InterruptedException {
        Thread.sleep(7000);
        continueBtn.click();

    }
}
