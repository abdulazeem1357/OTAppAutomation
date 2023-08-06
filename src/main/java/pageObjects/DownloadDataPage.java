package pageObjects;

import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DownloadDataPage extends BasePage {
    private final AndroidDriver driver;
    private static final String DOWNLOAD_SUCCESS_MESSAGE = "Data download completed: \n" + "Publication Data downloaded successfully";
    private static final String DOWNLOAD_FAILURE_MESSAGE = "Publication download failed: Unable to get publication list from server";
    private final Waiting waiting;

    public DownloadDataPage(AndroidDriver androidDriver) {
        super(androidDriver);
        driver = androidDriver;
        waiting = new Waiting(driver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/ticketHead")
    private WebElement downloadDataPageTitle;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/btnDownloadMetadata")
    private WebElement startDownloadButton;

    @AndroidFindBy(id = "android:id/progress_percent")
    private WebElement progressPercentage;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement DialogueHeader;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement dialogueButton;

    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogueMessage;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/cancelButton")
    private WebElement backButton;

    public String getDownloadDataPageTitle() {
        return waiting.getText(downloadDataPageTitle).getText();
//        return getText(downloadDataPageTitle);
    }

    public void clickStartDownloadButton() {
        waiting.click(startDownloadButton);
    }

    public String getDialogueHeader() {
        return waiting.getText(DialogueHeader).getText();
    }

    public void clickDialogueOkButton() {
        waiting.click(dialogueButton);
    }

    public String getSuccessDialogueMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
//        wait.until(ExpectedConditions.attributeToBe(progressPercentage, "text", "25%"));
//        wait.until(ExpectedConditions.attributeToBe(progressPercentage, "text", "50%"));
//        wait.until(ExpectedConditions.attributeToBe(progressPercentage, "text", "75%"));
//        wait.until(ExpectedConditions.attributeToBe(progressPercentage, "text", "99%"));
        wait.until(ExpectedConditions.textToBePresentInElement(dialogueMessage, DOWNLOAD_SUCCESS_MESSAGE));

        return waiting.waitForTextToBePresentInElement(dialogueMessage, DOWNLOAD_SUCCESS_MESSAGE).getText();
    }

    public String getUnsuccessfulDialogueMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.textToBePresentInElement(dialogueMessage, DOWNLOAD_FAILURE_MESSAGE));

        return waiting.waitForTextToBePresentInElement(dialogueMessage, DOWNLOAD_FAILURE_MESSAGE).getText();
    }

    public void clickBackButton() {
        waiting.click(backButton);
    }
}