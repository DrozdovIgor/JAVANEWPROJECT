package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{

    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL;


    private static String getFolderXpathByName (String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle (String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TILE}", article_title);
    }

    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName (String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent (
                (folder_name_xpath),
                "Can't see folder by name" + name_of_folder,
                15
        );

        this.waitForElementAndClick (
                (folder_name_xpath),
                "Can't find folder by name" + name_of_folder,
                15
        );
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent((article_xpath),"Cannot find saved article by title " +article_title, 15);
    }

    public void waitForArticleToDissappearByTitle (String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent((article_xpath),"Saved article title still present with title " +article_title, 15);
    }

    public void swipeByArticleToDelete (String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft (
                (article_xpath),
                "Can't find save article"
        );
        if (Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDissappearByTitle (article_title);
    }





}
