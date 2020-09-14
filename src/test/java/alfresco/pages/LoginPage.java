package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(css="#username")
    private WebElement usernameInput;

    @FindBy(css="#password")
    private WebElement passwordInput;

    @FindBy(css="#login-button")
    private WebElement signInBtn;

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

}
