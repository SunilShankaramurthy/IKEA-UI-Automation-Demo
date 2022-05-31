package com.ikea.automation.cart;

import com.ikea.automation.base.BaseTest;
import com.ikea.automation.base.DriverManager;
import com.ikea.automation.pages.*;
import io.appium.java_client.InteractsWithApps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;


public class ShoppingBagTest extends BaseTest {
    HomePage homePage;
    SearchPage searchPage;
    ProductListPage productHome;
    ProductDetailPage productDetailPage;
    ShoppingCart shoppingBagPage;
    ProductCheckOut productcheckOut;

    @BeforeMethod
    public void beforeMethod(Method m) {
        homePage = new HomePage();
        ((InteractsWithApps) DriverManager.getDriver()).launchApp();
        System.out.println("\n************** Starting Test: " + m.getName() + " *****************\n");
    }

    @AfterMethod
    public void afterMethod() {
        ((InteractsWithApps) DriverManager.getDriver()).closeApp();
    }

    @Test
    public void compareProductDetails() throws Exception {
        try {
            SoftAssert softAssert = new SoftAssert();
            searchPage = homePage.tapOnSearchField();
            productHome = searchPage.searchItem(productDetails.getJSONObject("Product").getString("productName"));
            String productName = productHome.getProductName(0);
            String productPrice = productHome.getProductPrice(0);
            productDetailPage = productHome.openProductDetails(0);
            String productNameInDetailPage = productDetailPage.getProductName();
            String productPriceInDetailPage = productDetailPage.getProductPrice();
            softAssert.assertEquals(productNameInDetailPage, productName, "Product name is not matching");
            softAssert.assertEquals(productPriceInDetailPage, productPrice, "Product price is not matching");
            softAssert.assertAll();
        } catch (Exception e) {
            throw e;
        }
    }

     @Test
    public void validateCheckOut() {
         try {
             searchPage = homePage.tapOnSearchField();
             productHome = searchPage.searchItem(productDetails.getJSONObject("Product").getString("productName"));
             productDetailPage = productHome.openProductDetails(0);
             String productNameInDetailPage = productDetailPage.getProductName();
             productDetailPage.addToCart(productDetails.getJSONObject("Product").getInt("quantity"));
             productHome = productDetailPage.backToProductList();
             homePage = searchPage.exitSearch();
             shoppingBagPage = homePage.tapOnCart();
             String itemInCart = shoppingBagPage.cartList();
             Assert.assertEquals(itemInCart, productNameInDetailPage, "Product name is not matching ");
             Assert.assertEquals(shoppingBagPage.checkQuantity(),String.valueOf (productDetails.getJSONObject("Product").getInt("quantity")));
             productcheckOut = shoppingBagPage.productCheckout();
             productcheckOut.cancel_CheckOut();
             shoppingBagPage = productcheckOut.confirm_Cancel_Popup();
             shoppingBagPage.deleteFromCart();
             shoppingBagPage.confirmProductDeletion();
             Assert.assertTrue(shoppingBagPage.validateEmptyCart());

         } catch (Exception e) {
             throw e;
         }
    }
}
