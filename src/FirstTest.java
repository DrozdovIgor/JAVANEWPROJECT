import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and80");
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

     @Test
       public void EX3() {

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

         waitForElementPresent( //ожидание элемента
                 By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                 "Cannot find Object-oriented programming language by Java",
                 15
         );

         waitForElementPresent( //ожидание элемента
                 By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                 "Cannot find Object-oriented programming language by Java",
                 15
         );

         waitForElementAndClear( // очистка поля
                 By.id("org.wikipedia:id/search_src_text"),
                 "Cannot find search field",
                 5

         );

         waitForElementNotPresent( // отсутствие элемента
                 By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                 "Result of search is still present on the page",
                 5
         );
     }

@Test
        public void EX4() {
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

           assertElementHasChastichniiText (
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']"),
                    "Java",
                    "Ne sopvalo"

        );

            assertElementHasChastichniiText (
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                    "Java",
                    "Ne sopvalo"
            );

            assertElementHasChastichniiText (
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']"),
                    "Java",
                    "Ne sopvalo"
            );
         }


@Test
    public void testCompareSwipeArticle (){

        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='AppImage']"),
                "Cannot find AppImage article title ",
                5
        );

         waitForElementPresent( //получение элемента по айди
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

         swipeUpToFindElement(
                 By.xpath("//*[@text='View page in browser']"),
                 "Test error message",
                 20
         );
    }

    @Test
    public void saveFirstArticleToMyList()
    {
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
                15
        );

        waitForElementPresent( //получение элемента по айди
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );


        waitForElementPresent ( //ожидание элемента
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find Got it tip overlay",
                10
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find input to set name of articles folder",
                5
        );

        String name_of_folder= "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't press OK Button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't close article, can't find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find navigation button to my list",
                5
        );

        waitForElementPresent (
                By.xpath("//*[@text='"+name_of_folder+"']"),
                "Can't find created folder",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+name_of_folder+"']"),
                            "Can't find and click created folder",
                5
        );

        waitForElementPresent (
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        swipeElementToLeft (
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find save article"
        );

        waitForElementNotPresent( // отсутствие элемента
                By.xpath("//*[@text='Java (programming language)']"),
                "Result after delete is still present on the page",
                5
        );

    }

    @Test

    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );
        String search_line = "Linkin Park Diskography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cant find anything by the request " + search_line,
                15
        );

        int amount_of_search_results = getAmountofElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

    }
@Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );
        String search_line = "saxdxsdxs";

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cant find empty results label by the request " + search_line,
                15
        );
        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line
        );

    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "Cannot find search wikipedia input ",
                5
        );
        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find  'Object-oriented programming language' topic searching by" + search_line,
                15
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of article",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of article",
                15
        );
        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can't find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground ()
    {
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

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after input ",
                15
        );
        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning background ",
                15
        );

    }

    @Test
    public void saveTwoArticles ()
    {

            waitForElementAndClick( //поиск первой статьи
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
                    15
            );

            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Cannot find button to open article options",
                    5
            );


            waitForElementPresent (
                    By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find article title",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Cannot find option to add article to reading list",
                    5
            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find Got it tip overlay",
                    10
            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Can't find input to set name of articles folder",
                    5
            );

            String name_of_folder= "Learning programming";

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    name_of_folder,
                    "Can't put text into articles folder input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Can't press OK Button",
                    5
            );

            waitForElementAndClick( //закончили поиск первой статьи
                    By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Can't close article, can't find X link",
                    5
            );


        waitForElementAndClick(  //начинаем поиск второй статьи
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
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find search wikipedia input ",
                15
        );
        String title_before_add = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cant find title of article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );


        waitForElementPresent (
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementPresent (
                By.xpath("//*[@text='" +name_of_folder+ "']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick( // добавили вторую статью в сохраненки
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick( //закончили поиск по второй статье
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't close article, can't find X link",
                5
        );

            waitForElementAndClick( // переход в сохраненки
                    By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                    "Can't find navigation button to my list",
                    5
            );

            waitForElementPresent (
                    By.xpath("//*[@text='"+name_of_folder+"']"),
                    "Can't find created folder",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//*[@text='"+name_of_folder+"']"),
                    "Can't find and click created folder",
                    5
            );

            waitForElementPresent (
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Cannot find article title",
                    15
            );

            swipeElementToLeft (
                    By.xpath("//*[@text='Java (programming language)']"),
                    "Can't find save article"
            );

                waitForElementPresent (
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find article title after add",
                15
                );

                waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find search wikipedia input ",
                15
                );

                String title_after_add = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cant find title of article",
                15
                );

              //title_after_add = "fsdadd";
                 Assert.assertEquals(
                "Article title have been change after add",
                title_before_add,
                title_after_add
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


    private WebElement assertElementHasChastichniiText (By by, String Chast_texta, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String znachenie_elementa = element.getAttribute("text");
        assertThat(Arrays.asList(znachenie_elementa), everyItem(containsString(Chast_texta)));
        return element;
    }

    protected void swipeUp (int timeOfSwipe)
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

    protected void swipeUpQuick ()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement (By by, String error_message, int max_swipes)
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

    protected void swipeElementToLeft (By by, String error_message)
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

    private int getAmountofElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountofElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + "supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
    }

    }

    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}

