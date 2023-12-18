package pageObjects;

import fundamentals.AndroidBasics;
import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class TripPage extends BasePage {
    private final Waiting waiting;

    AndroidBasics androidBasics;

    public TripPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
        androidBasics = new AndroidBasics(androidDriver);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView")
    private WebElement selectRoutePageText;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/textview1")
    private WebElement selectRoutePageSingleIdText;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/search_src_text")
    private WebElement searchRouteField;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/title")
    private WebElement selectRouteDirectionDialogueHeader;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/closeImage")
    private WebElement closeSelectRouteDirectionDialogueButton;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/upCL")
    private WebElement startRouteButton;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/tripId")
    private WebElement endTripTitle;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/route")
    private WebElement endTripRoute;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/btnCancel")
    private WebElement endTripCancelButton;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashTransaction")
    private WebElement endTripTotalCashTransaction;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashPassenger")
    private WebElement endTripTotalCashPassenger;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashAmount")
    private WebElement endTripTotalCashAmount;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashLessTransaction")
    private WebElement endTripTotalCashLessTransaction;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashLessPassenger")
    private WebElement endTripTotalCashLessPassenger;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalCashLessAmount")
    private WebElement endTripTotalCashLessAmount;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalFareProductTransaction")
    private WebElement endTripTotalFareProductTransaction;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalFareProductPassenger")
    private WebElement endTripTotalFareProductPassenger;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalFareProductAmount")
    private WebElement endTripTotalFareProductAmount;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalTransaction")
    private WebElement endTripTotalTransaction;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalPassenger")
    private WebElement endTripTotalPassenger;
    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalAmount")
    private WebElement endTripTotalAmount;

    public String getSelectRoutePageText() {
        return waiting.waitForElementVisibility(selectRoutePageText).getText();
    }

    public void searchForRoute(String route) {
        waiting.clear(searchRouteField);
        waiting.sendText(searchRouteField, route);
    }

    public void clickOntRoute(String route) {
        WebElement singleIdElement =  waiting.waitForElementVisibility(selectRoutePageSingleIdText);
        if (singleIdElement == null) {
            System.out.println("Single element is null");
            waiting.waitForTextToBePresentInList(routeList(), route).click();
        } else {
            System.out.println( singleIdElement.getText());
            waiting.click(singleIdElement);
        }
    }

    public String getSelectRouteDirectionDialogueHeader() {
        return waiting.waitForElementVisibility(selectRouteDirectionDialogueHeader).getText();
    }

    public void clickCloseSelectRouteDirectionDialogue() {
        waiting.click(closeSelectRouteDirectionDialogueButton);
    }

    public void startRouteButton() {
        waiting.click(startRouteButton);
    }

    public String getEndTripTitle() {
        return waiting.waitForElementVisibility(endTripTitle).getText();
    }

    public String getEndTripRoute() {
        waiting.waitForElementVisibility(endTripRoute);
//        AndroidBasics.swipeVertical();
        androidBasics.scroll(endTripCancelButton, AndroidBasics.Direction.VERTICAL);
        return waiting.waitForElementVisibility(endTripRoute).getText();
    }

    public String getTotalCashTransaction() {
        return waiting.waitForElementVisibility(endTripTotalCashTransaction).getText();
    }

    public String getTotalCashPassenger() {
        return waiting.waitForElementVisibility(endTripTotalCashPassenger).getText();
    }

    public String getTotalCashAmount() {
        return waiting.waitForElementVisibility(endTripTotalCashAmount).getText();
    }

    public String getTotalCashLessTransaction() {
        return waiting.waitForElementVisibility(endTripTotalCashLessTransaction).getText();
    }

    public String getTotalCashLessPassenger() {
        return waiting.waitForElementVisibility(endTripTotalCashLessPassenger).getText();
    }

    public String getTotalCashLessAmount() {
        return waiting.waitForElementVisibility(endTripTotalCashLessAmount).getText();
    }

    public String getTotalFareProductTransaction() {
        return waiting.waitForElementVisibility(endTripTotalFareProductTransaction).getText();
    }

    public String getTotalFareProductPassenger() {
        return waiting.waitForElementVisibility(endTripTotalFareProductPassenger).getText();
    }

    public String getTotalFareProductAmount() {
        return waiting.waitForElementVisibility(endTripTotalFareProductAmount).getText();
    }

    public String getTotalTransaction() {
        return waiting.waitForElementVisibility(endTripTotalTransaction).getText();
    }

    public String getTotalPassenger() {
        return waiting.waitForElementVisibility(endTripTotalPassenger).getText();
    }

    public String getTotalAmount() {
        return waiting.waitForElementVisibility(endTripTotalAmount).getText();
    }

    public void clickEndTripCancelButton() {
        waiting.click(endTripCancelButton);
    }

    public String routeList() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[%d]/android.widget.RelativeLayout/android.widget.TextView";
    }
}
