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
        homePage.clickSignInFormButton();
        homePage.enterEmail("marko.markovic@email.com");
        homePage.enterPassword("Password123");
        homePage.clickSignInButton();
        Assertions.assertTrue(homePage.isSignOutButtonClickable());
    }

    @Test
    void unsuccessfulLoginTest(){
        YuGoHomePage homePage = new YuGoHomePage(webDriver);
        homePage.clickSignInFormButton();
        homePage.enterEmail("awpaodw@dwadpmaw.com");
        homePage.enterPassword("fdsgsfdg");
        homePage.clickSignInButton();
        Assertions.assertNotEquals("", homePage.getSignInErrorMessage());
    }
}
