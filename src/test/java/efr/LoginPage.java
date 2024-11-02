package efr;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement selectDomain = $("span.Select-arrow-zone");
    private SelenideElement domainRf = $x("//div[text()='RF']");
    private SelenideElement domainGo = $x("//div[text()='GO']");

    private SelenideElement usernameInput = $("input.input[type='text'][data-id='field-login']");
    private SelenideElement passwordInput = $("input.input[type='password'][data-id='field-password']");

    private SelenideElement loginButton = $("button[data-id='button-save']");

    public void login(String userName, String password){
        selectDomain.click();
        domainRf.click();
        usernameInput.setValue(userName);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public void adminLogin(String userName, String password){
        selectDomain.click();
        domainGo.click();
        usernameInput.setValue(userName);
        passwordInput.setValue(password);
        loginButton.click();
    }
}
