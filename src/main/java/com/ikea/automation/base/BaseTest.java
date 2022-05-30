package com.ikea.automation.base;


import com.ikea.automation.utils.JsonParser;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import org.json.JSONObject;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileReader;
import java.io.InputStream;


public class BaseTest {
    public static JSONObject productDetails;
    private static AppiumDriverLocalService server;
    public WebDriverWait wait;


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
            System.out.println("Appium server started");

        }
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        DriverManager.initializeDriver();
        productDetails= JsonParser.parse("productData.json");


    }


    @AfterTest(alwaysRun = true)
    public void quit() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }
}
