package steps;

import cucumber.TestContext;
import fundamentals.AndroidBasics;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.DayPrepPage;
import pageObjects.SideMenuComponent;
import utils.AppConstants;

public class DayPrepCheck {

    TestContext testContext;
    SideMenuComponent sideMenuComponent;
    DayPrepPage dayPrepPage;
    AndroidBasics androidBasics;

    public DayPrepCheck(TestContext context) {
        testContext = context;
        sideMenuComponent = testContext.getPageObjectManager().getSideMenuComponent();
        dayPrepPage = testContext.getPageObjectManager().getDayPrepPage();
        androidBasics = testContext.getPageObjectManager().getAndroidBasics();
    }

    @When("I send the application to the background")
    public void iSendTheApplicationToTheBackground() {
        androidBasics.moveAppToBackgroundForFiveSeconds();
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

    @And("The connectivity check should be passed")
    public void theConnectivityCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getConnectivitySuccessfulMessage(), AppConstants.CONNECTIVITY_CHECK_SUCCESS_MESSAGE);
    }

    @And("The firmware check should be passed")
    public void theFirmwareCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getFirmwareSuccessfulMessage(), AppConstants.FIRMWARE_CHECK_SUCCESS_MESSAGE);
    }

    @And("The configuration check should be passed")
    public void theConfigurationCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getConfigurationSuccessMessage(), AppConstants.CONFIGURATION_CHECK_SUCCESS_MESSAGE);
    }

    @And("The download data check should be passed")
    public void theDownloadDataCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getDownloadDataSuccessMessage(), AppConstants.DOWNLOAD_DATA_CHECK_SUCCESS_MESSAGE);
    }

    @And("The data reconciliation check should be passed")
    public void theDataReconciliationCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getDataReconciliationSuccessMessage(), AppConstants.RECONCILIATION_CHECK_SUCCESS_MESSAGE);
    }

    @And("The data purge check should be passed")
    public void theDataPurgeCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getDataPurgingSuccessMessage(), AppConstants.DATA_PURGE_CHECK_SUCCESS_MESSAGE);
    }

    @And("The logs sync check should be passed")
    public void theLogsSyncCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getLogsSyncingSuccessMessage(), AppConstants.LOGS_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The commands sync check should be passed")
    public void theCommandsSyncCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getCommandsSyncingSuccessMessage(), AppConstants.COMMANDS_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The notification sync check should be passed")
    public void theNotificationSyncCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getNotificationSyncingSuccessMessage(), AppConstants.NOTIFICATION_SYNC_CHECK_SUCCESS_MESSAGE);
    }

    @And("The hardware validation check should be passed")
    public void theHardwareValidationCheckShouldBePassed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getHardwareValidationSuccessMessage(), AppConstants.HARDWARE_VALIDATION_CHECK_SUCCESS_MESSAGE);
    }

    @When("The day prep checks should be completed successfully")
    public void theDayPrepChecksShouldBeCompletedSuccessfully() {
    }

    @And("The day prep no internet connectivity dialogue box is displayed")
    public void theDayPrepNoInternetConnectivityDialogueBoxIsDisplayed() {
        SoftAssertManager.getSoftAssert().assertEquals(dayPrepPage.getNoConnectivityDialogueMessage(), AppConstants.DAY_PREP_NO_CONNECTIVITY_MESSAGE);
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