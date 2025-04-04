package com.oryggi.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.oryggi.base.BaseTest;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver();

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

            if (screenshotPath != null) {
                System.out.println("📸 Screenshot saved at: " + screenshotPath);
            } else {
                System.out.println("❌ Screenshot path was null. Something went wrong.");
            }
        } else {
            System.out.println("⚠ WebDriver was null. Could not capture screenshot.");
        }
    }
}
