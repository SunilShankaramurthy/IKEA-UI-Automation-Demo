package com.ikea.automation.pages;

import com.ikea.automation.base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductCheckOut extends BasePage {

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/popup_other_btn")
    private MobileElement confirmCancel;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200F\u200F\u200E\u200E\u200E\u200F\u200F\u200F\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200E\u200F\u200F\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200F\u200E\u200E\u200E\u200E\u200E\u200E\u200F\u200E\u200F\u200E\u200E\u200E\u200E\u200F\u200F\u200F\u200E\u200E\u200E\u200E\u200ENavigate up\u200E\u200F\u200E\u200E\u200F\u200E\"]")
    private MobileElement cancelCheckout;

    public ProductCheckOut cancel_CheckOut() {
        click(cancelCheckout, "Cancel item checkout ");
        return this;
    }

    public ShoppingCart confirm_Cancel_Popup() {
        click(confirmCancel, "Confirming item checkout cancel");
        return new ShoppingCart();
    }
}
