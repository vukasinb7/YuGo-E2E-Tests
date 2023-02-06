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
    // ==================== Pick vehicle type form =====================
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

    // ==================== Searching driver ====================
    @FindBy(how = How.XPATH, xpath = "//app-searching-driver-screen/div/h1")
    private WebElement searchDriverMessage;

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
}
