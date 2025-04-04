package com.oryggi.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.oryggi.base.BaseTest;

public class ScreenshotTest extends BaseTest {

    @Test
    public void forceFailScreenshotTest() {
        System.out.println("⚠ Intentionally failing test to verify screenshot...");
        Assert.fail("Failing test to trigger screenshot");
    }
}
