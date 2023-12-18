package steps;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.LoginToTerminalPage;
import pageObjects.MainPage;
import pageObjects.SettingsPage;
import pageObjects.SideMenuComponent;
import utils.AppConstants;

public class LoginToTerminal {

    TestContext testContext;
    MainPage mainPage;
    SideMenuComponent sideMenuComponent;
    SettingsPage settingsPage;
    LoginToTerminalPage loginToTerminalPage;

    public LoginToTerminal(TestContext context) {
        testContext = context;
        mainPage = testContext.getPageObjectManager().getMainPage();
        sideMenuComponent = testContext.getPageObjectManager().getSideMenuComponent();
        settingsPage = testContext.getPageObjectManager().getSettingsPage();
        loginToTerminalPage = testContext.getPageObjectManager().getLoginToTerminalPage();
    }

    @Given("I launch the application")
    public void iLaunchTheApplication() {
    }

    @Then("The main page should be opened")
    public void theMainPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(mainPage.getMainPageTitleText(), AppConstants.MAIN_PAGE_TITLE);
    }

    @When("I click on the side menu button")
    public void iClickOnTheSideMenuButton() {
        sideMenuComponent.clickNavigationbutton();
    }

    @Then("The side menu should be opened")
    public void theSideMenuShouldBeOpened() {
//        softAssert.assertEquals(sideMenuComponent.getMerchantName(), "Merchant");
    }

    @And("I click on the settings page button")
    public void iClickOnTheSettingsPageButton() {
        sideMenuComponent.clickSettingsButton();
    }

    @Then("The admin dialogue should be opened")
    public void theAdminDialogueShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(sideMenuComponent.getAdminDialogueHeader(), AppConstants.ADMIN_DIALOGUE_TITLE);
    }

    @And("I enter the pin to access the settings page")
    public void iEnterThePinToAccessTheSettingsPage() {
        sideMenuComponent.enterPin(AppConstants.SETTINGS_PIN);
    }

    @And("I click on the verify pin button")
    public void iClickOnTheVerifyPinButton() {
        sideMenuComponent.clickVerifyButton();
    }

    @Then("The settings page should be opened")
    public void theSettingsPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(settingsPage.getSettingsPageTitle(), AppConstants.SETTINGS_PAGE_TITLE);
    }

    @When("I click on the login page button")
    public void iClickOnTheLoginPageButton() {
        settingsPage.clickLoginPageButton();
    }

    @Then("The login page should be opened")
    public void theLoginPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(loginToTerminalPage.getLoginPageTitleText(), AppConstants.LOGIN_PAGE_TITLE);
    }

    @When("I click on the User ID button")
    public void iClickOnTheUserIDButton() {
        loginToTerminalPage.clickUserIDButton();
    }

    @And("I give the terminal {string} User ID")
    public void iGiveTheTerminalUserID(String terminalUserID) {
        loginToTerminalPage.enterUserIDField(terminalUserID);
    }

    @Then("I click ok to accept the username")
    public void iClickOkToAcceptTheUsername() {
        loginToTerminalPage.clickOkButton();
    }

    @When("I click on the password button")
    public void iClickOnThePasswordButton() {
        loginToTerminalPage.clickPasswordButton();
    }

    @And("I give the terminal {string} password")
    public void iGiveTheTerminalPassword(String terminalPassword) {
        loginToTerminalPage.enterPasswordField(terminalPassword);
    }

    @Then("I click ok to accept the password")
    public void iClickOkToAcceptThePassword() {
        loginToTerminalPage.clickOkButton();
    }

    @When("I click on the terminal server button")
    public void iClickOnTheTerminalServerButton() {
        loginToTerminalPage.clickTerminalServer();
    }

    @And("I select the {string} environment")
    public void iSelectTheEnvironment(String terminalEnvironment) {
        loginToTerminalPage.selectTerminalEnvironment(terminalEnvironment);
    }

    @When("I click on the login to terminal button")
    public void iClickOnTheLoginToTerminalButton() {
        loginToTerminalPage.clickLoginButton();
    }

    @Then("I should be logged in to the terminal successfully")
    public void iShouldBeLoggedInToTheTerminalSuccessfully() {
        SoftAssertManager.getSoftAssert().assertEquals(loginToTerminalPage.getDialogueMessage(), AppConstants.LOGIN_SUCCESS_MESSAGE);
    }

    @When("I click ok to close the login to terminal dialogue")
    public void iClickOkToCloseTheLoginToTerminalDialogue() {
        loginToTerminalPage.clickOkButton();
    }

    @Given("I am on the settings page")
    public void iAmOnTheSettingsPage() {
        SoftAssertManager.getSoftAssert().assertEquals(settingsPage.getSettingsPageTitle(), AppConstants.SETTINGS_PAGE_TITLE);
    }

    @Then("I should see the message {string} after attempting to login to the terminal")
    public void iShouldSeeTheMessageAfterAttemptingToLoginToTheTerminal(String terminalLoginMessage) {
        SoftAssertManager.getSoftAssert().assertEquals(loginToTerminalPage.getDialogueMessage(), terminalLoginMessage);
    }
}