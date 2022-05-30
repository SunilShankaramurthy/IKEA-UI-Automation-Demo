package com.ikea.automation.pages;

import com.ikea.automation.base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class HomePage extends BasePage {

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/textView7")
    private MobileElement searchField;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"IKEA UAE\"])[1]")
    private MobileElement shoppingBagIcon;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/search_products_text_view")
    private MobileElement searchItem;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_text")
    private MobileElement searchItemfromList;


    public SearchPage tapOnSearchField() {
        click(searchField, "Tap on Search field");
        return new SearchPage();
    }

    public ShoppingCart tapOnCart() {
        click(shoppingBagIcon, "Tap on Shopping Bag");
        return new ShoppingCart();
    }

}
