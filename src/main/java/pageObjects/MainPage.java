package pageObjects;

import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {
    private static final String LOGIN_TERMINAL_BODY_TEXT = "Please login to OpenTransit..!!";
    private static final String DOWNLOAD_DATA_BODY_TEXT = "Please download data..!!";
    private static final String LOGIN_SHIFT_BODY_TEXT = "Please login to start shift";
    private static final String GPS_NOT_WORKING_BODY_TEXT = "GPS is not working properly";
    private static final String ADHOC_SHIFT_ALERT_TITLE = "Adhoc Shift";
    private static final String OPEN_TRANSIT_TITLE = "OPENTRANSIT";
    private static final String TEXT_ATTRIBUTE = "text";
    private final Waiting waiting;
    private final AndroidDriver driver;

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
        driver = androidDriver;
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/toolbarText")
    private WebElement mainPageTitle;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/validationStatus")
    private WebElement bodyText;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/generalBtn")
    private WebElement loginShiftButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/text_view_dialog_title")
    private WebElement driverShiftLoginDialogue;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/driver_id")
    private WebElement driverIDField;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/driver_pin")
    private WebElement driverPinField;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/shift_waybill")
    private WebElement shiftWayBillField;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/loginButton")
    private WebElement driverShiftLoginButton;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement continueShiftButton;

    public String getMainPageTitleText() {
        waiting.waitUntilAttributeToBe(mainPageTitle, TEXT_ATTRIBUTE, OPEN_TRANSIT_TITLE);
        return waiting.getText(mainPageTitle).getText();
    }

    public String getLoginTerminalText() {
        return waiting.waitForTextToBePresentInElement(bodyText, LOGIN_TERMINAL_BODY_TEXT).getText();
    }

    public String getDownloadDataText() {
        return waiting.waitForTextToBePresentInElement(bodyText, DOWNLOAD_DATA_BODY_TEXT).getText();
    }

    public String getLoginShiftText() {
        return waiting.waitUntilAttributeToBe(bodyText, TEXT_ATTRIBUTE, LOGIN_SHIFT_BODY_TEXT).getText();
    }

    public String getGPSNotWorkingText() {
        return waiting.waitForTextToBePresentInElement(bodyText, GPS_NOT_WORKING_BODY_TEXT).getText();
    }

    public void clickLoginShiftButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.visibilityOf(loginShiftButton));
        waiting.click(loginShiftButton);
    }

    public String getDriverShiftLoginDialogueHeader() {
        return waiting.waitForElementVisibility(driverShiftLoginDialogue).getText();
    }

    public void enterDriverID(String driverID) {
        waiting.clear(driverIDField);
        waiting.sendText(driverIDField, driverID);
    }

    public void enterDriverPin(String driverPin) {
        waiting.clear(driverPinField);
        waiting.sendText(driverPinField, driverPin);
    }

    public void enterWayBill(String wayBill) {
        waiting.clear(shiftWayBillField);
        waiting.sendText(shiftWayBillField, wayBill);
    }

    public void clickDriverShiftLoginButton() {
        waiting.click(driverShiftLoginButton);
    }

    public String getAdHocAlertTitle() {
        return waiting.waitForTextToBePresentInElement(alertTitle, ADHOC_SHIFT_ALERT_TITLE).getText();
    }

    public void clickContinueShiftButton() {
        waiting.click(continueShiftButton);
    }
}