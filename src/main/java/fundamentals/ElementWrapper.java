package fundamentals;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementWrapper {
    private final AndroidDriver driver;
    private final By locator;
    private WebElement element;

    public ElementWrapper(AndroidDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public WebElement get() {
        if (element == null || isStale()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.pollingEvery(Duration.ofNanos(5000));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
        return element;
    }

    public boolean isStale() {
        try {
            element.isEnabled();
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        }
    }
}