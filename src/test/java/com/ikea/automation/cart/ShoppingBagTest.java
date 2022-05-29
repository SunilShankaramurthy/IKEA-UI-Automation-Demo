package com.ikea.automation.cart;

import com.ikea.automation.base.BaseTest;
import com.ikea.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ShoppingBagTest extends BaseTest {
    HomePage homePage;
    SearchPage searchPage;
    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    ShoppingBagPage shoppingBagPage;
    ProductCheckOut productcheckOut;


    @BeforeMethod
    public void beforeMethod(Method m) {
        homePage = new HomePage();
        ProductListPage productListPage = new ProductListPage();
        System.out.println("\n**************Starting Test: " + m.getName() + " *****************\n");
    }

    @Test
    public void validateProductDetails() throws InterruptedException {

        searchPage = homePage.tapOnSearchField();
        productListPage = searchPage.searchItem("Table");
        String productName = productListPage.getProductName(0);
        String productPrice = productListPage.getProductPrice(0);
        productDetailPage = productListPage.openProductDetails(0);
        String productNameInDetailPage = productDetailPage.getProductName();
        String productPriceInDetailPage = productDetailPage.getProductPrice();

        Assert.assertEquals(productNameInDetailPage, productName);
        Assert.assertEquals(productPriceInDetailPage, productPrice);

        productDetailPage.addToCart(2);
        productListPage = productDetailPage.backToProductList();
        homePage = searchPage.exitSearch();
        shoppingBagPage = homePage.tapOnCart();
        String itemInCart = shoppingBagPage.cartList();

        Assert.assertEquals(itemInCart, productName);

        productcheckOut = shoppingBagPage.productCheckout();
        productcheckOut.cancel_CheckOut();
        shoppingBagPage = productcheckOut.confirm_Cancel_Popup();
        shoppingBagPage.deleteFromCart();
        shoppingBagPage.confirmProductDeletion();

    }

}
