package com.ikea.automation.listeners.retry_mechanism;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry implements IRetryAnalyzer{
    private static int retryCount=0;
    private static final int maxRetryCount=2;

    public boolean retry(ITestResult result){
        if(retryCount < maxRetryCount){
            retryCount++;
            return true;
        }
        return false;
    }
}