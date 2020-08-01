package waithelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    public static boolean waitForPageLoad(WebDriver driver){
        WebDriverWait wait = createExplicitWait(driver);
        return wait.until((ExpectedCondition<Boolean>) drv -> ((JavascriptExecutor) drv)
                .executeScript("return document.readyState").equals("complete"));
    }
    public static WebElement waitForElementLocated(By locator, WebDriver driver){
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element){
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeInvisible(WebDriver driver, WebElement element) {
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementUntilContains(WebDriver driver, WebElement element, String text){
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public static boolean waitForElementToBeEnabled(WebDriver driver, WebElement element){
        waitForElementToBeVisible(driver, element);
        return element.isEnabled();
    }

    public static void waitForElementUntilAttributeContains
            (WebDriver driver, WebElement element, String attribute, String value){
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public static void waitForURLContains(WebDriver driver, String extractUrl){
        FluentWait wait = createFluentWait(driver);
        wait.until(ExpectedConditions.urlContains(extractUrl));
    }

    public static FluentWait<WebDriver> createFluentWait (WebDriver driver){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /***
     * This method retuns an Explicit Wait with a timeout of 30 seconds
     * @param driver
     * @return Explicit WebDriverWait
     */
    public static WebDriverWait createExplicitWait (WebDriver driver){
        return new WebDriverWait(driver,30);
    }

    public static void waitForElementToBePresent(WebDriver driver, WebElement element){
        createExplicitWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement locator){
        createExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /***
     * This method is a method overload from waitForElementToBePresent method created before
     * @param driver
     * @param locator
     */
    public static void waitForElementToBePresent(WebDriver driver, By locator){
        createExplicitWait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}