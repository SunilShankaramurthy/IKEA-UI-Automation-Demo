package com.ikea.automation.pages;

import com.ikea.automation.base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailPage extends BasePage {

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_product_name")
    private MobileElement detailProductName;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_regular_price")
    private MobileElement detailProductPrice;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/add_to_cart_bottom_layout")
    private MobileElement addtoCart;

    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/ic_back")
    private MobileElement backtoProductList;

    public String getProductName() {
        return getText(detailProductName, "Product name in product detail page: ");
    }

    public String getProductPrice() {
        return getText(detailProductPrice, "Product price in product detail page: ");
    }

    public ProductDetailPage addToCart(int quantity) {
        for (int i = 1; i <= quantity; i++)
            click(addtoCart, "Add item quantity "+i+" to cart");
        return this;
    }

    public ProductListPage backToProductList() {
        click(backtoProductList, "Going back to product list page from product detail page");
        return new ProductListPage();
    }
}
