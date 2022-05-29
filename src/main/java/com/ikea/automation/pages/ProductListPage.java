package com.ikea.automation.pages;

import com.ikea.automation.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.By;

import java.util.List;

public class ProductListPage extends BaseTest {
    //  @AndroidFindAll(xpath="//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ikea.alfuttaim.store:id/rv_products\"]/android.view.ViewGroup[@index='0']")
    // private MobileElement products;


    @AndroidFindBys({
            @AndroidBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ikea.alfuttaim.store:id/rv_products\"]/android.view.ViewGroup")})
    public List<MobileElement> products;

    String itemName = "com.ikea.alfuttaim.store:id/tv_product_name";
    String itemPrice = "com.ikea.alfuttaim.store:id/tv_regular_price";
    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_product_name")
    private MobileElement productName;
    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_regular_price")
    private MobileElement productPrice;

    public String getProductName(int index) {
        waitForVisibility(productName);
        String name = products.get(index).findElement(By.id(itemName)).getText();

        System.out.println("Product name is " + name);
        return name;
    }

    public String getProductPrice(int index) {
        String price = products.get(index).findElement(By.id(itemPrice)).getText();

        System.out.println("Product name is " + price);
        return price;
    }

    public ProductDetailPage openProductDetails(int index) {
        products.get(index).click();
        return new ProductDetailPage();
    }
}
