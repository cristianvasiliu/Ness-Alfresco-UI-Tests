package alfresco.tests;

import alfresco.Flows;
import alfresco.pages.FilesPage;
import alfresco.pages.HomePage;
import alfresco.pages.LoginPage;
import alfresco.pages.SettingsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PageElement;

public class CreateRepositoryTests extends BaseUiTest {
    private PageElement pageElement;
    private SettingsPage settingsPage;
    private LoginPage loginPage;
    private FilesPage filesPage;
    private HomePage homePage;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUpBeforeClass() {
        pageElement = PageFactory.initElements(webDriver, PageElement.class);
        settingsPage = PageFactory.initElements(webDriver, SettingsPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        filesPage = PageFactory.initElements(webDriver, FilesPage.class);
        homePage = PageFactory.initElements(webDriver, HomePage.class);

        LOGGER.isInfoEnabled();
    }

    @Test (priority = 1)
    public void loginIntoWebsiteTest() {
        LOGGER.info("loginIntoWebsiteTest test has begun");

        pageElement.clickOnButton(settingsPage.getSelectProviderArrow());
        pageElement.clickOnButton(settingsPage.getProviderSecondOptionBtn());
        LOGGER.debug("getProviderSecondOptionBtn was pressed");

        pageElement.clickOnButton(settingsPage.getApplyBtn());

        waitForPageLoad();
        LOGGER.info( "Alfresco Login Page was loaded successfully.");

        pageElement.enterTextToInputField("guest@example.com", loginPage.getUsernameInput());
        pageElement.enterTextToInputField("Password", loginPage.getPasswordInput());
        pageElement.clickOnButton(loginPage.getSignInBtn());

        waitForPageLoad();
        assert homePage.getAppHomeViewHeader().isDisplayed();
        assert homePage.getAppHomeViewSubHeaderTitle().getText().equals("Angular components for Alfresco");

        LOGGER.info( "Alfresco Home Page was loaded successfully.");
    }

    @Test (priority = 2)
    public void createRepository(){
        Flows.loadHomepage(webDriver, false);
        LOGGER.info( "Alfresco Files Page was loaded successfully.");

        pageElement.clickOnButton(filesPage.getNewFolderBtn());
        pageElement.waitForElementVisibility(filesPage.getNewFolderModal());

        String randomGeneratedFolderName = "ChrisV-" + RandomStringUtils.randomAlphabetic(3);
        pageElement.enterTextToInputField(randomGeneratedFolderName, filesPage.getNameInputNewFolderModal());
        pageElement.clickOnButton(filesPage.getCreateBtnNewFolderModal());
        pageElement.waitForElementDisappear(filesPage.getCreateBtnNewFolderModal());

        boolean foundNewlyCreatedFolder = false;
        for (WebElement element : filesPage.getFolderList()) {
            if (element.getAttribute("aria-label").equals(randomGeneratedFolderName)){
                foundNewlyCreatedFolder = true;
                break;
            }
        }

        assert foundNewlyCreatedFolder;
        LOGGER.info( "Folder with user name was created successfully.");
    }

}
