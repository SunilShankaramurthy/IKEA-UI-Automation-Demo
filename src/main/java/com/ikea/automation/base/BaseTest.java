package com.ikea.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties properties;
    InputStream inputStream;

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) {

        try {
            properties = new Properties();
            String propertiesFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);

            String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps/IKEA_v1.2.7.apk";
            // URL appUrl=getClass().getClassLoader().getResource(properties.getProperty("andriodApplocation"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability(MobileCapabilityType.APP, appUrl);
            caps.setCapability("avd", "Pixel_3");
            caps.setCapability("avdLaunchTimeout", 300000);
            //  caps.setCapability("appPackage","com.ikea.alfuttaim.store");
            //caps.setCapability("appActivity","com.ikea.alfuttaim.store/com.ikea.kompis.welcomescreen.WelcomeActivity");

            URL url = new URL(properties.getProperty("appiumURL"));
            driver = new AndroidDriver(url, caps);

            System.out.print("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();

    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        e.getAttribute(attribute);
        return attribute;
    }

    public void performTouch(int xOffset, int yOffset) {

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(xOffset, yOffset)).perform();


    }

    @AfterTest
    public void afterTest() {
        //    driver.quit();

    }
}
