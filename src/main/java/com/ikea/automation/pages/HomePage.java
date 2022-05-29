package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.testng.Assert;


public class HomePage extends BaseTest {


    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/textView7")
    private MobileElement searchField;


    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"IKEA UAE\"])[1]")
    private MobileElement shoppingBagIcon;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/search_products_text_view")
    private MobileElement searchItem;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_text")
    private MobileElement searchItemfromList;


    public SearchPage tapOnSearchField() {
        click(searchField);
        return new SearchPage();

    }

    public ShoppingBagPage tapOnCart() {
        click(shoppingBagIcon);
        return new ShoppingBagPage();
    }


    public void search() throws InterruptedException {


        MobileElement productName = (MobileElement)
                driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ikea.alfuttaim.store:id/rv_products\"]/android.view.ViewGroup[@index='0']");
        // .findElement(By.id("com.ikea.alfuttaim.store:id/tv_product_name"));
        //  driver.findElementsById("com.ikea.alfuttaim.store:id/tv_product_name").get(1);
        waitForVisibility(productName);

        String listViewProductName = productName.findElement(By.id("com.ikea.alfuttaim.store:id/tv_product_name")).getText();
        String listViewProductPrice = productName.findElement(By.id("com.ikea.alfuttaim.store:id/tv_regular_price")).getText();

        System.out.println(listViewProductName + " Price " + listViewProductPrice);

        productName.click();


        MobileElement productDetailName = (MobileElement)
                driver.findElementById("com.ikea.alfuttaim.store:id/tv_product_name");

        MobileElement productDetailPrice = (MobileElement)
                driver.findElementById("com.ikea.alfuttaim.store:id/tv_regular_price");

        waitForVisibility(productDetailName);

        String productNameInDetail = productDetailName.getText();
        String productPriceInDetail = productDetailPrice.getText();

        System.out.println(productNameInDetail + " Price " + productPriceInDetail);


        Assert.assertEquals(productNameInDetail, listViewProductName);
        Assert.assertEquals(productPriceInDetail, listViewProductPrice);

////////////////////////
        MobileElement el21 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/add_to_cart_bottom_layout");
        el21.click();
        MobileElement el22 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/add_to_cart_bottom_layout");
        el22.click();

        MobileElement exitProductDetailPage = (MobileElement)
                driver.findElementById("com.ikea.alfuttaim.store:id/ic_back");

        exitProductDetailPage.click();
        Thread.sleep(5000);
////////////////////////
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(140, 247)).perform();
        Thread.sleep(5000);

        MobileElement exitSearchPage = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/back_btn");

        waitForVisibility(exitSearchPage);
        exitSearchPage.click();
        exitSearchPage.click();

        Thread.sleep(5000);


        System.out.println("Reached here");

        // MobileElement cart = (MobileElement) driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"IKEA UAE\"])[1]");

        MobileElement cart = (MobileElement) driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"IKEA UAE\"])[1]");
////////////////////////
        MobileElement cartProductName = (MobileElement)
                driver.findElementById("com.ikea.alfuttaim.store:id/tv_product_name");
        waitForVisibility(cartProductName);
        String productInCart = cartProductName.getText();

        Assert.assertEquals(productInCart, productNameInDetail);

//        MobileElement el3 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/product_qty");
//        waitForVisibility(el3);
//        el3.click();

//        MobileElement el4 = (MobileElement) driver.findElementById("android:id/numberpicker_input");
//        waitForVisibility(el4);
//        el4.click();
//
//        MobileElement el5 = (MobileElement) driver.findElementById("android:id/button1");
//        waitForVisibility(el5);
//        el5.click();
        MobileElement el15 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/btn_checkout");
        waitForVisibility(el15);
        el15.click();
        Thread.sleep(10000);

        ////////////////////////
        touchAction.tap(PointOption.point(82, 148)).perform();
        //   waitForVisibility(el16);
        Thread.sleep(3000);
        //  el16.click();
        MobileElement el17 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/popup_other_btn");
        waitForVisibility(el17);
        el17.click();
        MobileElement el18 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/iv_remove");
        waitForVisibility(el18);
        el18.click();
        MobileElement el19 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/popup_btn");
        waitForVisibility(el19);
        el19.click();
        MobileElement el20 = (MobileElement) driver.findElementById("com.ikea.alfuttaim.store:id/empty_text");
        waitForVisibility(el20);
        el20.click();
        driver.quit();

    }

}
