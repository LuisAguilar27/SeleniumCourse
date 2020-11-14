package pages;

import drivermanager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumhelpers.SeleniumActions;
import waithelpers.WaitHelper;

import java.util.List;

public class HomePage {
    @FindBy(css = "#panelLinkSelected > span.title-enter-yuor-address-guest-home-button")
    private WebElement addressButton;

    @FindBy(className = "enter-yuor-address-guest-home enter-yuor-address-guest-home-change-address")
    private WebElement firstAddress;

    @FindBy(css = "#guestShipZoneaddress")
    private WebElement zipCodeInput;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[2]/div[1]/ul/li[2]/div/div[2]/a/div[2]")
    private WebElement acceptButton;

    @FindBy(className = "tooltip-inner")
    private WebElement errorMessage;

    @FindBy(css = "body > div.pac-container.pac-logo")    //Solo cuando el resultado te retorna un solo registro
    private WebElement addressOptions;

    public void addressButton(){
        SeleniumActions.clickOnElement(addressButton);
    }

    public boolean verifyAddressPopUpIsPresent(){
        return SeleniumActions.elementIsVisible(firstAddress);
    }

    public void inputCode(String zipCode){
        zipCodeInput.click();
        SeleniumActions.sendKeys(zipCodeInput,zipCode);
    }

    public void acceptButton(){
        SeleniumActions.clickOnElement(acceptButton);
    }

    public void clickConfigOption(int position){
        WaitHelper.waitForElementToBeVisible(DriverManager.getDriver(), addressOptions);
        List<WebElement> optionsList = addressOptions.findElements(By.cssSelector("body > div.pac-container.pac-logo > div"));
        optionsList.get(position).click();
    }

    public String errorMessage(){
        WaitHelper.waitForElementToBeVisible(DriverManager.getDriver(), errorMessage);
        return  SeleniumActions.getText(errorMessage);
    }
}
