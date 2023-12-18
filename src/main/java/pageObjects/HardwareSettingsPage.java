package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HardwareSettingsPage extends BasePage {
    public HardwareSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    private final Waiting waiting;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView")
    private WebElement hardwareSettingsPageTitle;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]")
    private WebElement currentStatusGPS;

    public String getHardwareSettingsPageTitle() {
        return waiting.getText(hardwareSettingsPageTitle);
    }

    public void clickUseGPSForLocationButton() {
        waiting.waitForTextToBePresentInList(getHardwareSettingItems(), ConstantsPage.USE_GPS_FOR_LOCATION).click();
    }

    public String getAlertTitle() {
        return waiting.getText(alertTitle);
    }

    public void clickDialogueNoButton() {
        waiting.waitForTextToBePresentInList(getAlertDialogueButtons(), ConstantsPage.ALERT_DIALOGUE_NO_BUTTON).click();
    }

    public void clickDialogueIfAvailableButton() {
        waiting.waitForTextToBePresentInList(getAlertDialogueButtons(), ConstantsPage.ALERT_DIALOGUE_IF_AVAILABLE_BUTTON).click();
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
