package com.ikea.automation.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ikea.automation.base.BaseTest;
import com.ikea.automation.reports.ExtentReport;
import com.ikea.automation.utils.TestUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class TestListener implements ITestListener {
    TestUtils testUtils = new TestUtils();

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest baseTest = new BaseTest();
        ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
                .assignCategory("Demo"+ baseTest.getPlatform() + "_" + baseTest.getDeviceName())
                .assignAuthor("Sunil Shankaramurthy");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
          //  testUtils.log().log(Level.ERROR,stringWriter.toString());;

            BaseTest base = new BaseTest();
            File file = base.getDriver().getScreenshotAs(OutputType.FILE);

            byte[] encoded = null;
            try {
                encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            ExtentReport.getTest().fail("Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
            ExtentReport.getTest().fail(result.getThrowable());
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.getReporter().flush();
    }
}
