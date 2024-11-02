package efr;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FindClientPage {
    CreateClient createClient = new CreateClient();

    private SelenideElement passwordInput = $("input[title='Введите серию и номер']");
    private SelenideElement searchButton = $("button[data-id='document-button-find']");

    private SelenideElement newCelientBtn = $x("//button[contains(text(),'Новый клиент')]");

    private SelenideElement menuBtn = $x("(//a[@aria-haspopup='menu'])[2]");

    private SelenideElement systemJournalBtn = $x("//span[contains(text(), 'Системный журнал')]");
    public void findClient(String seriesAndNumber){
        passwordInput.setValue(seriesAndNumber);
        searchButton.click();
    }

    public void newClient(String seriesAndNumber){
        passwordInput.setValue(seriesAndNumber);
        searchButton.click();
        newCelientBtn.click();
    }

    public void openSystemJournal(SelenideDriver driver) throws InterruptedException {
        menuBtn.click();
        Thread.sleep(2000);
        if (!systemJournalBtn.exists()){
            menuBtn.click();
            Thread.sleep(2000);
        }
        systemJournalBtn.click();
    }

}
