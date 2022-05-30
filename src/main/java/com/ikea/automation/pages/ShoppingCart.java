package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ShoppingCart extends BaseTest {

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
        click(checkout, "Checkout product");
        return new ProductCheckOut();
    }

    public ShoppingCart deleteFromCart() {
        click(deleteItem, "Delete the item from cart");
        return this;
    }

    public ShoppingCart confirmProductDeletion() {
        click(confirmDelete, "Confirming deleting the item from cart");
        return this;
    }

    public String validateEmptyCart() {
        return getAttribute(validateEmptyCart, "text");

    }

}
