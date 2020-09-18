package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(css = "app-home-view > header")
    private WebElement appHomeViewHeader;

    @FindBy(css = "app-home-view > header > div > div.app-home-headline > h2")
    private WebElement appHomeViewSubHeaderTitle;

    public WebElement getAppHomeViewHeader() {
        return appHomeViewHeader;
    }

    public WebElement getAppHomeViewSubHeaderTitle() {
        return appHomeViewSubHeaderTitle;
    }

}
