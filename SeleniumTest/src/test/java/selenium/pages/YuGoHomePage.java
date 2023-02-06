package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class YuGoHomePage {
    private static final String PAGE_URL = "http://localhost:4200/home";
    private final WebDriver webDriver;
    private final WebDriverWait waiter;
    private Actions actions;


    // ==================== Map =====================
    @FindBy(how = How.CSS, css = "body")
    private WebElement map;


    // ==================== Route picker form =====================
    @FindBy(how = How.CSS, css = "form div:nth-child(1) button")
    private WebElement selectDepartureIcon;
    @FindBy(how = How.CSS, css = "form div:nth-child(2) button")
    private WebElement selectDestinationIcon;
    @FindBy(how = How.CSS, css = "form button.mdc-button")
    private WebElement pickRouteContinueButton;
    @FindBy(how = How.CSS, css = "app-ride-pick-destination form>div:first-of-type input")
    private WebElement departureTextInputField;
    @FindBy(how = How.CSS, css = "app-ride-pick-destination form>div:nth-child(2) input")
    private WebElement destinationTextInputField;
    @FindBy(how = How.CSS, css = "app-ride-pick-destination form>div:nth-child(3)>ul>li:nth-child(1) p")
    private WebElement recommendedAddressesListItem;
    @FindBy(how = How.CSS, css = "app-ride-pick-destination h1")
    private WebElement title;


    // ===================== Pick vehicle type form =====================
    @FindBy(how = How.XPATH, xpath = "//h2[text()='LUX']/../../..")
    private WebElement luxVehicleCard;
    @FindBy(how = How.XPATH, xpath = "//h2[text()='STANDARD']/../../..")
    private WebElement standardVehicleCard;
    @FindBy(how = How.XPATH, xpath = "//h2[text()='VAN']/../../..")
    private WebElement vanVehicleCard;
    @FindBy(how = How.CSS, css = "app-ride-pick-properties button:nth-child(2)")
    private WebElement pickVehicleTypeContinueButton;

    // ==================== Add passengers form =====================
    @FindBy(how = How.CSS, css = "#addPassengersFormField input")
    private WebElement passengerInputField;
    @FindBy(how = How.CSS, css = "#addPassengerBtn")
    private WebElement addPassengerBtn;
    @FindBy(how = How.CSS, css = "app-ride-add-passengers button:nth-of-type(2)")
    private WebElement addPassengersContinueButton;
    // ==================== Time picker form =====================
    @FindBy(how = How.CSS, css = "app-ride-pick-time mat-form-field:nth-of-type(1) input")
    private WebElement datePickerField;
    @FindBy(how = How.CSS, css = "app-ride-pick-time mat-form-field:nth-of-type(2) input")
    private WebElement timePickerField;
    @FindBy(how = How.CSS, css = "app-ride-pick-time>div>div button:last-child")
    private WebElement timePickerContinueButton;
    // ==================== Login form ====================
    @FindBy(how = How.CSS, css = "#signin-form-button")
    private WebElement signInFormButton;
    @FindBy(how = How.CSS, css = "#login-item mat-form-field:nth-child(1) input")
    private WebElement emailInput;
    @FindBy(how = How.CSS, css = "#login-item mat-form-field:nth-child(2) input")
    private WebElement passwordInput;
    @FindBy(how = How.CSS, css = "#signin-button")
    private WebElement signInButton;
    @FindBy(how = How.CSS, css = "#logout-button-div a")
    private WebElement signOutButton;
    @FindBy(how = How.CSS, css = ".errorMessage")
    private WebElement signInErrorMessage;
    @FindBy(how = How.CSS, css = "app-navbar [routerlink='/users-accounts']")
    private WebElement adminNavbarUserAccounts;
    @FindBy(how = How.CSS, css = "app-navbar [routerlink='/favorite']")
    private WebElement passengerNavbarFavourite;
    @FindBy(how = How.CSS, css = "mat-button-toggle-group")
    private WebElement driverOnlineButton;

    // ==================== Searching driver ====================
    @FindBy(how = How.XPATH, xpath = "//app-searching-driver-screen/div/h1")
    private WebElement searchDriverMessage;

    // ===================== Driver ====================


    @FindBy(how = How.CSS, css = "#accept-ride-button")
    private WebElement acceptRideButton;
    @FindBy(how = How.CSS, css = "#start-ride-driver")
    private WebElement startRideButton;
    @FindBy(how = How.CSS, css = "#end-ride-driver")
    private WebElement endRideButton;


    // ==================== Passenger ====================
    @FindBy(how = How.CSS, css = "app-history-review-card-passenger")
    private WebElement reviewDialog;

    @FindBy(how = How.CSS, css = "#submit-vehicle")
    private WebElement vehicleReviewSubmit;
    @FindBy(how = How.CSS, css = "#submit-ride")
    private WebElement rideReviewSubmit;
    @FindBy(how = How.CSS, css = "#vehicle-review-textarea")
    private WebElement vehicleTextArea;
    @FindBy(how = How.CSS, css = "#ride-review-textarea")
    private WebElement rideTextArea;
    @FindBy(how = How.CSS, css = "#l2v")
    private WebElement vehicleStar;
    @FindBy(how = How.CSS, css = "#l2r")
    private WebElement rideStar;
    @FindBy(how = How.CSS, css = "#click-next")
    private WebElement nextCarousel;


    public YuGoHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        waiter = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        webDriver.get(PAGE_URL);
        PageFactory.initElements(webDriver, this);
    }
    public void loginAction(String email, String password) {
        clickSignInFormButton();
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }

    public boolean isAdminLoggedIn(){
        return waiter.until(ExpectedConditions.elementToBeClickable(adminNavbarUserAccounts)).isDisplayed();
    }
    public boolean isPassengerLoggedIn(){
        return waiter.until(ExpectedConditions.elementToBeClickable(passengerNavbarFavourite)).isDisplayed();
    }
    public boolean isDriverLoggedIn(){
        return waiter.until(ExpectedConditions.elementToBeClickable(driverOnlineButton)).isDisplayed();
    }
    public boolean verifyPageOpened(){
        boolean isOpened = (new WebDriverWait(webDriver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.textToBePresentInElement(title, "Where can we pick you up?"));

        return isOpened;
    }

    public void clickSelectDepartureIcon(){
        waiter.until(ExpectedConditions.elementToBeClickable(selectDepartureIcon)).click();
    }
    public void clickSelectDestinationIcon(){
        waiter.until(ExpectedConditions.elementToBeClickable(selectDestinationIcon)).click();
    }
    public void clickOnMap(int xOffset, int yOffset){
        actions.moveToElement(map).moveByOffset(xOffset, yOffset).click().perform();
    }
    public void clickRouteContinueButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(pickRouteContinueButton)).click();
    }
    public void selectLuxVehicle(){
        waiter.until(ExpectedConditions.elementToBeClickable(luxVehicleCard)).click();
    }
    public void clickVehicleTypeContinueButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(pickVehicleTypeContinueButton)).click();
    }

    public void clickSignInFormButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(signInFormButton)).click();
    }
    public void enterEmail(String email){
        waiter.until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(email);
    }
    public void enterPassword(String password){
        waiter.until(ExpectedConditions.elementToBeClickable(passwordInput)).sendKeys(password);
    }
    public void clickSignInButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
    public boolean isSignOutButtonClickable(){
        return waiter.until(ExpectedConditions.elementToBeClickable(signOutButton)).isDisplayed();
    }
    public String getSignInErrorMessage(){
        return waiter.until(ExpectedConditions.elementToBeClickable(signInErrorMessage)).getText();
    }
    public void addPassenger(String email){
        waiter.until(ExpectedConditions.elementToBeClickable(passengerInputField)).sendKeys(email);
        waiter.until(ExpectedConditions.elementToBeClickable(addPassengerBtn)).click();
    }
    public void clickAddPassengersContinueButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(addPassengersContinueButton)).click();
    }
    public void enterDeparture(String departure){
        waiter.until(ExpectedConditions.elementToBeClickable(departureTextInputField)).sendKeys(departure);
        waiter.until(ExpectedConditions.elementToBeClickable(departureTextInputField)).sendKeys(Keys.ENTER);
    }
    public void enterDestination(String departure){
        waiter.until(ExpectedConditions.elementToBeClickable(destinationTextInputField)).sendKeys(departure);
        waiter.until(ExpectedConditions.elementToBeClickable(destinationTextInputField)).sendKeys(Keys.ENTER);
    }
    public String pickRecommendedAddress(){
        String output = waiter.until(ExpectedConditions.visibilityOf(recommendedAddressesListItem)).getText();
        waiter.until(ExpectedConditions.visibilityOf(recommendedAddressesListItem)).click();
        return output;
    }
    public String getDepartureAddressText(){
        return waiter.until(ExpectedConditions.visibilityOf(departureTextInputField)).getAttribute("value");
    }
    public String getDestinationAddressText(){
        return waiter.until(ExpectedConditions.visibilityOf(destinationTextInputField)).getAttribute("value");
    }
    public void clickTimePickerContinueButton(){
        waiter.until(ExpectedConditions.elementToBeClickable(timePickerContinueButton)).click();
    }
    public String getSearchDriverMessage(){
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return waiter.until(ExpectedConditions.visibilityOf(searchDriverMessage)).getAttribute("value");
    }

    public void driverOnlineIfNeeded(){
        if (!driverOnlineButton.getAttribute("aria-pressed").equals("true"))
            driverOnlineButton.click();
    }
    public void driverAcceptedRide(){
        waiter.until(ExpectedConditions.elementToBeClickable(acceptRideButton)).click();
    }

    public void driverStartedRide(){
        waiter.until(ExpectedConditions.elementToBeClickable(startRideButton)).click();
    }
    public void driverEndedRide(){
        waiter.until(ExpectedConditions.elementToBeClickable(endRideButton)).click();
    }

    public boolean isDialogPresent(){
        return waiter.until(ExpectedConditions.elementToBeClickable(reviewDialog)).isDisplayed();
    }
    public boolean isDialogClosed(){
        return webDriver.findElements(By.id("app-history-review-card-passenger")).size() == 0;
    }

    public void exitDialog(){
        Actions actions= new Actions(webDriver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void submitVehicleReview(){
        waiter.until(ExpectedConditions.elementToBeClickable(vehicleReviewSubmit)).click();
    }

    public boolean isVehicleButtonPresent(){
        return  vehicleReviewSubmit.isDisplayed();
    }

    public void submitRideReview(){
        waiter.until(ExpectedConditions.elementToBeClickable(rideReviewSubmit)).click();
    }

    public boolean isRideButtonPresent(){
        return rideReviewSubmit.isDisplayed();
    }



    public void enterVehicleReviewText(){
        waiter.until(ExpectedConditions.elementToBeClickable(vehicleTextArea)).sendKeys("Recenzija");
    }

    public void enterRideReviewText(){
        waiter.until(ExpectedConditions.elementToBeClickable(rideTextArea)).sendKeys("Recenzija");
    }

    public void enterVehicleReviewStar(){
        waiter.until(ExpectedConditions.elementToBeClickable(vehicleStar)).click();
    }

    public void enterRideReviewStar(){
        waiter.until(ExpectedConditions.elementToBeClickable(rideStar)).click();
    }

    public void clickNextCarousel(){
        waiter.until(ExpectedConditions.elementToBeClickable(nextCarousel)).click();
    }

    public void reserveRide(){
        enterDeparture("Crvena Cesma Sremska Mitrovica");
        pickRecommendedAddress();

        enterDestination("Milosa Obilica Sremska Mitrovica");
        pickRecommendedAddress();
        clickRouteContinueButton();
        selectLuxVehicle();
        clickVehicleTypeContinueButton();
        addPassenger("darko.darkovic@email.com");
        clickAddPassengersContinueButton();
        clickTimePickerContinueButton();
    }

}
