package efr;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProcessInitiationPage {
    private SelenideElement continueButton = $x("//button[text()='Продолжить']");

    public void continueProcess() throws InterruptedException {
        Thread.sleep(10000);
        continueButton.click();
        Thread.sleep(5000);
        while (continueButton.exists()){
            System.out.println("Клик по кнопке \"Продолжить\"");
            continueButton.click();
            Thread.sleep(1500);
        }
        Thread.sleep(20000);
        if(continueButton.exists()){
            continueButton.click();
        }
    }
}
