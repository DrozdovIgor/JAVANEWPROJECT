import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel API 31");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Test\\Desktop\\JavaAppiumAutomationNew\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


  @Test
    public void firstTest() {
        waitForElementAndClick( //Клик на элемент
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );

        waitForElementAndSendKeys( //ввод текста в элемент
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find X to cancel search",
                5
        );

        waitForElementPresent( //ожидание элемента
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oriented programming language by Java",
                15
        );
    }



    @Test

    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find X to cancel search",
                5
        );

        waitForElementAndClear( // очистка поля
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5

        );

       waitForElementAndClick(
               By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Search wikipedia' input",
                5
        );

        waitForElementNotPresent( // отсутствие элемента
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test

    public void testCompareArticleTitle (){

        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find search wikipedia input ",
                5
        );

        WebElement title_element = waitForElementPresent( //получение элемента по айди
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        String article_title = title_element.getAttribute("text"); // получение текста из элемента
        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );

    }


    @Test
      public void EX2()
           {
    waitForElementAndClick(
            By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
            "Cannot find search wikipedia input ",
            5
    );

     assertElementHasText (
            By.xpath("//*[contains (@text, 'Search…')]"),
            "Search…",
            "Мой первый ассерт выскочил"

    );


}




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) { //ожидание элемента
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) { //ожидание + клик
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) { //Ожидание + ввод
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds) // ожидание, что элемент отсутствует
    {
        WebDriverWait wait= new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds) // ожидание + очистка
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

   private WebElement assertElementHasText (By by, String expected_value, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String znachenie_elementa = element.getAttribute("text");
        assertEquals(error_message, znachenie_elementa, expected_value);
        //boolean equals = expected_value.equals(znachenie_elementa);
        //assertTrue(error_message, equals);
        return element;
    }
}

