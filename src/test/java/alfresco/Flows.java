package alfresco;

import alfresco.tests.BaseUiTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Flows {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void loadNewHomepageWindow(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        loadHomepage(webDriver, true);
    }

    public static void loadHomepage(WebDriver webDriver, boolean newHomePageWindow) {
        if (!newHomePageWindow) {
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        webDriver.get("http://qaexercise.envalfresco.com/files");
        BaseUiTest baseUiTest = new BaseUiTest();
        baseUiTest.waitForPageLoad();
        LOGGER.info("Alfresco Home Page was loaded successfully.");
    }

}
