package steps;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.SettingsPage;
import pageObjects.TerminalSettingsPage;
import utils.AppConstants;

public class TerminalSettings {
    TestContext testContext;
    SettingsPage settingsPage;
    TerminalSettingsPage terminalSettingsPage;

    public TerminalSettings(TestContext context) {
        testContext = context;
        settingsPage = testContext.getPageObjectManager().getSettingsPage();
        terminalSettingsPage = testContext.getPageObjectManager().getTerminalSettingsPage();
    }

    @When("I click on the terminal settings page button")
    public void iClickOnTheTerminalSettingsPageButton() {
        settingsPage.clickTerminalSettingsPageButton();
    }

    @Then("The terminal settings page should be opened")
    public void theTerminalSettingsPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(terminalSettingsPage.getTerminalSettingsPageTitle(), AppConstants.TERMINAL_SETTINGS_PAGE_TITLE);
    }

    @When("I click on the enable tag authenticate button")
    public void iClickOnTheEnableTagAuthenticateButton() {
        terminalSettingsPage.clickEnableTagAuthenticateButton();
    }

    @Then("The enable tag authenticate dialogue should be opened")
    public void theEnableTagAuthenticateDialogueShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(terminalSettingsPage.getAlertTitle(), AppConstants.ENABLE_TAG_AUTHENTICATE_ALERT_TITLE);
    }

    @And("I click on the no button on the tag authenticate dialogue")
    public void iClickOnTheNoButtonOnTheTagAuthenticateDialogue() {
        terminalSettingsPage.clickDialogueNoButton();
    }

    @Then("The enable tag authenticate should be set to {string}")
    public void theEnableTagAuthenticateShouldBeSetTo(String message) {
        SoftAssertManager.getSoftAssert().assertEquals(terminalSettingsPage.getEnableTagAuthenticateCurrentStatus(), message);
    }

    @When("I click on the auto disable wifi button")
    public void iClickOnTheAutoDisableWifiButton() {
        terminalSettingsPage.clickAutoDisableWifiButton();
    }

    @Then("The auto disable wifi dialogue should be opened")
    public void theAutoDisableWifiDialogueShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(terminalSettingsPage.getAlertTitle(), AppConstants.AUTO_DISABLE_WIFI_ALERT_TITLE);
    }

    @And("I click on the no button on the auto disable wifi dialogue")
    public void iClickOnTheNoButtonOnTheAutoDisableWifiDialogue() {
        terminalSettingsPage.clickDialogueNoButton();
    }

    @Then("The auto disable wifi should be set to {string}")
    public void theAutoDisableWifiShouldBeSetTo(String message) {
        SoftAssertManager.getSoftAssert().assertEquals(terminalSettingsPage.getAutoDisableWifiCurrentStatus(), message);
    }
}
