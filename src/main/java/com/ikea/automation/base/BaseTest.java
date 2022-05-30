package com.ikea.automation.base;

import com.aventstack.extentreports.Status;
import com.ikea.automation.reports.ExtentReport;
import com.ikea.automation.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class BaseTest {

    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<Properties> properties = new ThreadLocal<Properties>();
    protected static ThreadLocal<String> platform = new ThreadLocal<String>();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
    private static AppiumDriverLocalService server;
    InputStream inputStream;
    TestUtils testUtils = new TestUtils();

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public AppiumDriver getDriver() {

        return driver.get();
    }

    public void setDriver(AppiumDriver setDriver) {

        driver.set(setDriver);
    }

    public Properties getProps() {

        return properties.get();
    }

    public void setProps(Properties setProps) {

        properties.set(setProps);
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String setPlatform) {

        platform.set(setPlatform);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String dateTime2) {
        dateTime.set(dateTime2);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String setDeviceName) {
        deviceName.set(setDeviceName);
    }

    @BeforeSuite
    public void beforeSuite() {
        server = getAppiumServerDefault();
        if (!server.isRunning()) {
            server.start();
            System.out.println("Server started");
            server.clearOutPutStreams();

        } else
            System.out.println("Appium Server is running");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    @AfterSuite
    public void afterSuite() {
        if (server.isRunning()) {
            server.stop();
            testUtils.log().info("Appium server started");

        }
    }



    @Parameters({"platformName", "platformVersion", "deviceName", "systemPort","udid","emulator"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion,
                           String deviceName, String systemPort,String udid, String emulator) throws IOException {
        AppiumDriver driver;
        setPlatform(platformName);
        setDeviceName(deviceName);

        String strFile = "logs" + File.separator + platformName + "_" + deviceName;
        File logFile = new File(strFile);
        if (!logFile.exists()) {
            logFile.mkdirs();
        }
        //route logs to separate file for each thread
        ThreadContext.put("ROUTINGKEY", strFile);
        testUtils.log().info("log path: " + strFile);


        try {
            Properties properties = new Properties();
            String propertiesFileName = "config.properties";

            testUtils.log().info("load " + propertiesFileName);
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);
            setProps(properties);


            String applicationUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps/IKEA_v1.2.7.apk";

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
            desiredCapabilities.setCapability(MobileCapabilityType.APP, applicationUrl);
            desiredCapabilities.setCapability("systemPort", systemPort);
            if(emulator.equalsIgnoreCase("true")) {
                desiredCapabilities.setCapability("avd", deviceName);
                desiredCapabilities.setCapability("avdLaunchTimeout", 300000);
            }
            URL url = new URL(properties.getProperty("appiumURL"));

            setDriver(new AppiumDriver(url, desiredCapabilities));

            testUtils.log().info("driver initialized");
        } catch (Exception e) {
            testUtils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());;
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }


    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
       testUtils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        testUtils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e, String msg) {

        String txt = getAttribute(e, "text");
        testUtils.log().info(msg + txt);
        ExtentReport.getTest().log(Status.INFO, msg + txt);
        return txt;
    }


    public void performTouch(int xOffset, int yOffset, String msg) {

        TouchAction touchAction = new TouchAction(getDriver());
        touchAction.tap(PointOption.point(xOffset, yOffset)).perform();
        testUtils.log().info(msg);
        ExtentReport.getTest().log(Status.INFO, msg);

    }

    @AfterTest
    public void afterTest() {
        if (getDriver() != null) {
            getDriver().quit();
        }

    }
}
