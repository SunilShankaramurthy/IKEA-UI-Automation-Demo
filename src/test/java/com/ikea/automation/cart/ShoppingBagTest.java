package com.ikea.automation.cart;

import com.ikea.automation.base.BaseTest;
import com.ikea.automation.pages.*;
import io.appium.java_client.InteractsWithApps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class ShoppingBagTest extends BaseTest {
    HomePage homePage;
    SearchPage searchPage;
    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    ShoppingBagPage shoppingBagPage;
    ProductCheckOut productcheckOut;
    SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void beforeMethod(Method m) {
        homePage = new HomePage();
        ProductListPage productListPage = new ProductListPage();
        ((InteractsWithApps) driver).launchApp();

        System.out.println("\n**************Starting Test: " + m.getName() + " *****************\n");
    }

    @AfterMethod
    public void afterMethod(){
        ((InteractsWithApps) driver).closeApp();
    }

//    @AfterTest
//    public void afterTest(){
//        driver.quit();
//    }

    @Test(priority = 0)
    public void compareProductDetails() throws InterruptedException {

        searchPage = homePage.tapOnSearchField();
        productListPage = searchPage.searchItem("Table");
        String productName = productListPage.getProductName(0);
        String productPrice = productListPage.getProductPrice(0);
        productDetailPage = productListPage.openProductDetails(0);
        String productNameInDetailPage = productDetailPage.getProductName();
        String productPriceInDetailPage = productDetailPage.getProductPrice();

        softAssert.assertEquals(productNameInDetailPage, productName);
        softAssert.assertEquals(productPriceInDetailPage, productPrice);
        softAssert.assertAll();
    }

    @Test
        public void validateCheckOut() throws InterruptedException {
        searchPage = homePage.tapOnSearchField();
        productListPage = searchPage.searchItem("Table");
        productDetailPage = productListPage.openProductDetails(0);
        String productNameInDetailPage = productDetailPage.getProductName();
        productDetailPage.addToCart(2);
        productListPage = productDetailPage.backToProductList();
        homePage = searchPage.exitSearch();
        shoppingBagPage = homePage.tapOnCart();
        String itemInCart = shoppingBagPage.cartList();
        softAssert.assertEquals(itemInCart,productNameInDetailPage);
        softAssert.assertAll();
        productcheckOut = shoppingBagPage.productCheckout();
        productcheckOut.cancel_CheckOut();
        shoppingBagPage = productcheckOut.confirm_Cancel_Popup();
        shoppingBagPage.deleteFromCart();
        shoppingBagPage.confirmProductDeletion();

    }

}
