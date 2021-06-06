package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_INPUT_BOX = "id:org.wikipedia:id/search_src_text";
        SEARCH_TITLE_RESULT1 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']";
        SEARCH_TITLE_RESULT2 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']";
        SEARCH_TITLE_RESULT3 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']";
        SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']/../*[@text='{DESCRIPTION}']";
    }


    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
