package alfresco.tests;

import alfresco.pages.SettingsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PageElement;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CreateRepositoryTests extends BaseUiTest{
    private PageElement pageElement;
    private SettingsPage settingsPage;

    @BeforeClass
    public void setUpBeforeClass() {
        pageElement = PageFactory.initElements(driver, PageElement.class);
        settingsPage = PageFactory.initElements(driver, SettingsPage.class);
    }

    @Test
    public void createRepository() {
        LOGGER.info("createRepository test is here");
    }

}
