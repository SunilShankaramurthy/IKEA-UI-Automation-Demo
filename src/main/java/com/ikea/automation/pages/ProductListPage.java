package com.ikea.automation.pages;

import com.ikea.automation.base.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductListPage extends BasePage {

    @AndroidFindBys({

            @AndroidBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ikea.alfuttaim.store:id/rv_products\"]/android.view.ViewGroup")})
    public List<MobileElement> products;

    String itemId = "com.ikea.alfuttaim.store:id/tv_product_name";
    String itemPriceId = "com.ikea.alfuttaim.store:id/tv_regular_price";

    /* Future Use */
    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_product_name")
    private MobileElement productName;
    @AndroidFindBy(id = "com.ikea.alfuttaim.store:id/tv_regular_price")
    private MobileElement productPrice;

    public String getProductName(int index) throws InterruptedException {
        return getText(products.get(index).findElement(By.id(itemId)), "Product name of the first item is: ");
    }

    public String getProductPrice(int index) {
        return getText(products.get(index).findElement(By.id(itemPriceId)),"Product price of the first item is: ");

    }

    public ProductDetailPage openProductDetails(int index) {
        click(products.get(index).findElement(By.id((itemId))), "Open the product: ");
        return new ProductDetailPage();
    }
}
