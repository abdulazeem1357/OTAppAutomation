package pageObjects;

import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ConstantsPage extends BasePage {

    private final Waiting waiting;
    public ConstantsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/toolBarTitle")
    private WebElement pageTitle;

    public String getPageTitle() {
        return waiting.waitForElementVisibility(pageTitle).getText();
    }

}
