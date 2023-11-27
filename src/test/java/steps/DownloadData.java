package steps;

import fundamental.AndroidBasics;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.TestBase;
import utils.AppConstants;

public class DownloadData extends TestBase {
    @When("I navigate to the settings page")
    public void iNavigateToTheSettingsPage() {
        AndroidBasics.navigateBack();
    }

    @When("I navigate to the main page")
    public void iNavigateToTheMainPage() {
        AndroidBasics.navigateBack();
    }

    @Given("I am already logged in")
    public void iAmAlreadyLoggedIn() {

//        sideMenuComponent.clickNavigationbutton();
//        softAssert.assertEquals(sideMenuComponent.getMerchantName(), MERCHANT_NAME);
//        sideMenuComponent.loseFocus();
    }

    @And("I am on the main page")
    public void iAmOnTheMainPage() {
        softAssert.assertEquals(mainPage.getMainPageTitleText(), AppConstants.MAIN_PAGE_TITLE);
    }

    @When("I click on the download data page button")
    public void iClickOnTheDownloadDataPageButton() {
        sideMenuComponent.clickDownloadDataPageButton();
    }

    @Then("The download data page should be opened")
    public void theDownloadDataPageShouldBeOpened() {
        softAssert.assertEquals(downloadDataPage.getDownloadDataPageTitle(), AppConstants.DOWNLOAD_DATA_PAGE_TITLE);
    }

    @And("I click on the start download button")
    public void iClickOnTheStartDownloadButton() {
        downloadDataPage.clickStartDownloadButton();
    }

    @Then("The download should be started")
    public void theDownloadShouldBeStarted() {
        softAssert.assertEquals(downloadDataPage.getDialogueHeader(), AppConstants.DOWNLOAD_DATA_DIALOGUE_TITLE);
    }

    @And("The data should be downloaded successfully")
    public void theDataShouldBeDownloadedSuccessfully() {
        softAssert.assertEquals(downloadDataPage.getSuccessDialogueMessage(), AppConstants.DOWNLOAD_SUCCESS_MESSAGE);
    }

    @When("I click ok to accept the data download dialogue")
    public void iClickOkToAcceptTheDataDownloadDialogue() {
        downloadDataPage.clickDialogueOkButton();
    }

    @And("I turn off the wifi connectivity")
    public void iTurnOffTheWifiConnectivity() {
        AndroidBasics.turnOffInternetConnectivity();
    }

    @And("The data download should be unsuccessful")
    public void theDataDownloadShouldBeUnsuccessful() {
        softAssert.assertEquals(downloadDataPage.getUnsuccessfulDialogueMessage(), AppConstants.DOWNLOAD_FAILURE_MESSAGE);
    }

    @And("I turn on the wifi connectivity")
    public void iTurnOnTheWifiConnectivity() {
        AndroidBasics.turnOnInternetConnectivity();
    }
}
