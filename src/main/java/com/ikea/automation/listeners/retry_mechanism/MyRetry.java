package com.ikea.automation.listeners.retry_mechanism;

import com.ikea.automation.base.DriverManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyRetry implements IRetryAnalyzer {
    private static int retryCount = 0;

    public boolean retry(ITestResult result) {
        int maxRetryCount= Integer.parseInt(System.getProperty("maxRetryCount"));
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}