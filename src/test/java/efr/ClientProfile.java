package efr;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ClientProfile {

    private SelenideElement closeNotificationBtn = $("button.bx--toast-notification__close-button");
    private SelenideElement newProductButton = $("button[data-id='overflow-menu-button-overflow-menu-button']");
    private SelenideElement takeOutMortgageButton = $x("//button//div[text()='Оформить ипотеку']");

    private SelenideElement clientsBtn = $x("//span[contains(text(),'Клиенты')]");
//    private SelenideElement takeOutMortgageButton = $x("//button[@data-id='item' and @role='menuitem']//div[contains(@class, 'text-elem') and text()='Оформить ипотеку']");
//    private SelenideElement takeOutMortgageButton = $x("//button//div[text()='Оформить ипотеку']");
//    private SelenideElement takeOutMortgageButton = $$("button.bx--overflow-menu-options__btn div.text-elem")
//            .findBy(Condition.text("Оформить ипотеку"));
//    private SelenideElement takeOutMortgageButton = $$("button.bx--overflow-menu-options__btn")
//            .findBy(Condition.text("Оформить ипотеку"));

    public void takeOutMortgage() throws InterruptedException {
        Thread.sleep(3000);
        closeNotificationBtn.click();
        newProductButton.click();
        Thread.sleep(3000);
        takeOutMortgageButton.click();
    }

    public void goToSearchClientPage(){
        clientsBtn.click();
    }
}
