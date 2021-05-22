package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MainPageObject
{
    protected AppiumDriver driver;


    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) { //ожидание элемента
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);

    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) { //ожидание + клик
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) { //Ожидание + ввод
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds) // ожидание, что элемент отсутствует
    {
        WebDriverWait wait= new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds) // ожидание + очистка
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText (By by, String expected_value, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String znachenie_elementa = element.getAttribute("text");
        assertEquals(error_message, znachenie_elementa, expected_value);
        //boolean equals = expected_value.equals(znachenie_elementa);
        //assertTrue(error_message, equals);
        return element;
    }


    public WebElement assertElementHasPartOfText(By by, String Chast_texta, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String znachenie_elementa = element.getAttribute("text");
        assertThat(Arrays.asList(znachenie_elementa), everyItem(containsString(Chast_texta)));
        return element;
    }

    public void swipeUp (int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.
                press(x,start_y).
                waitAction(timeOfSwipe).
                moveTo(x, end_y).
                release().
                perform();
    }

    public void swipeUpQuick ()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement (By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size()==0) {

            if (already_swiped>max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft (By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message,10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();

    }

    public int getAmountofElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountofElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + "supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent (By by, String error_message)
    {
        if (driver.findElements(by).size()==0) {
            throw new AssertionError(error_message);
        }
        else
        {WebElement element= driver.findElement(by);}

    }

    public void checkRotationBeforeTest (ScreenOrientation orientation, String error_message)
    {
        ScreenOrientation current_orientation = driver.getOrientation();
        if ((orientation.equals(current_orientation))==false) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        };


    }

}