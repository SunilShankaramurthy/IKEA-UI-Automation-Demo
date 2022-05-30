package com.ikea.automation.base;

import com.aventstack.extentreports.Status;
import com.ikea.automation.reports.ExtentReport;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriverWait wait;


    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        wait = new WebDriverWait(DriverManager.getDriver(), 10);
    }

    public void waitForVisibility(MobileElement e) {
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e, String msg) {
        String txt = getAttribute(e, "text");
        ExtentReport.getTest().log(Status.INFO, msg + txt);
        return txt;
    }

    public Boolean getBool(MobileElement e, String msg) {
        waitForVisibility(e);
        Boolean result = e.isDisplayed();
        ExtentReport.getTest().log(Status.INFO, msg + result);
        return result;
    }


    public void performTouch(int xOffset, int yOffset, String msg) {
        TouchAction touchAction = new TouchAction(DriverManager.getDriver());
        touchAction.tap(PointOption.point(xOffset, yOffset)).perform();
        ExtentReport.getTest().log(Status.INFO, msg);

    }

}
