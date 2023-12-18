package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginToTerminalPage extends BasePage {
    private final Waiting waiting;

    public LoginToTerminalPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView")
    private WebElement loginPageTitle;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.EditText")
    private WebElement inputField;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement okButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView")
    private WebElement dialogueMessage;

    public String getLoginPageTitleText() {
        return waiting.waitForElementVisibility(loginPageTitle).getText();
    }

    public void clickUserIDButton() {
        waiting.waitForTextToBePresentInList(getLoginPageItems(), ConstantsPage.USER_ID_BUTTON).click();
    }

    public void enterUserIDField(String userID) {
        waiting.clear(inputField);
        waiting.sendText(inputField, userID);
    }

    public void clickOkButton() {
        waiting.click(okButton);
    }

    public void clickPasswordButton() {
        waiting.waitForTextToBePresentInList(getLoginPageItems(), ConstantsPage.PASSWORD_BUTTON).click();
    }

    public void enterPasswordField(String password) {
        waiting.clear(inputField);
        waiting.sendText(inputField, password);
    }

    public void clickTerminalServer() {
        waiting.waitForTextToBePresentInList(getLoginPageItems(), ConstantsPage.TERMINAL_SERVER).click();
    }

    public void selectTerminalEnvironment(String terminalEnvironment) {
        waiting.waitForTextToBePresentInList(getTerminalItems(), terminalEnvironment).click();
    }

    public void clickLoginButton() {
        waiting.waitForTextToBePresentInList(getLoginPageItems(), ConstantsPage.LOGIN_BUTTON).click();
    }

    public String getDialogueMessage() {
        return waiting.waitForElementVisibility(dialogueMessage).getText();
    }

    public String getLoginPageItems() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[%d]/android.widget.RelativeLayout/android.widget.TextView[1]";
    }

    public String getTerminalItems() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[%d]";
    }
}
