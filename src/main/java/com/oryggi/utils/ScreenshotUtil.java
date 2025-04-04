package com.oryggi.utils;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots";

    public static String captureScreenshot(WebDriver driver, String testName) {
        String filePath = SCREENSHOT_PATH + testName + ".png";
        try {
            if (driver == null) {
                System.out.println("⚠ WebDriver instance is null! Screenshot not taken.");
                return null;
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File screenshotDir = new File(SCREENSHOT_PATH);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            FileHandler.copy(srcFile, new File(filePath));
            System.out.println("📸 Screenshot saved: " + filePath);
            return filePath;

        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
        }
        return null;
    }
}
