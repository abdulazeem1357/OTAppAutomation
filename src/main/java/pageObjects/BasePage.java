package pageObjects;

import fundamental.MyLogger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//BasePage class provides common functionalities used in the application
//It is the parent class of all other Page Objects
public class BasePage {
    protected final AndroidDriver driver;

    //    It initializes the elements of the PageObject using PageFactory
    public BasePage(AndroidDriver androidDriver) {
        this.driver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    //    Button and TextField are inner classes which are used to handle buttons and text fields respectively
    //    WrapsElement interface is used to access the underlying web element
    static class Button implements WrapsElement {
        private final WebElement button;

        Button(WebElement button) {
            this.button = button;
        }

        public WebElement getWrappedElement() {
            return button;
        }

        public String getText() {
            return button.getText();
        }

        public void clickButton() {
            button.click();
        }
    }

    static class TextField implements WrapsElement {
        private final WebElement textField;

        TextField(WebElement textField) {
            this.textField = textField;
        }

        public WebElement getWrappedElement() {
            return textField;
        }

        public String getText() {
            return textField.getText();
        }
    }
}
