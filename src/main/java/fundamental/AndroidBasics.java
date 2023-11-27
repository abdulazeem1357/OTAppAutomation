package fundamental;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import pageObjects.BasePage;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AndroidBasics extends BasePage {
    private static final MyLogger logger = MyLogger.getInstance();
    private static ConnectionState connection;
    private static AndroidDriver driver;

    public AndroidBasics(AndroidDriver androidDriver) {
        super(androidDriver);
        driver = androidDriver;
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public enum Direction {
        VERTICAL,
        HORIZONTAL
    }

    // turns off the Wi-Fi connectivity
    public static void turnOffWIFI() {
        connection = driver.getConnection();
        if (connection.isWiFiEnabled()) {
            logger.logInfo("Switching Wi-Fi Off");
            driver.toggleWifi();
        } else {
            logger.logInfo("Wi-Fi is already Off");
        }
    }

    // turns on the Wi-Fi connectivity
    public static void turnOnWIFI() {
        connection = driver.getConnection();
        if (!connection.isWiFiEnabled()) {
            logger.logInfo("Switching Wi-Fi on");
            driver.toggleWifi();
        } else {
            logger.logInfo("Wi-Fi is already On");
        }
    }

    // turns off the Wi-Fi and Data connectivity
    public static void turnOffInternetConnectivity() {
        connection = driver.getConnection();
        boolean changesMade = false;

        if (connection.isWiFiEnabled() || connection.isDataEnabled()) {
            if (connection.isWiFiEnabled()) {
                logger.logInfo("Switching Wi-Fi Off");
                driver.toggleWifi();
                changesMade = true;
            } else {
                logger.logInfo("Wi-Fi is already Off");
            }
            if (connection.isDataEnabled()) {
                logger.logInfo("Switching data Off");
                driver.toggleData();
                changesMade = true;
            } else {
                logger.logInfo("Data is already Off");
            }
        } else {
            logger.logInfo("Wi-Fi and Data are already Off");
        }

        if (changesMade) {
            logger.logInfo("Waiting for connectivity to stabilize");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // turns on the Wi-Fi and Data connectivity
    public static void turnOnInternetConnectivity() {
        connection = driver.getConnection();
        boolean changesMade = false;

        if (!connection.isWiFiEnabled() || !connection.isDataEnabled()) {
            if (!connection.isWiFiEnabled()) {
                logger.logInfo("Switching Wi-Fi On");
                driver.toggleWifi();
                changesMade = true;
            } else {
                logger.logInfo("Wi-Fi is already On");
            }
            if (!connection.isDataEnabled()) {
                logger.logInfo("Switching data On");
                driver.toggleData();
                changesMade = true;
            } else {
                logger.logInfo("Data is already On");
            }
        } else {
            logger.logInfo("Wi-Fi and Data are already On");
        }

        if (changesMade) {
            logger.logInfo("Waiting for connectivity to stabilize");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                // Wait for internet connectivity to stabilize
                int retries = 2;
                while (retries > 0 && !isInternetConnected()) {
                    Thread.sleep(5000);
                    retries--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // checks if the internet is connected or not
    public static boolean isInternetConnected() {
        try {
            // Step 1: Check if you can reach a well-known IP address
            InetAddress ipAddress = InetAddress.getByName("8.8.8.8");
            if (ipAddress.isReachable(5000)) {
                // Step 2: Make an HTTP request to a known URL and check if the response code is successful
                URL url = new URL("https://www.google.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Internet is connected
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Internet is not connected
        return false;
    }

    public static void hideKeyboard() {
        driver.hideKeyboard();
    }

    public static void swipeVertical() {
        PointerInput rightThumb = new PointerInput(PointerInput.Kind.TOUCH, "rightThumb");

        Interaction moveToStart = rightThumb.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
        Interaction pressDown = rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = rightThumb.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 520, 490);
        Interaction release = rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(rightThumb, 0);

        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(release);

        driver.perform(Collections.singletonList(swipe));
    }

    public static void swipeHorizontal() {
        PointerInput rightThumb = new PointerInput(PointerInput.Kind.TOUCH, "rightThumb");

        Interaction moveToStart = rightThumb.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 960, 320);
        Interaction pressDown = rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = rightThumb.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 200, 320);
        Interaction release = rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(rightThumb, 0);

        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(release);

        driver.perform(Collections.singletonList(swipe));
    }

    public static void moveAppToBackgroundForFiveSeconds() {
        try {
            driver.runAppInBackground(Duration.ofSeconds(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scroll(WebElement element, Direction direction) {
        String previousPageSource = "";
        while (isElementNotEnabled(element) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = driver.getPageSource();
            if (direction.equals(Direction.HORIZONTAL)) {
                performHorizontalScroll();
            } else if (direction.equals(Direction.VERTICAL)) {
                performVerticalScroll();
            }
        }
    }

    private static boolean isNotEndOfPage(String previousPageSource) {
        return !previousPageSource.equals(driver.getPageSource());
    }

    private static boolean isElementNotEnabled(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private static boolean isAndroid() {
        return driver != null;
    }

    private static boolean isElementNotEnabled(By by) {
        List<WebElement> elements = driver.findElements(by);
        if (isAndroid()) {
            return elements.isEmpty();
        }
        if (!elements.isEmpty()) {
            return elements.get(0).getAttribute("visible").equalsIgnoreCase("false");
        }
        return true;
    }

    private static void performVerticalScroll() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int endX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() * 0.25);

        performScrollUsingSequence(startX, startY, endX, endY);
    }

    private static void performHorizontalScroll() {
        swipeHorizontal();
    }

    private static void performScrollUsingSequence(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "first-finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public static void checkAppForegroundStatus() {
        boolean isAppInForeground = Objects.equals(driver.currentActivity(), "com.bps.bpass.activites.appcompat.MainAppCompatTapActivity");

        if (isAppInForeground) {
            System.out.println("The App in Foreground is: " + driver.currentActivity());
        }
        else {
            try {
                driver.startActivity(new Activity("com.bps.bpass.mainpackage", "com.bps.bpass.activites.appcompat.MainAppCompatTapActivity"));
                System.out.println("App is brought to foreground: " + driver.currentActivity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}