package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
                SEARCH_INIT_ELEMENT = "xpath://*[contains (@text, 'Search Wikipedia')]";
                SEARCH_INPUT = "xpath://*[contains (@text, 'Search…')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
                SEARCH_INPUT_BOX = "id:org.wikipedia:id/search_src_text";
                SEARCH_TITLE_RESULT1 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']";
                SEARCH_TITLE_RESULT2 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']";
                SEARCH_TITLE_RESULT3 = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']";
                SEARCH_RESULT_BY_TITLE_OR_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']/../*[@text='{DESCRIPTION}']";
    }

    public AndroidSearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }


}
