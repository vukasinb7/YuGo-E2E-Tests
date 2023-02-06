package selenium.tests;

import org.junit.jupiter.api.AfterEach;
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
}
