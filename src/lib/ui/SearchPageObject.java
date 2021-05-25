package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{

    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains (@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "//*[contains (@text, 'Search…')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
    SEARCH_INPUT_BOX = "org.wikipedia:id/search_src_text",
    SEARCH_TITLE_RESULT1 = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']",
    SEARCH_TITLE_RESULT2 = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']",
    SEARCH_TITLE_RESULT3 = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']",
    SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'] [@text='{SUBSTRING_TITLE}'] | ..//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING_DESCRIPTION}']";

    //*[@resource-id='org.wikipedia:id/page_list_item_title'] [@text='Java'] |  ..//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Island of Indonesia']


    public SearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */

    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchTitleAndDescription (String substring_title, String substring_description)
    {
        SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL.replace("{SUBSTRING_TITLE}", substring_title);
        SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL.replace("{SUBSTRING_DESCRIPTION}", substring_description);

        return SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL;

    }



    /* TEMPLATES METHODS */

    public void initSearchInput ()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");

    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present!",5);
    }

    public void clickCancelSearch ()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button.", 5);
    }



    public void typeSearchLine (String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line, "Cannot find and type into search input",5);
    }

    public void waitForSearchResult (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles ()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cant find anything by the request ",
                15
        );
        return this.getAmountofElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel ()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);

    }

    public void assertThereIsNotResultOfSearch ()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any resuts");
    }

    public void waitForTextInSearchAndClear ()
    {
        this.waitForElementAndClear( // очистка поля
                By.id(SEARCH_INPUT_BOX),
                "Cannot find and clear search field",
                5
        );
    }

    public void waitForSearchBoxIsEmpty (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(By.xpath(search_result_xpath), "Search box is not empty ", 5);
    }

    public void checkSearch (String expected_value)
    {
        this.assertElementHasText(By.xpath(SEARCH_INPUT),expected_value,"Search input don't match with expected value");
    }

    public void checkMultipleTitleForText (String input_text)
    {
        this.assertElementHasPartOfText(By.xpath(SEARCH_TITLE_RESULT1),input_text, "Entered text " +input_text+ " is not contained in title 1");
        this.assertElementHasPartOfText(By.xpath(SEARCH_TITLE_RESULT2),input_text, "Entered text " +input_text+ " is not contained in title 2");
        this.assertElementHasPartOfText(By.xpath(SEARCH_TITLE_RESULT3),input_text, "Entered text " +input_text+ " is not contained in title 3");
    }

    public void waitForElementByTitleAndDescription (String title, String description)
    {
        String search_result_xpath_title = getResultSearchTitleAndDescription(title,description);
        this.waitForElementPresent(
                By.xpath(search_result_xpath_title),
                "Cannot find title with substring title " + title + " and substring description " + description,
                5
        );
        int size_elements = this.getAmountofElements(By.xpath(search_result_xpath_title));
        Assert.assertTrue("Elements less that 3", size_elements > 2 );

    }






}
