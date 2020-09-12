package alfresco.tests;

import alfresco.pages.SettingsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.PageElement;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class BaseUiTest {
    static WebDriver driver;
    private SettingsPage settingsPage;

    @BeforeClass
    public void driverOpensSettingsPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_85-0-4183-87");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://qaexercise.envalfresco.com/settings");
        waitForPageLoad();
        LOGGER.info("Alfresco Settings Page was loaded successfully.");

        settingsPage = PageFactory.initElements(driver, SettingsPage.class);
    }

    public void waitForPageLoad() {
        try {
            new WebDriverWait(driver, 5, 100)
                    .until((ExpectedCondition<Boolean>) driver -> {
                        assert driver != null;
                        return ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState").equals("complete");
                    });
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("return window.stop");
        }

        try {
            PageElement pageElement = PageFactory.initElements(driver, PageElement.class);
            settingsPage = PageFactory.initElements(driver, SettingsPage.class);

            /*pageElement.waitForElementVisibility();
            settingsPage.clickOnConsentButton();*/
        } catch (Exception e) {
            System.out.println("Element consentButton not found, this is still ok");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }

}
