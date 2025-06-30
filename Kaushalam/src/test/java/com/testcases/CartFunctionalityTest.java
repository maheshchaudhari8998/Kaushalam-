package com.testcases;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BaseTest;
import com.utils.ScreenshotUtil;
import com.utils.TestResultLogger;

public class CartFunctionalityTest extends BaseTest {

    @Test
    public void testCartFunctionality() {
        String testCase = "TestCase2";
        try {
            driver.get("https://www.amazon.in");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Headphones", Keys.ENTER);

            WebElement product = driver.findElements(By.cssSelector("span.a-size-medium")).get(0);
            product.click();

            for (String win : driver.getWindowHandles()) {
                driver.switchTo().window(win);
            }

            ScreenshotUtil.captureScreenshot(driver, "product_page.png");

            WebElement title = driver.findElement(By.id("productTitle"));
            WebElement price = driver.findElement(By.cssSelector(".a-price .a-offscreen"));
            Assert.assertTrue(title.isDisplayed() && price.isDisplayed(), "Product title/price not displayed");

            driver.findElement(By.id("add-to-cart-button")).click();
            Thread.sleep(3000);

            driver.findElement(By.id("nav-cart")).click();
            ScreenshotUtil.captureScreenshot(driver, "cart_page.png");

            Assert.assertTrue(driver.getPageSource().contains("Headphones"));

            driver.findElement(By.xpath("//*[text()='Delete']")).click();
            Thread.sleep(2000);
            ScreenshotUtil.captureScreenshot(driver, "empty_cart.png");

            TestResultLogger.logResult(testCase, "Passed");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "error_test2.png");
            TestResultLogger.logResult(testCase, "Failed");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}