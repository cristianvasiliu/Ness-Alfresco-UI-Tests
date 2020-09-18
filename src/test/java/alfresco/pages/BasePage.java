package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    @FindBy(css = "mat-sidenav > div > mat-nav-list > mat-list-item.mat-list-item.mat-focus-indicator.app-sidenav-link.mat-list-item-avatar.mat-list-item-with-avatar.ng-star-inserted.app-sidenav-link--active > div > div.mat-list-text > span")
    private WebElement contentServicesBtn;

    public WebElement getContentServicesBtn() {
        return contentServicesBtn;
    }

}
