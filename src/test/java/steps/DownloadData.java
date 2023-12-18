package steps;

import cucumber.TestContext;
import fundamentals.AndroidBasics;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.DownloadDataPage;
import pageObjects.MainPage;
import pageObjects.SideMenuComponent;
import utils.AppConstants;

public class DownloadData {

    TestContext testContext;
    MainPage mainPage;
    SideMenuComponent sideMenuComponent;
    DownloadDataPage downloadDataPage;
    AndroidBasics androidBasics;

    public DownloadData(TestContext context) {
        testContext = context;
        mainPage = testContext.getPageObjectManager().getMainPage();
        sideMenuComponent = testContext.getPageObjectManager().getSideMenuComponent();
        downloadDataPage = testContext.getPageObjectManager().getDownloadDataPage();
        androidBasics = testContext.getPageObjectManager().getAndroidBasics();
    }

    @When("I navigate to the settings page")
    public void iNavigateToTheSettingsPage() {
        androidBasics.navigateBack();
    }

    @When("I navigate to the main page")
    public void iNavigateToTheMainPage() {
        androidBasics.navigateBack();
    }

    @Given("I am already logged in")
    public void iAmAlreadyLoggedIn() {

//        sideMenuComponent.clickNavigationbutton();
//        softAssert.assertEquals(sideMenuComponent.getMerchantName(), MERCHANT_NAME);
//        sideMenuComponent.loseFocus();
    }

    @And("I am on the main page")
    public void iAmOnTheMainPage() {
        SoftAssertManager.getSoftAssert().assertEquals(mainPage.getMainPageTitleText(), AppConstants.MAIN_PAGE_TITLE);
    }

    @When("I click on the download data page button")
    public void iClickOnTheDownloadDataPageButton() {
        sideMenuComponent.clickDownloadDataPageButton();
    }

    @Then("The download data page should be opened")
    public void theDownloadDataPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(downloadDataPage.getDownloadDataPageTitle(), AppConstants.DOWNLOAD_DATA_PAGE_TITLE);
    }

    @And("I click on the start download button")
    public void iClickOnTheStartDownloadButton() {
        downloadDataPage.clickStartDownloadButton();
    }

    @Then("The download should be started")
    public void theDownloadShouldBeStarted() {
        SoftAssertManager.getSoftAssert().assertEquals(downloadDataPage.getDialogueHeader(), AppConstants.DOWNLOAD_DATA_DIALOGUE_TITLE);
    }

    @And("The data should be downloaded successfully")
    public void theDataShouldBeDownloadedSuccessfully() {
        SoftAssertManager.getSoftAssert().assertEquals(downloadDataPage.getSuccessDialogueMessage(), AppConstants.DOWNLOAD_SUCCESS_MESSAGE);
    }

    @When("I click ok to accept the data download dialogue")
    public void iClickOkToAcceptTheDataDownloadDialogue() {
        downloadDataPage.clickDialogueOkButton();
    }

    @And("I turn off the wifi connectivity")
    public void iTurnOffTheWifiConnectivity() {
        androidBasics.turnOffInternetConnectivity();
    }

    @And("The data download should be unsuccessful")
    public void theDataDownloadShouldBeUnsuccessful() {
        SoftAssertManager.getSoftAssert().assertEquals(downloadDataPage.getUnsuccessfulDialogueMessage(), AppConstants.DOWNLOAD_FAILURE_MESSAGE);
    }

    @And("I turn on the wifi connectivity")
    public void iTurnOnTheWifiConnectivity() {
        androidBasics.turnOnInternetConnectivity();
    }
}
