package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage {

    @FindBy(css = "#adf-provider-selector > div > div.mat-select-arrow-wrapper.ng-tns-c136-1 > div")
    private WebElement selectProviderArrow;

    @FindBy(css="#mat-option-1 > span")
    private WebElement providerSecondOptionBtn;

    @FindBy(css = "#host-button > span")
    private WebElement applyBtn;

    public WebElement getSelectProviderArrow() {
        return selectProviderArrow;
    }

    public WebElement getProviderSecondOptionBtn() {
        return providerSecondOptionBtn;
    }

    public WebElement getApplyBtn() {
        return applyBtn;
    }

}
