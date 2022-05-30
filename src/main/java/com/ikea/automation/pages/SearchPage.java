package com.ikea.automation.pages;

import com.ikea.automation.base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchPage extends BasePage {


    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/search_products_text_view")
    private MobileElement searchItem;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_text")
    private MobileElement searchItemfromList;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/back_btn")
    private MobileElement exitSearchPage;

    public ProductListPage searchItem(String itemName) {

        sendKeys(searchItem, itemName, "Enter the product name");
        click(searchItemfromList, "Select item " + itemName + " from search list");
        return new ProductListPage();
    }

    public HomePage exitSearch() throws InterruptedException {
        performTouch(140, 247, "Tap on back icon from product list page");
        click(exitSearchPage, "Tap on back arrow");
        click(exitSearchPage, "Tap on back arrow");
        return new HomePage();
    }

}

