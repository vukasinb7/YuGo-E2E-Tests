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
    void createRideSuccessfulTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction("pera.peric@email.com", "Password123");
        homePage.clickSelectDepartureIcon();
        homePage.clickOnMap(-10, 20);
        homePage.clickSelectDestinationIcon();
        homePage.clickOnMap(45, -35);
        homePage.clickRouteContinueButton();
        homePage.selectLuxVehicle();
        homePage.clickVehicleTypeContinueButton();
        homePage.addPassenger("darko.darkovic@email.com");
        homePage.clickAddPassengersContinueButton();
        homePage.clickTimePickerContinueButton();
    }
    @Test
    void createRideUnsuccessfulTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction("pera.peric@email.com", "Password123");

        homePage.enterDeparture("Subotica");
        homePage.pickRecommendedAddress();

        homePage.enterDestination("Kikinda");
        homePage.pickRecommendedAddress();

        homePage.clickRouteContinueButton();

        homePage.selectLuxVehicle();
        homePage.clickVehicleTypeContinueButton();
        homePage.clickAddPassengersContinueButton();
        homePage.clickTimePickerContinueButton();
    }
    @Test
    void pickLocationWithoutMarkersTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        homePage.enterDeparture("Vojvode Misica, Novi Sad");
        String expectedDeparture = homePage.pickRecommendedAddress();
        String checkDeparture = homePage.getDepartureAddressText();

        homePage.enterDestination("3bir, Novi Sad");
        String expectedDestination = homePage.pickRecommendedAddress();
        String checkDestination = homePage.getDestinationAddressText();

        Assertions.assertEquals(checkDeparture, expectedDeparture);
        Assertions.assertEquals(checkDestination, expectedDestination);
    }
    @Test
    void successfulAdminLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction(ADMIN_EMAIL, ADMIN_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isAdminLoggedIn());
    }
    @Test
    void successfulPassengerLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction(PASSENGER_EMAIL, PASSENGER_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isPassengerLoggedIn());
    }

    @Test
    void successfulDriverLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction(DRIVER_EMAIL, DRIVER_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isDriverLoggedIn());
    }

    @Test
    void unsuccessfulLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction("awpaodw@dwadpmaw.com","fdsgsfdg");
        Assertions.assertNotEquals("", homePage.getSignInErrorMessage());
    }
    
}
