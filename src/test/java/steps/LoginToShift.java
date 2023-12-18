package steps;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.DownloadDataPage;
import pageObjects.MainPage;
import pageObjects.ShiftPage;
import pageObjects.SideMenuComponent;
import utils.AppConstants;

public class LoginToShift {
    TestContext testContext;
    MainPage mainPage;
    DownloadDataPage downloadDataPage;
    ShiftPage shiftPage;
    SideMenuComponent sideMenuComponent;

    public LoginToShift(TestContext context) {
        testContext = context;
        mainPage = testContext.getPageObjectManager().getMainPage();
        downloadDataPage = testContext.getPageObjectManager().getDownloadDataPage();
        shiftPage = testContext.getPageObjectManager().getShiftPage();
        sideMenuComponent = testContext.getPageObjectManager().getSideMenuComponent();
    }

    @When("I navigate to the main page from download page")
    public void iNavigateToTheMainPageFromDownloadPage() {
        downloadDataPage.clickBackButton();
    }

    @When("I click on login to shift button")
    public void iClickOnLoginToShiftButton() {
        mainPage.clickLoginShiftButton();
    }

    @Then("The driver shift login popup should be opened")
    public void theDriverShiftLoginPopupShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(mainPage.getDriverShiftLoginDialogueHeader(), AppConstants.DRIVER_SHIFT_DIALOGUE_TITLE);
    }

    @And("I enter the {string} driver ID")
    public void iEnterTheDriverID(String driverID) {
        mainPage.enterDriverID(driverID);
    }

    @And("I enter the {string} driver pin")
    public void iEnterTheDriverPin(String driverPin) {
        mainPage.enterDriverPin(driverPin);
    }

    @And("I click on the login to shift button")
    public void iClickOnTheLoginToShiftButton() {
        mainPage.clickDriverShiftLoginButton();
    }

    @Then("The adhoc shift pop up should be opened")
    public void theAdhocShiftPopUpShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(mainPage.getAdHocAlertTitle(), AppConstants.ADHOC_ALERT_TITLE);
    }

    @When("I click on the continue to adhoc shift button")
    public void iClickOnTheContinueToAdhocShiftButton() {
        mainPage.clickContinueShiftButton();
    }

    @Then("I should be logged in to the shift successfully")
    public void iShouldBeLoggedInToTheShiftSuccessfully() {
        SoftAssertManager.getSoftAssert().assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @And("The shift page should be opened")
    public void theShiftPageShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @When("I click on the end shift button")
    public void iClickOnTheEndShiftButton() {
        sideMenuComponent.clickEndShiftButton();
    }

    @Then("The shift should end")
    public void theShiftShouldEnd() {
        SoftAssertManager.getSoftAssert().assertEquals(sideMenuComponent.getEndShiftDialogueTitle(), AppConstants.END_SHIFT_DIALOGUE_TITLE);
        SoftAssertManager.getSoftAssert().assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @And("I enter the {string} wayBill")
    public void iEnterTheWayBill(String wayBill) {
        mainPage.enterWayBill(wayBill);
    }

    @Then("I should see the toast message {string} after attempting to driver shift login")
    public void iShouldSeeTheToastMessageAfterAttemptingToDriverShiftLogin(String toastMessage) {
        SoftAssertManager.getSoftAssert().assertEquals(mainPage.getToastMessage(), toastMessage);
    }

    @When("I click on the cancel adhoc shift button")
    public void iClickOnTheCancelAdhocShiftButton() {
        mainPage.clickCancelShiftButton();
    }
}