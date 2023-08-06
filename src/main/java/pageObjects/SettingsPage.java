package pageObjects;

import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {
    private final Waiting waiting;

    public SettingsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    private static final String hardwareSettingsButton = "Hardware Setting";
    private static final String loginButton = "Log In";

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView")
    private WebElement settingsPageTitle;

    public String getSettingsPageTitle() {
        return waiting.waitForElementVisibility(settingsPageTitle).getText();
    }

    public void clickLoginPageButton() {
        waiting.waitForTextToBePresentInList(getSettingsMenuItems(), loginButton).click();
    }

    public void clickHardwareSettingsPageButton() {
        waiting.waitForTextToBePresentInList(getSettingsMenuItems(), hardwareSettingsButton).click();
    }

    public String getSettingsMenuItems() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[%d]/android.widget.RelativeLayout/android.widget.TextView[1]";
    }
}
