package seleniumhelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumActions {
    public static Object executeJavaScript(WebDriver driver, String jsString){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(jsString);
    }
    public static void clickOnElement(WebElement element){
        element.click();
    }
    public static void clickOnElementLocated(WebDriver driver,By locator){
        driver.findElement(locator).click();
    }
    public static void submit(WebElement element){
        element.submit();
    }
    public static void secondaryClick(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
    public static void scrollToElementLocated(WebDriver driver, WebElement element){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void scrollToTop(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.HOME).build().perform();
    }
    public static void scrollToBottom(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END).build().perform();
    }
    public static void sendKeys(WebElement element,String text){
        element.sendKeys(text);
    }
    public static boolean isCheckBoxOrRadioChecked(WebElement element){
        boolean verify = element.isSelected();
        return verify;
    }
    public static void cleanElementText(WebElement element){
        element.clear();
    }
    public static boolean elementIsEnabled(WebElement element){
        return element.isEnabled();
    }
    public static boolean elementIsVisible(WebElement element){
        return element.isDisplayed();
    }
    public static String getElementAttribute(WebElement element, String name){
        String ElementAtribut = element.getAttribute(name);
        return ElementAtribut;
    }
    public static String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public static int getValueFromString(WebElement element){
        return Integer.parseInt(getText(element));
    }
    public static String getText(WebElement element){
        return element.getText();
    }
    public static void selectByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }
    public static void selectByVisibleText(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public static void selectByIndex(WebElement element, int value){
        Select select = new Select(element);
        select.selectByIndex(value);
    }

}