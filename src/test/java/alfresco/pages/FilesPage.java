package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilesPage {

    @FindBy(css="#document-list-container > adf-upload-drag-area > div > adf-toolbar > mat-toolbar > div > button:nth-child(2) > span > mat-icon")
    private WebElement newFolderBtn;

    public WebElement getNewFolderBtn() {
        return newFolderBtn;
    }

}
