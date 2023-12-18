package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class TerminalSettingsPage extends BasePage {
    public TerminalSettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }
    private final Waiting waiting;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView")
    private WebElement terminalSettingsPageTitle;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement alertTitle;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]")
    private WebElement currentEnableTagAuthenticateStatus;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView[2]")
    private WebElement currentAutoDisableWifiStatus;

    public String getTerminalSettingsPageTitle() {
        return waiting.getText(terminalSettingsPageTitle);
    }
    public void clickEnableTagAuthenticateButton() {
        waiting.waitForTextToBePresentInList(getTerminalSettingItems(), ConstantsPage.ENABLE_TAG_AUTHENTICATE).click();
    }

    public void clickAutoDisableWifiButton() {
        waiting.waitForTextToBePresentInList(getTerminalSettingItems(), ConstantsPage.AUTO_DISABLE_WIFI).click();
    }

    public String getAlertTitle() {
        return waiting.getText(alertTitle);
    }

    public void clickDialogueNoButton() {
        waiting.waitForTextToBePresentInList(getAlertDialogueButtons(), ConstantsPage.ALERT_DIALOGUE_NO_BUTTON).click();
    }

    public String getEnableTagAuthenticateCurrentStatus() {
        return waiting.waitForElementVisibility(currentEnableTagAuthenticateStatus).getText();
    }

    public String getAutoDisableWifiCurrentStatus() {
        return waiting.waitForElementVisibility(currentAutoDisableWifiStatus).getText();
    }

    public String getTerminalSettingItems() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[%d]/android.widget.RelativeLayout/android.widget.TextView[1]";
    }

    public String getAlertDialogueButtons() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[%d]";
    }
}
