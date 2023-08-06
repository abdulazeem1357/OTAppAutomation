package fundamental;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// Four steps to take care of
// 1. get the element using by
// 2. wait for the element to be found
// 3. return the element if found
// 4. perform action on the element

public class Waiting {
    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final int timeout;
    private final MyLogger logger = MyLogger.getInstance();
    private String firstElementTextBeforeSwipe;

    public Waiting(AndroidDriver androidDriver) {
        this.driver = androidDriver;
        this.timeout = 20;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // waitForElementVisibility: waits for the visibility of the element in the list of elements
    public WebElement waitForElementVisibility(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.logError(String.format("TimeoutException: Elements not visible: " + e.getMessage()));
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public String pollForVisibilityOfElement(WebElement element) {
        String CONTENT_DESCRIPTION_ATTRIBUTE = "content-desc";
        return pollForVisibilityOfElement(element, 1, CONTENT_DESCRIPTION_ATTRIBUTE);
    }

    public String pollForVisibilityOfElement(WebElement element, int pollingTimeInNanos, String attributeToLookFor) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.pollingEvery(Duration.ofNanos(pollingTimeInNanos))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                )).getAttribute(attributeToLookFor);
    }

    public WebElement waitForPresenceOfElement(By element) {
        try {
            ElementWrapper elementWrapper = new ElementWrapper(driver, element);
            WebElement item = elementWrapper.get();
            System.out.println("Found Element: " + item.getText() + "Content Description: " + item.getAttribute("content-desc"));
            return item;
        } catch (Exception e) {
            logger.logError(String.format("TimeoutException: Elements not visible: " + e.getMessage()));
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // waitForElementsVisibility: waits for the visibility of the element in the list of elements
    public List<WebElement> waitForElementsVisibility(List<WebElement> elements) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            logger.logError("TimeoutException: Elements not visible: " + e.getMessage());
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // waitForTextToBePresentInElement: waits for element visibility and text to be present in element using explicit wait, then returns text of element.
    public WebElement waitForTextToBePresentInElement(WebElement element, String partialText) {
        waitForElementVisibility(element);
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, partialText));
            return element;
        } catch (Exception e) {
            logger.logError("TimeoutException: No Such Text Found in The Element: " + e.getMessage());
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // This method takes in a xpath of a list and an item to locate in the list.
    // It iterates through the list by incrementing the index in the xpath string format until it locates the desired item.
    // It then returns the item and breaks out of the loop.
    public WebElement waitForTextToBePresentInList(String itemsList, String itemToLocate) {
        int index = 1;

        while (true) {
            String xpath = String.format(itemsList, index);
            try {
                WebElement item = driver.findElement(By.xpath(xpath));
                waitForElementVisibility(item);
                String text = item.getText();
                if (text.equals(itemToLocate)) {
                    return item;
                }
                index++;
            } catch (Exception e) {
                logger.logError("TimeoutException: Item Not Found In The List: " + e.getMessage());
                break;
            }
        }
        try {
            throw new Exception("Item List not found: " + itemsList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // waitAndSwipeForElementWithDesiredTextToBeFound searches for a WebElement with the desired text in a list of WebElements,
    // swiping in the specified direction until the element is found or the end of the list is reached.
    public WebElement waitAndSwipeForElementWithDesiredTextToBeFound(String itemsList, String itemToLocate, AndroidBasics.Direction direction) {
        int index = 1;

        while (true) {
            String xpath = String.format(itemsList, index);
            WebElement item = findElementWithXPath(xpath);

            if (item != null) {
                waitForElementVisibility(item);
                String text = item.getText();
                if (text.equals(itemToLocate)) {
                    return item;
                }
                index++;
            } else {
                if (isEndOfList(itemsList)) {
                    break;
                }
                if (direction.equals(AndroidBasics.Direction.HORIZONTAL)) {
                    AndroidBasics.swipeHorizontal();
                } else if (direction.equals(AndroidBasics.Direction.VERTICAL)) {
                    AndroidBasics.swipeVertical();
                }
                index = 1;
            }
        }
        logger.logError("TimeoutException: Item Not Found In The List");
        try {
            throw new Exception("Element not found: " + itemToLocate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // findElementWithXPath finds the first WebElement that matches the provided XPath or
    // returns null if no matching elements are found.
    private WebElement findElementWithXPath(String xpath) {
        List<WebElement> itemList = driver.findElements(By.xpath(xpath));
        return itemList.isEmpty() ? null : itemList.get(0);
    }

    // isEndOFList determines if the end of the list has been reached
    // by comparing the text of the first element before and after swiping.
    private boolean isEndOfList(String itemsList) {
        String firstElementXPath = String.format(itemsList, 1);
        List<WebElement> firstElementList = driver.findElements(By.xpath(firstElementXPath));

        if (!firstElementList.isEmpty()) {
            WebElement firstElement = firstElementList.get(0);
            String firstElementTextAfterSwipe = firstElement.getText();

            if (firstElementTextBeforeSwipe != null && firstElementTextBeforeSwipe.equals(firstElementTextAfterSwipe)) {
                return true;
            } else {
                firstElementTextBeforeSwipe = firstElementTextAfterSwipe;
            }
        }
        return false;
    }

    // waitUntilAttributeToBe: waits for the provided text to be present in the provided attribute of the element
    public WebElement waitUntilAttributeToBe(WebElement element, String attribute, String textToBe) {
        waitForElementVisibility(element);
        try {
            if (wait.until(ExpectedConditions.attributeToBe(element, attribute, textToBe))) {
                return element;
            }
        } catch (Exception e) {
            logger.logError("TimeoutException: No Such Text Found in The Attribute: " + e.getMessage());
        }
        try {
            throw new Exception("Text: " + textToBe + "not found in Attribute: " + attribute + "in Element: " + element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // clear: clears the value of the element
    public WebElement clear(WebElement element) {
        waitForElementVisibility(element);
        element.clear();
        return element;
    }

    // click: clicks on the element
    public WebElement click(WebElement element) {
        waitForElementVisibility(element);
        element.click();
        return element;
    }

    // sendText: sends text to the element
    public WebElement sendText(WebElement element, String text) {
        waitForElementVisibility(element);
        element.sendKeys(text);
        return element;
    }

    // getAttribute: gets the value of the attribute for the element
    public WebElement getAttribute(WebElement element, String attribute) {
        waitForElementVisibility(element);
        element.getAttribute(attribute);
        return element;
    }

    // getText: gets the text of the element
    public WebElement getText(WebElement element) {
        waitForElementVisibility(element);
        element.getText();
        return element;
    }

    // allows to use further already provided expected conditions
    public void until(ExpectedCondition condition) {
        wait.until(condition);
    }
}