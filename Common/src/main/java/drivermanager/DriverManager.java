package drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverInfo;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.sql.Driver;


public class DriverManager {
    static WebDriver driver;
    public static WebDriver createWebDriver(String browser){
        switch (browser){
            case "chrome":
            default:
                driver = chromeBrowser();
                break;
            case "firefox":
                driver = firefoxBrowser();
                break;
        }
        return driver;
    }
    public static ChromeDriver chromeBrowser (){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }
    public static FirefoxDriver firefoxBrowser(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-private");
        return new FirefoxDriver(firefoxOptions);
    }
    public static WebDriver getDriver(){
        return driver;
    }
    public static void tearDownBrowser(){
        driver.quit();
    }
}
