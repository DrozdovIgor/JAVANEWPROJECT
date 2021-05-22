package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTION_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";



    public ArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement ()
    {
        return this.waitForElementPresent(By.id (TITLE), "Cannot find article title on page", 15);
    }

    public String getArticleTitle ()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter ()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );


        this.waitForElementPresent ( //ожидание элемента
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find article title",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find Got it tip overlay",
                10
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can't find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can't put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can't press OK Button",
                5
        );
    }

    public void closeArticle ()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't close article, can't find X link",
                5
        );
    }

    public void addMoreArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );


        this.waitForElementPresent ( //ожидание элемента
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find article title",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementPresent (
                By.xpath("//*[@text='" +name_of_folder+ "']"),
                "Cannot find article title",
                15
        );

        this.waitForElementAndClick( // добавили вторую статью в сохраненки
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find option to add article to reading list",
                5
        );


    }

    public void checkArticleTitle ()
    {
        this.assertElementPresent(By.id(TITLE),"Article has not title_element");
    }



}
