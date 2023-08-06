package steps;

import fundamental.AndroidBasics;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.TestBase;
import utils.AppConstants;

import java.time.Duration;

public class DayPrepCheck extends TestBase {
    @When("I send the application to the background")
    public void iSendTheApplicationToTheBackground() {
        AndroidBasics.moveAppToBackgroundForFiveSeconds();
    }

    @And("I click on the day end button")
    public void iClickOnTheDayEndButton() {
        sideMenuComponent.clickdayEndButton();
    }

    @Then("The day prep page should be opened")
    public void theDayPrepPageShouldBeOpened() {
    }

    @Then("The application should come to the foreground on {string} screen")
    public void theApplicationShouldComeToTheForegroundOnScreen(String activityScreenTitle) {
    }

    @And("The connectivity check should be started")
    public void theConnectivityCheckShouldBeStarted() {
        softAssert.assertEquals(dayPrepPage.getConnectivityCheckStartMessage(), AppConstants.CONNECTIVITY_CHECK_START_MESSAGE);
    }

    @And("The connectivity check should be in progress")
    public void theConnectivityCheckShouldBeInProgress() {
        softAssert.assertEquals(dayPrepPage.getConnectivityCheckInProgressMessage(), AppConstants.CONNECTIVITY_CHECK_IN_PROGRESS_MESSAGE);
    }

    @And("The connectivity check should be passed")
    public void theConnectivityCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getConnectivitySuccessfulMessage(), AppConstants.CONNECTIVITY_CHECK_SUCCESS_MESSAGE);
    }

    @And("The firmware check should be started")
    public void theFirmwareCheckShouldBeStarted() {
        softAssert.assertEquals(dayPrepPage.getFirmwareCheckStartMessage(), AppConstants.FIRMWARE_CHECK_START_MESSAGE);
    }

    @And("The firmware check should be in progress")
    public void theFirmwareCheckShouldBeInProgress() {
        softAssert.assertEquals(dayPrepPage.getFirmwareCheckInProgressMessage(), AppConstants.FIRMWARE_CHECK_IN_PROGRESS_MESSAGE);
    }

    @And("The firmware check should be passed")
    public void theFirmwareCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getFirmwareSuccessfulMessage(), AppConstants.FIRMWARE_CHECK_SUCCESS_MESSAGE);
    }

    @And("The configuration check should be passed")
    public void theConfigurationCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getConfigurationSuccessMessage(), AppConstants.CONFIGURATION_CHECK_SUCCESS_MESSAGE);
    }

    @And("The download data check should be passed")
    public void theDownloadDataCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getDownloadDataSuccessMessage(), AppConstants.DOWNLOAD_DATA_CHECK_SUCCESS_MESSAGE);
    }

    @And("The data reconciliation check should be passed")
    public void theDataReconciliationCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getDataReconciliationSuccessMessage(), AppConstants.RECONCILIATION_CHECK_SUCCESS_MESSAGE);
    }

    @And("The data purge check should be passed")
    public void theDataPurgeCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getDataPurgingSuccessMessage(), AppConstants.DATA_PURGE_CHECK_SUCCESS_MESSAGE);
    }

    @And("The logs sync check should be passed")
    public void theLogsSyncCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getLogsSyncingSuccessMessage(), AppConstants.LOGS_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The commands sync check should be passed")
    public void theCommandsSyncCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getCommandsSyncingSuccessMessage(), AppConstants.COMMANDS_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The notification sync check should be passed")
    public void theNotificationSyncCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getNotificationSyncingSuccessMessage(), AppConstants.NOTIFICATION_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The hardware validation check should be passed")
    public void theHardwareValidationCheckShouldBePassed() {
        softAssert.assertEquals(dayPrepPage.getHardwareValidationSuccessMessage(), AppConstants.HARDWARE_VALIDATION_CHECK_SUCCESS_MESSAGE);
    }

    @When("The day prep checks should be completed successfully")
    public void theDayPrepChecksShouldBeCompletedSuccessfully() {
    }

    @And("The day prep no internet connectivity dialogue box is displayed")
    public void theDayPrepNoInternetConnectivityDialogueBoxIsDisplayed() {
        softAssert.assertEquals(dayPrepPage.getNoConnectivityDialogueMessage(), AppConstants.DAY_PREP_NO_CONNECTIVITY_MESSAGE);
    }

    @Then("I click on the day prep retry button")
    public void iClickOnTheDayPrepRetryButton() {
        dayPrepPage.clickRetryButton();
    }

    @When("I click on the continue button on the no internet day prep connectivity dialogue box")
    public void iClickOnTheContinueButtonOnTheNoInternetDayPrepConnectivityDialogueBox() {
        dayPrepPage.clickDialogueBoxContinueButton();
    }
}