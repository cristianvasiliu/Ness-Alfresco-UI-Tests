package alfresco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilesPage {

    @FindBy(css="#document-list-container > adf-upload-drag-area > div > adf-toolbar > mat-toolbar > div > button:nth-child(2) > span > mat-icon")
    private WebElement newFolderBtn;

    @FindBy(css="adf-folder-dialog > mat-dialog-content > form")
    private WebElement newFolderModal;

    @FindBy(css="#adf-folder-name-input")
    private WebElement nameInputNewFolderModal;

    @FindBy(css="#adf-folder-create-button > span")
    private WebElement createBtnNewFolderModal;

    @FindBy(css="adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body")
    private List<WebElement> folderList;

    public WebElement getNewFolderBtn() {
        return newFolderBtn;
    }

    public WebElement getNewFolderModal() {
        return newFolderModal;
    }

    public WebElement getNameInputNewFolderModal() {
        return nameInputNewFolderModal;
    }

    public WebElement getCreateBtnNewFolderModal() {
        return createBtnNewFolderModal;
    }

    public List<WebElement> getFolderList() {
        return folderList;
    }

}
