package pageObjects;

import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HardwareSettingsPage extends BasePage {
    public HardwareSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    private final Waiting waiting;
    private static final String GPS_ALERT_TITLE = "Use GPS for location";
    private static final String GPS_STATUS_NO = "No";
    private static final String ALERT_DIALOGUE_NO_BUTTON = "No";
    private static final String ALERT_DIALOGUE_YES_BUTTON = "Yes";
    private static final String ALERT_DIALOGUE_IF_AVAILABLE_BUTTON = "If available";
    private static final String TEXT_ATTRIBUTE = "text";
    private static final String USE_GPS_FOR_LOCATION = "Use GPS for location";

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView")
    private WebElement hardwareSettingsPageTitle;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]")
    private WebElement currentStatusGPS;

    public String getHardwareSettingsPageTitle() {
        return waiting.waitForElementVisibility(hardwareSettingsPageTitle).getText();
    }

    public void clickUseGPSForLocationButton() {
        waiting.waitForTextToBePresentInList(getHardwareSettingItems(), USE_GPS_FOR_LOCATION).click();
    }

    public String getAlertTitle() {
        return waiting.waitUntilAttributeToBe(alertTitle, TEXT_ATTRIBUTE, GPS_ALERT_TITLE).getText();
    }

    public void clickDialogueNoButton() {
        waiting.waitForTextToBePresentInList(getAlertDialogueButtons(), ALERT_DIALOGUE_NO_BUTTON).click();
    }

    public void clickDialogueIfAvailableButton() {
        waiting.waitForTextToBePresentInList(getAlertDialogueButtons(), ALERT_DIALOGUE_IF_AVAILABLE_BUTTON).click();
    }

    public String getGPSCurrentStatus() {
        return waiting.waitForElementVisibility(currentStatusGPS).getText();
    }

    public String getHardwareSettingItems() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[%d]/android.widget.RelativeLayout/android.widget.TextView[1]";
    }

    public String getAlertDialogueButtons() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[%d]";
    }
}
