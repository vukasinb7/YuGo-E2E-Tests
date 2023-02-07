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
    private WebDriver webDriver1;
    private final String PASSENGER_EMAIL="pera.peric@email.com";
    private final String PASSENGER_02_EMAIL = "darko.darkovic@email.com";
    private final String PASSENGER_03_EMAIL = "parica.petkovic@email.com";
    private final String PASSENGER_PASSWORD="Password123";
    private final String DRIVER_EMAIL="perislav.peric@email.com";
    private final String DRIVER_02_EMAIL="nikola.nikolic@email.com";
    private final String DRIVER_PASSWORD="Password123";
    private final String ADMIN_EMAIL="marko.markovic@email.com";
    private final String ADMIN_PASSWORD="Password123";

    @BeforeEach
    void init(){
        System.setProperty("WebDriver.chrome.driver", "chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterEach
    void flush(){
        webDriver.quit();
    }

    @Test
    void createRideSuccessfulTest(){
        webDriver1 = new ChromeDriver();
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpenedPassenger = homePage.verifyPageOpened();
        Assertions.assertTrue(isOpenedPassenger);

        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);

        boolean isOpenedDriver = homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePageDriver.loginAction(DRIVER_02_EMAIL,DRIVER_PASSWORD);
        homePage.loginAction(PASSENGER_EMAIL, PASSENGER_PASSWORD);
        homePage.clickSelectDepartureIcon();
        homePage.clickOnMap(-10, 20);
        homePage.clickSelectDestinationIcon();
        homePage.clickOnMap(45, -35);
        homePage.clickRouteContinueButton();
        homePage.selectStandardVehicle();
        homePage.clickVehicleTypeContinueButton();
        homePage.addPassenger(PASSENGER_03_EMAIL);
        homePage.clickAddPassengersContinueButton();
        homePage.clickTimePickerContinueButton();

        homePageDriver.driverAcceptedRide();
        webDriver1.close();

        String message = homePage.getSearchDriverMessage();
        String expectedMessage = "Driver is on his way. Estimated time of arrival: ";
        Assertions.assertTrue(message.contains(expectedMessage));

    }
    @Test
    void createScheduledRideTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.loginAction(PASSENGER_02_EMAIL, PASSENGER_PASSWORD);
        homePage.clickSelectDepartureIcon();
        homePage.clickOnMap(-10, 20);
        homePage.clickSelectDestinationIcon();
        homePage.clickOnMap(45, -35);
        homePage.clickRouteContinueButton();
        homePage.selectStandardVehicle();
        homePage.clickVehicleTypeContinueButton();
        homePage.clickAddPassengersContinueButton();
        homePage.selectDate("FEB", "15");
        homePage.clickTimePickerContinueButton();

        String message = homePage.getSearchDriverMessage();
        String expectedMessage = "The ride has been scheduled. You will get a confirmation notification, 30 minutes before ride.";
        Assertions.assertEquals(expectedMessage, message);
    }
    @Test
    void createRideUnsuccessfulTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        homePage.loginAction(PASSENGER_EMAIL, PASSENGER_PASSWORD);

        homePage.enterDeparture("Subotica");
        homePage.pickRecommendedAddress();

        homePage.enterDestination("Kikinda");
        homePage.pickRecommendedAddress();

        homePage.clickRouteContinueButton();

        homePage.selectLuxVehicle();
        homePage.clickVehicleTypeContinueButton();
        homePage.clickAddPassengersContinueButton();
        homePage.clickTimePickerContinueButton();

        String message = homePage.getSearchDriverMessage();
        String expectedMessage = "We couldn't find available driver, please try again later.";
        Assertions.assertEquals(expectedMessage, message);
    }
    @Test
    void pickLocationWithoutMarkersTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

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

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        homePage.loginAction(ADMIN_EMAIL, ADMIN_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isAdminLoggedIn());
    }
    @Test
    void successfulPassengerLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        homePage.loginAction(PASSENGER_EMAIL, PASSENGER_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isPassengerLoggedIn());
    }

    @Test
    void successfulDriverLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        homePage.loginAction(DRIVER_EMAIL, DRIVER_PASSWORD);
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
        Assertions.assertTrue(homePage.isDriverLoggedIn());
    }

    @Test
    void unsuccessfulLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);

        boolean isOpened= homePage.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        homePage.loginAction("awpaodw@dwadpmaw.com","fdsgsfdg");
        Assertions.assertNotEquals("", homePage.getSignInErrorMessage());
    }

    @Test
    void endOfRideNoReview(){
        webDriver1 = new ChromeDriver();

        YuGoHomePage homePagePassenger = new YuGoHomePage(webDriver);
        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);
        boolean isOpened= homePagePassenger.verifyPageOpened();
        Assertions.assertTrue(isOpened);

        boolean isOpenedDriver= homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePagePassenger.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        homePageDriver.loginAction(DRIVER_EMAIL,DRIVER_PASSWORD);

        homePagePassenger.reserveRide();

        homePageDriver.driverAcceptedRide();
        homePageDriver.driverStartedRide();
        homePageDriver.driverEndedRide();
        Assertions.assertTrue(homePagePassenger.isDialogPresent());
        homePagePassenger.exitDialog();
        Assertions.assertTrue(homePagePassenger.isDialogClosed());

        webDriver1.quit();
    }

    @Test
    void pressEmptyDialog(){
        webDriver1 = new ChromeDriver();

        YuGoHomePage homePagePassenger = new YuGoHomePage(webDriver);
        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);
        boolean isOpened= homePagePassenger.verifyPageOpened();
        Assertions.assertTrue(isOpened);
        boolean isOpenedDriver= homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePagePassenger.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        homePageDriver.loginAction(DRIVER_EMAIL,DRIVER_PASSWORD);

        homePagePassenger.reserveRide();

        homePageDriver.driverAcceptedRide();
        homePageDriver.driverStartedRide();
        homePageDriver.driverEndedRide();
        Assertions.assertTrue(homePagePassenger.isDialogPresent());
        homePagePassenger.submitVehicleReview();
        Assertions.assertTrue(homePagePassenger.isVehicleButtonPresent());
        homePagePassenger.clickNextCarousel();
        homePagePassenger.submitRideReview();
        Assertions.assertTrue(homePagePassenger.isRideButtonPresent());
        homePagePassenger.exitDialog();
        Assertions.assertTrue(homePagePassenger.isDialogClosed());

        webDriver1.quit();
    }

    @Test
    void pressOnlyTextDialog(){
        webDriver1 = new ChromeDriver();

        YuGoHomePage homePagePassenger = new YuGoHomePage(webDriver);
        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);
        boolean isOpened= homePagePassenger.verifyPageOpened();
        Assertions.assertTrue(isOpened);
        boolean isOpenedDriver= homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePagePassenger.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        homePageDriver.loginAction(DRIVER_EMAIL,DRIVER_PASSWORD);

        homePagePassenger.reserveRide();

        homePageDriver.driverAcceptedRide();
        homePageDriver.driverStartedRide();
        homePageDriver.driverEndedRide();
        Assertions.assertTrue(homePagePassenger.isDialogPresent());
        homePagePassenger.enterVehicleReviewText();
        homePagePassenger.submitVehicleReview();
        Assertions.assertTrue(homePagePassenger.isVehicleButtonPresent());
        homePagePassenger.clickNextCarousel();
        homePagePassenger.enterRideReviewText();
        homePagePassenger.submitRideReview();
        Assertions.assertTrue(homePagePassenger.isRideButtonPresent());
        homePagePassenger.exitDialog();
        Assertions.assertTrue(homePagePassenger.isDialogClosed());

        webDriver1.quit();
    }

    @Test
    void pressOnlyStarDialog(){
        webDriver1 = new ChromeDriver();

        YuGoHomePage homePagePassenger = new YuGoHomePage(webDriver);
        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);
        boolean isOpened= homePagePassenger.verifyPageOpened();
        Assertions.assertTrue(isOpened);
        boolean isOpenedDriver= homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePagePassenger.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        homePageDriver.loginAction(DRIVER_EMAIL,DRIVER_PASSWORD);

        homePagePassenger.reserveRide();

        homePageDriver.driverAcceptedRide();
        homePageDriver.driverStartedRide();
        homePageDriver.driverEndedRide();
        Assertions.assertTrue(homePagePassenger.isDialogPresent());
        homePagePassenger.enterVehicleReviewStar();
        homePagePassenger.submitVehicleReview();
        Assertions.assertFalse(homePagePassenger.isVehicleButtonPresent());
        homePagePassenger.clickNextCarousel();
        homePagePassenger.enterRideReviewStar();
        homePagePassenger.submitRideReview();
        Assertions.assertFalse(homePagePassenger.isRideButtonPresent());
        homePagePassenger.exitDialog();
        Assertions.assertTrue(homePagePassenger.isDialogClosed());

        webDriver1.quit();
    }

    @Test
    void enterFullReview(){
        webDriver1 = new ChromeDriver();

        YuGoHomePage homePagePassenger = new YuGoHomePage(webDriver);
        YuGoHomePage homePageDriver = new YuGoHomePage(webDriver1);
        boolean isOpened= homePagePassenger.verifyPageOpened();
        Assertions.assertTrue(isOpened);
        boolean isOpenedDriver= homePageDriver.verifyPageOpened();
        Assertions.assertTrue(isOpenedDriver);

        homePagePassenger.loginAction(PASSENGER_EMAIL,PASSENGER_PASSWORD);
        homePageDriver.loginAction(DRIVER_EMAIL,DRIVER_PASSWORD);

        homePagePassenger.reserveRide();

        homePageDriver.driverAcceptedRide();
        homePageDriver.driverStartedRide();
        homePageDriver.driverEndedRide();
        Assertions.assertTrue(homePagePassenger.isDialogPresent());
        homePagePassenger.enterVehicleReviewStar();
        homePagePassenger.enterVehicleReviewText();
        homePagePassenger.submitVehicleReview();
        Assertions.assertFalse(homePagePassenger.isVehicleButtonPresent());
        homePagePassenger.clickNextCarousel();
        homePagePassenger.enterRideReviewStar();
        homePagePassenger.enterRideReviewText();
        homePagePassenger.submitRideReview();
        Assertions.assertFalse(homePagePassenger.isRideButtonPresent());
        homePagePassenger.exitDialog();
        Assertions.assertTrue(homePagePassenger.isDialogClosed());

        webDriver1.quit();
    }
    
}
