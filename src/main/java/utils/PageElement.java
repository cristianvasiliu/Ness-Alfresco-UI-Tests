package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class PageElement {

    private final long TIMEOUT_SECONDS = 5;
    private final long TIMEOUT_ELEMENT_DISAPPEAR_SECONDS = 5;
    private final long TIMEOUT_POLL_MILLISECONDS = 1000;

    private WebDriver driver;
    public PageElement(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForElementVisibility(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementVisibility(WebElement webElement, long secondsToWait) {
        new WebDriverWait(driver, secondsToWait)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    private void waitForAnimatedElementVisibility(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
        sleep(1000);
    }

    private void moveMouseOver(WebElement webElement) {
        waitForElementVisibility(webElement);
        new Actions(driver).moveToElement(webElement).perform();
    }

    public void waitForElementDisappear(WebElement webElement) {
        try{
            new WebDriverWait(driver, TIMEOUT_ELEMENT_DISAPPEAR_SECONDS,
                    TIMEOUT_POLL_MILLISECONDS).until(ExpectedConditions
                    .invisibilityOfAllElements(Collections.singletonList(webElement)));
        }catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void waitForElementAttributeVisibility(WebElement webElement, String value) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        webDriverWait.until(ExpectedConditions.attributeToBe(webElement,"style", value));
    }

    public void selectFirstSearchedItem(List<WebElement> containerList){
        for (WebElement webElement : containerList){
            webElement.click();
            break;
        }
    }

    public void selectIndexItem(List<WebElement> containerList, String value){
        for (WebElement webElement : containerList){
            if (webElement.getAttribute("index").contentEquals(value)){
                webElement.click();
                break;
            }
        }
    }

    public void clickOnButton(WebElement webElement){
        waitForElementVisibility(webElement);
        webElement.click();
    }

    public void enterTextToInputField(String itemToTypeIn, WebElement inputField) {
        waitForElementVisibility(inputField);
        inputField.clear();
        inputField.sendKeys(itemToTypeIn);
    }

    public void getWebElementCssClass(WebElement webElement, String value) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 1);
        webDriverWait.until(ExpectedConditions.attributeToBe(webElement,"class", value));
    }

    public void scrollToElementWithWait(WebElement webElement) {
        waitForAnimatedElementVisibility(webElement);
        scrollToElement(webElement);
    }

    private void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        sleep(5000);
    }

    public static void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
