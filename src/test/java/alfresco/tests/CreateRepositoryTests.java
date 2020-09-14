package alfresco.tests;

import alfresco.Flows;
import alfresco.pages.FilesPage;
import alfresco.pages.LoginPage;
import alfresco.pages.SettingsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PageElement;

public class CreateRepositoryTests extends BaseUiTest {
    private PageElement pageElement;
    private SettingsPage settingsPage;
    private LoginPage loginPage;
    private FilesPage filesPage;
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void setUpBeforeClass() {
        pageElement = PageFactory.initElements(webDriver, PageElement.class);
        settingsPage = PageFactory.initElements(webDriver, SettingsPage.class);
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        filesPage = PageFactory.initElements(webDriver, FilesPage.class);

        LOGGER.isInfoEnabled();
    }

    @Test (priority = 1)
    public void loginIntoWebsiteTest() {
        LOGGER.info("createRepository test has begun");

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
        LOGGER.info( "Alfresco Home Page was loaded successfully.");
    }

    @Test (priority = 2)
    public void createRepository(){
        Flows.loadHomepage(webDriver, false);
        LOGGER.info( "Alfresco Files Page was loaded successfully.");

        pageElement.clickOnButton(filesPage.getNewFolderBtn());
    }

}
