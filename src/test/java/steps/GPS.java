package steps;

import fundamental.AndroidBasics;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HardwareSettingsPage;
import pageObjects.SettingsPage;
import tests.TestBase;
import utils.AppConstants;

import java.io.IOException;

public class GPS extends TestBase {
    @When("I click on the hardware settings page")
    public void iClickOnTheHardwareSettingsPage() {
        settingsPage.clickHardwareSettingsPageButton();
    }

    @Then("The hardware settings page should be opened")
    public void theHardwareSettingsPageShouldBeOpened() {
        softAssert.assertEquals(hardwareSettingsPage.getHardwareSettingsPageTitle(), AppConstants.HARDWARE_SETTINGS_PAGE_TITLE);
    }

    @When("I click on the gps button")
    public void iClickOnTheGpsButton() {
        hardwareSettingsPage.clickUseGPSForLocationButton();
    }

    @Then("The use GPS for location dialogue should be opened")
    public void theUseGPSForLocationDialogueShouldBeOpened() {
        softAssert.assertEquals(hardwareSettingsPage.getAlertTitle(), AppConstants.GPS_ALERT_TITLE_HEADER);
    }

    @When("I click on to set the GPS to if available button")
    public void iClickOnToSetTheGPSToIfAvailableButton() {
        hardwareSettingsPage.clickDialogueIfAvailableButton();
    }

    @Then("The GPS should be set to if available")
    public void theGPSShouldBeSetToIfAvailable() {
        softAssert.assertEquals(hardwareSettingsPage.getGPSCurrentStatus(), AppConstants.GPS_STATUS_IF_AVAILABLE);
    }
}
