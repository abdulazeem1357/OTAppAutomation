package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ShiftPage extends BasePage {
    private final Waiting waiting;

    public ShiftPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/toolbarText")
    private WebElement shiftPageTitle;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/generalBtn")
    private WebElement startTripButton;

    public String getShiftPageTitle() {
        return waiting.waitForElementVisibility(shiftPageTitle).getText();
    }

    public void clickStartTripButton() {
        waiting.click(startTripButton);
    }
}
