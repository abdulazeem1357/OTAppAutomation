package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

public class DayPrepPage extends BasePage {

    private final Waiting waiting;

    public DayPrepPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/toolBarTitle")
    private WebElement dayPrepPageTitle;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement dialogueTitle;

    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogueMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement dialogueBoxButton1;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/retryButton")
    private WebElement retryButton;

    @AndroidFindBy(id = "android:id/progress_percent")
    private WebElement progressPercentage;

    @AndroidFindBy(accessibility = "gray_Connectivity check")
    private WebElement _connectivityCheckStart;
    private static final By connectivityCheckStart = AppiumBy.accessibilityId("gray_Connectivity check");

    @AndroidFindBy(accessibility = "gray_Firmware check")
    private WebElement _firmwareCheckStart;
    private static final By firmwareCheckStart = AppiumBy.accessibilityId("gray_Firmware check");

    @AndroidFindBy(accessibility = "gray_Configuration check")
    private WebElement configurationCheckStart;

    @AndroidFindBy(accessibility = "gray_Download Data")
    private WebElement downloadDataCheckStart;

    @AndroidFindBy(accessibility = "gray_Data reconciliation")
    private WebElement dataReconciliationCheckStart;

    @AndroidFindBy(accessibility = "gray_Data purging")
    private WebElement dataPurgingCheckStart;

    @AndroidFindBy(accessibility = "gray_Logs syncing")
    private WebElement logsSyncingCheckStart;

    @AndroidFindBy(accessibility = "gray_Commands syncing")
    private WebElement commandsSyncingCheckStart;

    @AndroidFindBy(accessibility = "gray_Notification syncing")
    private WebElement notificationSyncingCheckStart;

    @AndroidFindBy(accessibility = "gray_Hardware validation")
    private WebElement hardwareValidationCheckStart;

    @AndroidFindBy(accessibility = "blue_Connectivity check")
    private WebElement _connectivityCheckInProgress;
    private static final By connectivityCheckInProgress = AppiumBy.accessibilityId("blue_Connectivity check");

    @AndroidFindBy(accessibility = "blue_Firmware check")
    private WebElement _firmwareCheckInProgress;
    private static final By firmwareCheckInProgress = AppiumBy.accessibilityId("blue_Firmware check");

    @AndroidFindBy(accessibility = "blue_Configuration check")
    private WebElement configurationCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Download Data")
    private WebElement downloadDataCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Data reconciliation")
    private WebElement dataReconciliationCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Data purging")
    private WebElement dataPurgingCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Logs syncing")
    private WebElement logsSyncingCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Commands syncing")
    private WebElement commandsSyncingCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Notification syncing")
    private WebElement notificationSyncingCheckInProgress;

    @AndroidFindBy(accessibility = "blue_Hardware validation")
    private WebElement hardwareValidationCheckInProgress;

    @AndroidFindBy(accessibility = "green_Connectivity check")
    @CacheLookup
    private WebElement _connectivityCheckPass;
    private static final By connectivityCheckPass = AppiumBy.accessibilityId("green_Connectivity check");

    @AndroidFindBy(accessibility = "green_Firmware check")
    @CacheLookup
    private WebElement _firmwareCheckPass;
    private static final By firmwareCheckPass = AppiumBy.accessibilityId("green_Firmware check");

    @AndroidFindBy(accessibility = "green_Configuration check")
    @CacheLookup
    private WebElement _configurationCheckPass;
    private static final By configurationCheckPass = AppiumBy.accessibilityId("green_Configuration check");

    @AndroidFindBy(accessibility = "green_Download Data")
    @CacheLookup
    private WebElement _downloadDataCheckPass;
    private static final By downloadDataCheckPass = AppiumBy.accessibilityId("green_Download Data");

    @AndroidFindBy(accessibility = "green_Data reconciliation")
    @CacheLookup
    private WebElement _dataReconciliationCheckPass;
    private static final By dataReconciliationCheckPass = AppiumBy.accessibilityId("green_Data reconciliation");


    @AndroidFindBy(accessibility = "green_Data purging")
    @CacheLookup
    private WebElement _dataPurgingCheckPass;
    private static final By dataPurgingCheckPass = AppiumBy.accessibilityId("green_Data purging");


    @AndroidFindBy(accessibility = "green_Logs syncing")
    @CacheLookup
    private WebElement _logsSyncingCheckPass;
    private static final By logsSyncingCheckPass = AppiumBy.accessibilityId("green_Logs syncing");


    @AndroidFindBy(accessibility = "green_Commands syncing")
    @CacheLookup
    private WebElement _commandsSyncingCheckPass;
    private static final By commandsSyncingCheckPass = AppiumBy.accessibilityId("green_Commands syncing");


    @AndroidFindBy(accessibility = "green_Notification syncing")
    @CacheLookup
    private WebElement _notificationSyncingCheckPass;
    private static final By notificationSyncingCheckPass = AppiumBy.accessibilityId("green_Notification syncing");


    @AndroidFindBy(accessibility = "green_Hardware validation")
    @CacheLookup
    private WebElement _hardwareValidationCheckPass;
    private static final By hardwareValidationCheckPass = AppiumBy.accessibilityId("green_Hardware validation");

    public String getDayStartPageTitle() {
        return waiting.waitForTextToBePresentInElement(dayPrepPageTitle, ConstantsPage.DAY_START_CHECK_PAGE_TITLE).getText();
    }

    public String getDayEndPageTitle() {
        return waiting.waitForTextToBePresentInElement(dayPrepPageTitle, ConstantsPage.DAY_END_CHECK_PAGE_TITLE).getText();
    }

    public String getNoConnectivityDialogueMessage() {
        WebElement noConnectivityMessage = waiting.waitForTextToBePresentInElement(dialogueMessage, "Sorry cannot find data connection. Please move the bus to \"Wifi zone\".");
        return noConnectivityMessage.getText();
    }

    public void clickDialogueBoxContinueButton() {
        WebElement dialogueBoxContinueButton = waiting.waitForTextToBePresentInElement(dialogueBoxButton1, "CONTINUE");
        waiting.click(dialogueBoxContinueButton);
    }

    public void clickRetryButton() {
        waiting.click(retryButton);
    }

    public String getConnectivitySuccessfulMessage() {
        return waiting.pollForVisibilityOfElement(_connectivityCheckPass);
    }

    public String getFirmwareSuccessfulMessage() {
        return waiting.pollForVisibilityOfElement(_firmwareCheckPass);
    }

    public String getConfigurationSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_configurationCheckPass);
    }

    public String getDownloadDataSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_downloadDataCheckPass);
    }

    public String getDataReconciliationSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_dataReconciliationCheckPass);
    }

    public String getDataPurgingSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_dataPurgingCheckPass);
    }

    public String getLogsSyncingSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_logsSyncingCheckPass);
    }

    public String getCommandsSyncingSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_commandsSyncingCheckPass);
    }

    public String getNotificationSyncingSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_notificationSyncingCheckPass);
    }

    public String getHardwareValidationSuccessMessage() {
        return waiting.pollForVisibilityOfElement(_hardwareValidationCheckPass);
    }
}