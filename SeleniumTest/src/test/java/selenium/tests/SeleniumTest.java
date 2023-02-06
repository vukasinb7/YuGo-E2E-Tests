package selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pages.YuGoHomePage;

public class SeleniumTest {
    private WebDriver webDriver;
    private String PASSENGER_EMAIL="pera.peric@email.com";
    private String PASSENGER_PASSWORD="Password123";
    private String DRIVER_EMAIL="perislav.peric@email.com";
    private String DRIVER_PASSWORD="Password123";
    private String ADMIN_EMAIL="marko.markovic@email.com";
    private String ADMIN_PASSWORD="Password123";

    @BeforeEach
    void init(){
        System.setProperty("WebDriver.chrome.driver", "chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterEach
    void flush(){
        //webDriver.quit();
    }

    @Test
    void firstTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.clickSelectDepartureIcon();
        homePage.clickOnMap(-10, 20);
        homePage.clickSelectDestinationIcon();
        homePage.clickOnMap(45, -35);
        homePage.clickRouteContinueButton();
        homePage.selectLuxVehicle();
        homePage.clickVehicleTypeContinueButton();
    }

    @Test
    void successfulLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
    }

    @Test
    void unsuccessfulLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction("awpaodw@dwadpmaw.com","fdsgsfdg");
        Assertions.assertNotEquals("", homePage.getSignInErrorMessage());
    }
    
}
