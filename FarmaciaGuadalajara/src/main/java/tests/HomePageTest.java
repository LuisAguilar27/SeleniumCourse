package tests;

import drivermanager.DriverManager;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;


public class HomePageTest {
    WebDriver driver;
    @BeforeMethod
    public void pageSetup (){
        driver=DriverManager.createWebDriver("chrome");
        driver.get("https://www.farmaciasguadalajara.com/es/farmaciasguadalajara");
    }
    @DataProvider(name = "testZipCode")
    public Object[][] testData (){
        return new  Object[][]{
                //{zipceode,position,menssage},
                {"1111111111111111",0,"Por favor, introduzca la dirección completa."},
                {"29200",0,"Aún no contamos con cobertura en tu zona, por favor, intenta con otra dirección."},
                {"",0,"Por favor, introduzca la dirección completa."},
                {"!#$%&/()=?¡",0,"Por favor, introduzca la dirección completa."}
        };
    }

    @Test(dataProvider = "testZipCode")
    public void test1(String zipcode, int position, String message){
        HomePage homePageTest = PageFactory.initElements(driver, HomePage.class);
        homePageTest.addressButton();
        homePageTest.inputCode(zipcode);
        if (zipcode.length() > 1 && zipcode.length() < 8){
            homePageTest.clickConfigOption(position);
        }
        homePageTest.acceptButton();
        Assert.assertEquals(message,homePageTest.errorMessage());
    }
/*
    @Test
    public void test1 (){
        HomePage homePageTest = PageFactory.initElements(driver, HomePage.class); //Se esta creando la instancia de la pagina donde estan declarados los elementos de la pagina
        homePageTest.addressButton();
//        Assert.assertTrue(homePageTest.verifyAddressPopUpIsPresent(),"La dirección ya ha sido introducida anteriormente");
        homePageTest.inputCode("1111111111111111");
        homePageTest.acceptButton();
        Assert.assertEquals("Por favor, introduzca la dirección completa.",homePageTest.errorMessage());
    }
    @Test
    public void test2() throws Exception {
        HomePage homePageTest = PageFactory.initElements(driver, HomePage.class);
        homePageTest.addressButton();
//        Assert.assertTrue(homePageTest.verifyAddressPopUpIsPresent(),"La dirección ya ha sido introducida anteriormente");
        homePageTest.inputCode("29200");
        int position = 0;   //posición de la ubicacion de la lista de elementos mostrados
        //Thread.sleep(3000);
        homePageTest.clickConfigOption(position);
        homePageTest.acceptButton();
        Assert.assertEquals("Aún no contamos con cobertura en tu zona, por favor, intenta con otra dirección.",homePageTest.errorMessage());
    }
*/
    @AfterMethod
    public void afterTest(){
        driver.quit();
    }
}
