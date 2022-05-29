package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;

public class SearchPage extends BaseTest {


    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/search_products_text_view")
    private MobileElement searchItem;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_text")
    private MobileElement searchItemfromList;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/back_btn")
    private MobileElement exitSearchPage;

    public ProductListPage searchItem(String itemName) {

        sendKeys(searchItem, itemName);
        click(searchItemfromList);
        return new ProductListPage();
    }

    public HomePage exitSearch() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(140, 247)).perform();

        click(exitSearchPage);
        click(exitSearchPage);
        return new HomePage();
    }

}

