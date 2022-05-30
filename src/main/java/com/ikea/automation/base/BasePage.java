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
import java.util.logging.Logger;

public class BasePage {
    public WebDriverWait wait;
    private static final int WAIT_TIME = 10;
    private static final Logger logger=Logger.getLogger(BasePage.class.getName());


    /**
     * Initialise page factory
     * */
    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        wait = new WebDriverWait(DriverManager.getDriver(), WAIT_TIME);
    }

    /**
     * Grouping all the commonly used mobile actions
     * */
    public void waitForVisibility(MobileElement e) {
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg + txt);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e, String msg) {
        String txt = getAttribute(e, "text");
        ExtentReport.getTest().log(Status.INFO, msg + txt);
        logger.info(msg +txt);
        return txt;
    }

    public Boolean getBool(MobileElement e, String msg) {
        waitForVisibility(e);
        Boolean result = e.isDisplayed();
        ExtentReport.getTest().log(Status.INFO, msg + result);
        logger.info(msg + result);
        return result;
    }


    public void performTouch(int xOffset, int yOffset, String msg) {
        TouchAction touchAction = new TouchAction(DriverManager.getDriver());
        touchAction.tap(PointOption.point(xOffset, yOffset)).perform();
        ExtentReport.getTest().log(Status.INFO, msg);
        logger.info(msg);

    }

}
