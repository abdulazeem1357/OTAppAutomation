package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

public class SideMenuComponent extends BasePage {
    private final AndroidDriver driver;
    Waiting waiting;

    public SideMenuComponent(AndroidDriver androidDriver) {
        super(androidDriver);
        this.driver = androidDriver;
        waiting = new Waiting(driver);
    }

    @AndroidFindBy(accessibility = "Open navigation drawer")
    private WebElement navigationButton;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewEntityName")
    private WebElement merchantName;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/nav_settings")
    private WebElement settingsButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/dPassET")
    private WebElement pinField;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/dVerify")
    private WebElement verifyButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/nav_dayEnd")
    private WebElement dayEndButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/nav_downloadData")
    private WebElement downloadDataPageButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/title")
    private WebElement adminDialogue;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/nav_endShift")
    private WebElement endShiftButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/nav_endTrip")
    private WebElement endTripButton;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement alertDialogueTitle;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement alertDialogueYesButton;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement alertDialogueNoButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/main_title")
    private WebElement endShiftDialogueTitle;

    public void clickNavigationbutton() {
        waiting.click(navigationButton);
    }

    public String getMerchantName() {
        return waiting.waitForElementVisibility(merchantName).getText();
    }

    public void clickSettingsButton() {
        waiting.click(settingsButton);
    }

    public void clickDownloadDataPageButton() {
        waiting.click(downloadDataPageButton);
    }

    public void enterPin(String pin) {
        waiting.clear(pinField);
        waiting.sendText(pinField, pin);
    }

    public void clickVerifyButton() {
        waiting.click(verifyButton);
    }

    public void clickdayEndButton() {
        waiting.click(dayEndButton);
    }

    public String getAdminDialogueHeader() {
        return waiting.waitForElementVisibility(adminDialogue).getText();
    }

    public void clickEndShiftButton() {
        waiting.click(endShiftButton);
    }

    public void clickEndTripButton() {
        waiting.click(endTripButton);
    }

    public void loseFocus() {
        int x = 950; // x coordinate
        int y = 950; // y coordinate

        new TouchAction(driver).tap(PointOption.point(x, y)).perform();
    }

    public String getAlertDialogueTitle() {
        return waiting.waitForElementVisibility(alertDialogueTitle).getText();
    }

    public void clickAlertDialogueYesButton() {
        waiting.click(alertDialogueYesButton);
    }

    public void clickAlertDialogueNoButton() {
        waiting.click(alertDialogueNoButton);
    }

    public String getEndShiftDialogueTitle() {
        return waiting.waitForElementVisibility(endShiftDialogueTitle).getText();
    }
}
