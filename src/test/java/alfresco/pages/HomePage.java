package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(css = "app-home-view > header")
    private WebElement appHomeViewHeader;

    @FindBy(css = "app-home-view > header > div > div.app-home-headline > h2")
    private WebElement appHomeViewSubHeaderTitle;

    @FindBy(css = "mat-sidenav-container > mat-sidenav > div > mat-nav-list > mat-list-item:nth-child(3) > div > div.mat-list-text > span")
    private WebElement contentServicesButton;

    public WebElement getAppHomeViewHeader() {
        return appHomeViewHeader;
    }

    public WebElement getAppHomeViewSubHeaderTitle() {
        return appHomeViewSubHeaderTitle;
    }

    public WebElement getContentServicesButton() { return contentServicesButton; }

}
