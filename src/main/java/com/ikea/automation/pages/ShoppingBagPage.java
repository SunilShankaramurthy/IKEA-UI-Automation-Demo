package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ShoppingBagPage extends BaseTest {

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_product_name")
    private MobileElement productName;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/btn_checkout")
    private MobileElement checkout;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/iv_remove")
    private MobileElement deleteItem;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/popup_btn")
    private MobileElement confirmDelete;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/empty_text")
    private MobileElement validateEmptyCart;


    public String cartList() {
        waitForVisibility(productName);
        return productName.getText();


    }

    public void checkProductQuantity() {

    }

    public ProductCheckOut productCheckout() {
        click(checkout);
        return new ProductCheckOut();
    }

    public ShoppingBagPage deleteFromCart() {
        click(deleteItem);
        return this;
    }

    public ShoppingBagPage confirmProductDeletion() {
        click(confirmDelete);
        return this;
    }

    public String validateEmptyCart() {
        return getAttribute(validateEmptyCart, "text");

    }

}
