package steps;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.HardwareSettingsPage;
import pageObjects.SettingsPage;
import utils.AppConstants;

public class GPS {
    TestContext testContext;
    SettingsPage settingsPage;
    HardwareSettingsPage hardwareSettingsPage;

    public GPS(TestContext context) {
        testContext = context;
        settingsPage = testContext.getPageObjectManager().getSettingsPage();
        hardwareSettingsPage = testContext.getPageObjectManager().getHardwareSettingsPage();
    }

    @When("I click on the hardware settings page")
    public void iClickOnTheHardwareSettingsPage() {
        settingsPage.clickHardwareSettingsPageButton();
    }

    @Then("The hardware settings page should be opened")
    public void theHardwareSettingsPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(hardwareSettingsPage.getHardwareSettingsPageTitle(), AppConstants.HARDWARE_SETTINGS_PAGE_TITLE);
    }

    @When("I click on the gps button")
    public void iClickOnTheGpsButton() {
        hardwareSettingsPage.clickUseGPSForLocationButton();
    }

    @Then("The use GPS for location dialogue should be opened")
    public void theUseGPSForLocationDialogueShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(hardwareSettingsPage.getAlertTitle(), AppConstants.GPS_ALERT_TITLE_HEADER);
    }

    @When("I click on to set the GPS to if available button")
    public void iClickOnToSetTheGPSToIfAvailableButton() {
        hardwareSettingsPage.clickDialogueIfAvailableButton();
    }

    @Then("The GPS should be set to if available")
    public void theGPSShouldBeSetToIfAvailable() {
        SoftAssertManager.getSoftAssert().assertEquals(hardwareSettingsPage.getGPSCurrentStatus(), AppConstants.GPS_STATUS_IF_AVAILABLE);
    }
}
