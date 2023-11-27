package pageObjects;

import fundamental.ElementWrapper;
import fundamental.Waiting;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DayPrepPage extends BasePage {

    private final Waiting waiting;

    private ElementWrapper elementWrapper;

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

    public String getConnectivityCheckStartMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(connectivityCheckStart);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        return null;
    }

    public String getConnectivityCheckInProgressMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(connectivityCheckInProgress);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        return null;
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

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    public String getConnectivitySuccessfulMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(connectivityCheckPass);
//        elementWrapper = new ElementWrapper(driver, connectivityCheckPass);
//        WebElement element = elementWrapper.get();
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        /*return wait.pollingEvery(Duration.ofNanos(5000))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions
                        .refreshed(ExpectedConditions
                                .presenceOfElementLocated(connectivityCheckPass)))
                .getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);*/

        return waiting.pollForVisibilityOfElement(_connectivityCheckPass);
    }

    public String getFirmwareCheckStartMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(firmwareCheckStart);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        return null;
    }

    public String getFirmwareCheckInProgressMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(firmwareCheckInProgress);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        return null;
    }

    public String getFirmwareSuccessfulMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(firmwareCheckPass);
//        elementWrapper = new ElementWrapper(driver, firmwareCheckPass);
//        WebElement element = elementWrapper.get();
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);


        /*WebElement element;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.pollingEvery(Duration.ofNanos(5000));
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(firmwareCheckPass));
            System.out.println("In Try Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        } catch (StaleElementReferenceException e) {
            wait.pollingEvery(Duration.ofNanos(5000));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(firmwareCheckPass));
            System.out.println("In Catch Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        }*/

/*        return wait.pollingEvery(Duration.ofNanos(5000))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions
                        .refreshed(ExpectedConditions
                                .presenceOfElementLocated(firmwareCheckPass)))
                .getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);*/


        /*Boolean elementPresent = wait.until(ExpectedConditions.stalenessOf(_firmwareCheckPass));
        while (!elementPresent) {
            try {
                return wait.until(ExpectedConditions.visibilityOf(_firmwareCheckPass)).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            elementPresent = wait.until(ExpectedConditions.stalenessOf(_firmwareCheckPass));
        }
        return null;*/

        return waiting.pollForVisibilityOfElement(_firmwareCheckPass);
    }

    public String getConfigurationSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(configurationCheckPass);
//        elementWrapper = new ElementWrapper(driver, configurationCheckPass);
//        WebElement element = elementWrapper.get();
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);


        /*WebElement element;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.pollingEvery(Duration.ofNanos(5000));

        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(configurationCheckPass));
            System.out.println("In Try Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        } catch (StaleElementReferenceException e) {
            wait.pollingEvery(Duration.ofNanos(5000));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(configurationCheckPass));
            System.out.println("In Catch Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        }*/

        /*return wait.pollingEvery(Duration.ofNanos(5000))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions
                        .presenceOfElementLocated(configurationCheckPass)))
                .getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);*/


        return waiting.pollForVisibilityOfElement(_configurationCheckPass);
    }

    public String getDownloadDataSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(downloadDataCheckPass);
//        elementWrapper = new ElementWrapper(driver, downloadDataCheckPass);
//        WebElement element = elementWrapper.get();
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);


        /*WebElement element;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.pollingEvery(Duration.ofNanos(5000));
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(downloadDataCheckPass));
            System.out.println("In Try Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        } catch (StaleElementReferenceException e) {
            wait.pollingEvery(Duration.ofNanos(5000));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(downloadDataCheckPass));
            System.out.println("In Catch Found Element: " + element.getText() + "Content Description: " + element.getAttribute("content-desc"));
            return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);
        }*/

       /* wait.pollingEvery(Duration.ofNanos(5000));
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(downloadDataCheckPass))).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);*/

        return waiting.pollForVisibilityOfElement(_downloadDataCheckPass);
    }

    public String getDataReconciliationSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(dataReconciliationCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(dataReconciliationCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_dataReconciliationCheckPass);

    }

    public String getDataPurgingSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(dataPurgingCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(dataPurgingCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_dataPurgingCheckPass);
    }

    public String getLogsSyncingSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(logsSyncingCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(logsSyncingCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_logsSyncingCheckPass);
    }

    public String getCommandsSyncingSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(commandsSyncingCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(commandsSyncingCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_commandsSyncingCheckPass);
    }

    public String getNotificationSyncingSuccessMessage() {
//        WebElement element = waiting.waitForPresenceOfElement(notificationSyncingCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(notificationSyncingCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_notificationSyncingCheckPass);
    }

    public String getHardwareValidationSuccessMessage() {

//        WebElement element = waiting.waitForPresenceOfElement(hardwareValidationCheckPass);
//        return element.getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

//        return retryWhileLoop(hardwareValidationCheckPass, 100).getAttribute(CONTENT_DESCRIPTION_ATTRIBUTE);

        return waiting.pollForVisibilityOfElement(_hardwareValidationCheckPass);
    }


    public boolean retryUsingWhileLoop_TryCatch(By locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofNanos(5000));
        boolean outcome = false;
        int repeat = 0;
        while (repeat <= 500) {
            try {
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(downloadDataCheckPass))).getAttribute("content-desc");
                return true;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            repeat++;
        }
        return outcome;
    }

    public WebElement retryWhileLoop(By locator, int timeToRetry) {
        WebElement foundElement = null;
        int repeat = 0;
        while (repeat <= timeToRetry) {
            try {
                foundElement = driver.findElement(locator);
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            repeat++;
        }
        if (foundElement == null) {
            throw new NotFoundException("The element was not located: " + connectivityCheckPass);
        }
        return foundElement;
    }
}