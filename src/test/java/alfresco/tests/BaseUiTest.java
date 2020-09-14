package alfresco.tests;

import alfresco.pages.SettingsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseUiTest {
    static WebDriver webDriver;
    private SettingsPage settingsPage = PageFactory.initElements(webDriver, SettingsPage.class);
    private static Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    public void driverOpensSettingsPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_85-0-4183-87");

        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("http://qaexercise.envalfresco.com/settings");
        waitForPageLoad();

        LOGGER.isInfoEnabled();
        LOGGER.info( "Alfresco Settings Page was loaded successfully.");
    }

    public void waitForPageLoad() {
        try {
            new WebDriverWait(webDriver, 5, 100)
                    .until((ExpectedCondition<Boolean>) driver -> {
                        assert driver != null;
                        return ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState").equals("complete");
                    });
        } catch (Exception e) {
            ((JavascriptExecutor) webDriver).executeScript("return window.stop");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser(){
        webDriver.quit();
    }

}
