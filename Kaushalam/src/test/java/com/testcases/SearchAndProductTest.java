package com.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utils.BaseTest;
import com.utils.ScreenshotUtil;
import com.utils.TestResultLogger;

public class SearchAndProductTest extends BaseTest {

    @Test
    public void testSearchAndProductDetails() {
        String testCase = "TestCase1";
        try {
            driver.get("https://www.amazon.in");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Laptop", Keys.ENTER);
            ScreenshotUtil.captureScreenshot(driver, "search_results.png");

            List<WebElement> products = driver.findElements(By.cssSelector("span.a-size-medium"));
            for (int i = 0; i < 3 && i < products.size(); i++) {
                WebElement product = products.get(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
                product.click();

                for (String win : driver.getWindowHandles()) {
                    driver.switchTo().window(win);
                }

                WebElement title = driver.findElement(By.id("productTitle"));
                WebElement price = driver.findElement(By.cssSelector(".a-price .a-offscreen"));

                Assert.assertTrue(title.isDisplayed(), "Product title not displayed.");
                Assert.assertTrue(price.isDisplayed(), "Product price not displayed.");
                ScreenshotUtil.captureScreenshot(driver, "product" + (i + 1) + "_details.png");

                driver.close(); // Close current tab
                driver.switchTo().window(driver.getWindowHandles().iterator().next()); // Switch to original tab
            }

            TestResultLogger.logResult(testCase, "Passed");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "error_test1.png");
            TestResultLogger.logResult(testCase, "Failed");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}
