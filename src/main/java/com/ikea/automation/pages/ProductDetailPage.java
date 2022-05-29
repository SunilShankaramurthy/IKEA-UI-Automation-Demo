package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailPage extends BaseTest {

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_product_name")
    private MobileElement detailProductName;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_regular_price")
    private MobileElement detailProductPrice;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/add_to_cart_bottom_layout")
    private MobileElement addtoCart;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/ic_back")
    private MobileElement backtoProductList;

    public String getProductName() {
        //  String name=getAttribute(detailProductName,"getText()");

        String name = detailProductName.getText();
        System.out.println("Product name is " + name);
        return name;
    }

    public String getProductPrice() {
        // String price=getAttribute(detailProductPrice,"getText()");
        String price = detailProductPrice.getText();
        System.out.println("Product name is " + price);
        return price;
    }

    public ProductDetailPage addToCart(int quantity) {
        for (int i = 1; i <= quantity; i++)
            click(addtoCart);
        return this;
    }

    public ProductListPage backToProductList() {
        click(backtoProductList);
        return new ProductListPage();
    }
}
