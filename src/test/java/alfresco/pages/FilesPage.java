package alfresco.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilesPage {

    protected WebDriver driver;

    @FindBy(css="#document-list-container > adf-upload-drag-area > div > adf-toolbar > mat-toolbar > div > button:nth-child(2) > span > mat-icon")
    private WebElement newFolderBtn;

    @FindBy(css="adf-folder-dialog > mat-dialog-content > form")
    private WebElement newFolderModal;

    @FindBy(css="#adf-folder-name-input")
    private WebElement nameInputNewFolderModal;

    @FindBy(css="#adf-folder-create-button > span")
    private WebElement newFolderCreateBtn;

    @FindBy(css="adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body")
    private List<WebElement> folderList;

    @FindBy(css="adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body")
    private WebElement folderListElement;

    @FindBy(css="#adf-folder-cancel-button > span")
    private WebElement newFolderCancelBtn;

    @FindBy(css="div > button:nth-child(5) > mat-icon")
    private WebElement deleteIndividualFolderBtn;

    @FindBy(css="#document-list-container > adf-upload-drag-area > div > adf-toolbar > mat-toolbar > div > button:nth-child(6) > span > mat-icon")
    private WebElement deleteAllFoldersBtn;

    // TODO: delete commented code if unnecessary
    /*private final By folderList = By.cssSelector("adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body");*/

    private final By newFolderBtnElement = By.xpath(".//*[@id='document-list-container']/adf-upload-drag-area/div/adf-toolbar/mat-toolbar/div/button[2]/span/mat-icon");
    private final By hiddenAlertModal = By.cssSelector(".cdk-overlay-pane > snack-bar-container > simple-snack-bar > span");

    public WebElement getNewFolderBtn() {
        return newFolderBtn;
    }

    public WebElement getNewFolderModal() {
        return newFolderModal;
    }

    public WebElement getNameInputNewFolderModal() {
        return nameInputNewFolderModal;
    }

    public WebElement getNewFolderCreateBtn() {
        return newFolderCreateBtn;
    }

    public List<WebElement> getFolderList() {
        return folderList;

        // TODO: delete commented code if unnecessary
        /*return driver.findElements(folderList);*/
    }

    public WebElement getFolderListElement() {
        return folderListElement;
    }

    public By getNewFolderBtnElement() {
        return newFolderBtnElement;
    }

    public By getHiddenAlertModal() {
        return hiddenAlertModal;
    }

    public WebElement getNewFolderCancelBtn() {
        return newFolderCancelBtn;
    }

    public WebElement getDeleteIndividualFolderBtn() {
        return deleteIndividualFolderBtn;
    }

    public WebElement getDeleteAllFoldersBtn() {
        return deleteAllFoldersBtn;
    }

}
