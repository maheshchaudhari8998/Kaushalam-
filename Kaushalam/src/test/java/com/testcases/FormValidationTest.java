package com.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BaseTest;
import com.utils.ScreenshotUtil;
import com.utils.TestResultLogger;

public class FormValidationTest extends BaseTest {

    @Test
    public void testFormValidation() {
        String testCase = "TestCase3";

        try {
            driver.get("https://appleid.apple.com/account");
            Thread.sleep(3000); // wait for page load
            ScreenshotUtil.captureScreenshot(driver, "form_loaded.png");

            driver.findElement(By.id("sign-in")).click();
            Thread.sleep(2000);
            ScreenshotUtil.captureScreenshot(driver, "required_fields_error.png");

            WebElement email = driver.findElement(By.id("account_name_text_field"));
            email.sendKeys("invalidEmail");
            email.sendKeys(Keys.TAB);
            Thread.sleep(1000);
            ScreenshotUtil.captureScreenshot(driver, "invalid_email.png");

            TestResultLogger.logResult(testCase, "Passed");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "error_test3.png");
            TestResultLogger.logResult(testCase, "Failed");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
