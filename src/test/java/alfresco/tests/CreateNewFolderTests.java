package alfresco.tests;

import alfresco.Flows;
import alfresco.pages.FilesPage;
import alfresco.pages.HomePage;
import alfresco.pages.LoginPage;
import alfresco.pages.SettingsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PageElement;

import java.util.List;

public class CreateNewFolderTests extends BaseUiTest {
    private PageElement pageElement;
    private SettingsPage settingsPage;
    private LoginPage loginPage;
    private FilesPage filesPage;
    private HomePage homePage;
    private static final Logger LOGGER = LogManager.getLogger();
    final String randomGeneratedFolderName = "ChrisV-" + RandomStringUtils.randomAlphabetic(3);
    List<WebElement> foldersListElements;

    @BeforeClass
    public void setUpBeforeClass() {
        pageElement = PageFactory.initElements(webDriver, PageElement.class);
        settingsPage = PageFactory.initElements(webDriver, SettingsPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        filesPage = PageFactory.initElements(webDriver, FilesPage.class);
        homePage = PageFactory.initElements(webDriver, HomePage.class);

        LOGGER.isInfoEnabled();
    }

    @Test(priority = 1)
    public void loginIntoWebsiteTest() {
        LOGGER.info("loginIntoWebsiteTest test has begun");

        pageElement.clickOnButton(settingsPage.getSelectProviderArrow());
        pageElement.clickOnButton(settingsPage.getProviderSecondOptionBtn());
        LOGGER.debug("getProviderSecondOptionBtn was pressed");

        pageElement.clickOnButton(settingsPage.getApplyBtn());

        waitForPageLoad();
        LOGGER.info("Alfresco Login Page was loaded successfully.");

        pageElement.enterTextToInputField("guest@example.com", loginPage.getUsernameInput());
        pageElement.enterTextToInputField("Password", loginPage.getPasswordInput());
        pageElement.clickOnButton(loginPage.getSignInBtn());

        waitForPageLoad();
        assert homePage.getAppHomeViewHeader().isDisplayed();
        assert homePage.getAppHomeViewSubHeaderTitle().getText().equals("Angular components for Alfresco");

        LOGGER.info("Alfresco Home Page was loaded successfully.");
    }

    @Test(priority = 2)
    public void createFolderWithSuccess() {
        Flows.loadHomepage(webDriver, false);
        LOGGER.info("Alfresco Files Page was loaded successfully.");
        // added sleep in order to wait for home page to fully load
        pageElement.sleep(2000);

        LOGGER.info("Folder name to be created: " + randomGeneratedFolderName);

        pageElement.clickOnButton(filesPage.getNewFolderBtn());
        pageElement.waitForElementVisibility(filesPage.getNewFolderModal());

        pageElement.enterTextToInputField(randomGeneratedFolderName, filesPage.getNameInputNewFolderModal());
        pageElement.clickOnButton(filesPage.getNewFolderCreateBtn());
        pageElement.waitForElementDisappear(filesPage.getNewFolderCreateBtn());

        LOGGER.info("New folder " + randomGeneratedFolderName + " was created successfully.");
        LOGGER.info("Search to find the newly created folder will be performed.");

        boolean foundNewlyCreatedFolder = false;
        foldersListElements = filesPage.getFolderListElement().findElements(By.cssSelector(
                "#document-list-container > adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body > adf-datatable-row"));

        for (WebElement element : foldersListElements) {
            if (element.getAttribute("aria-label").equals(randomGeneratedFolderName)) {
                foundNewlyCreatedFolder = true;
                break;
            }
        }

        assert foundNewlyCreatedFolder;
        LOGGER.info("New folder successfully created " + randomGeneratedFolderName +
                " was found along with the other folders in 'My file' list");
    }

    @Test(priority = 3)
    public void sameFolderNameShowsErrorMessage() {
        Flows.loadHomepage(webDriver, false);

        LOGGER.info("Alfresco Files Page was loaded successfully.");
        // added sleep in order to wait for home page to fully load
        pageElement.sleep(2000);

        pageElement.clickOnButton(filesPage.getNewFolderBtn());
        pageElement.waitForElementVisibility(filesPage.getNewFolderModal());

        pageElement.enterTextToInputField(randomGeneratedFolderName, filesPage.getNameInputNewFolderModal());
        pageElement.clickOnButton(filesPage.getNewFolderCreateBtn());

        LOGGER.info("Create New Folder button was pressed.");

        // assert that modal is still displayed and not closed
        assert (filesPage.getNewFolderModal().isDisplayed());
        pageElement.waitForAnimatedElementVisibility(filesPage.getHiddenAlertModal());

        String actualSameNameErrorMessage = webDriver.findElement(By.cssSelector(
                ".cdk-overlay-pane snack-bar-container > simple-snack-bar > span")).getText();
        pageElement.waitForItemDisappear(filesPage.getHiddenAlertModal());

        String expectedSameNameErrorMessage = "There's already a folder with this name. Try a different name.";
        assert expectedSameNameErrorMessage.equals(actualSameNameErrorMessage);

        LOGGER.info("Error message regarding 'same folder name already exists', was displayed.");
    }

    @AfterClass
    public void removeNewlyCreatedFolder() {
        LOGGER.info("'After Class' removal of newly created folder has begun.");

        pageElement.clickOnButton(filesPage.getNewFolderCancelBtn());
        pageElement.waitForElementDisappear(filesPage.getNewFolderCancelBtn());

        LOGGER.info("Cancel New Folder creation button was pressed.");

        foldersListElements = filesPage.getFolderListElement().findElements(By.cssSelector(
                "#document-list-container > adf-upload-drag-area > div > div > adf-document-list > adf-datatable > div > div.adf-datatable-body > adf-datatable-row"));

        String actualDeletionConfirmedMessage = "";
        for (WebElement element : foldersListElements) {
            if (element.getAttribute("aria-label").equals(randomGeneratedFolderName)) {
                pageElement.scrollToElementWithWait(element);
                WebElement elementThreeDotsBtn = element.findElement(By.cssSelector("div > button > span > mat-icon"));

                LOGGER.info("3button element was found and saved as an web element.");

                pageElement.clickOnButton(elementThreeDotsBtn);

                LOGGER.info("Folder properties button was clicked.");

                pageElement.scrollToElementWithWait(filesPage.getDeleteAllFoldersBtn());

                // TODO: 3button menu is not displayed and clicking below button fails
                /*pageElement.clickOnButton(filesPage.getDeleteIndividualFolderBtn());*/

                pageElement.clickOnButton(filesPage.getDeleteAllFoldersBtn());
                pageElement.waitForAnimatedElementVisibility(filesPage.getHiddenAlertModal());

                actualDeletionConfirmedMessage = webDriver.findElement(By.cssSelector(
                        ".cdk-overlay-pane snack-bar-container > simple-snack-bar > span")).getText();

                pageElement.waitForItemDisappear(filesPage.getHiddenAlertModal());

                break;
            }
        }

        String expectedDeletionConfirmedMessage = randomGeneratedFolderName + " deleted";
        assert expectedDeletionConfirmedMessage.equals(actualDeletionConfirmedMessage);

        LOGGER.info("Newly created folder was deleted.");
    }

}
