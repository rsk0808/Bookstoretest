package Bookstore;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Userdashboardclass {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
       
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://web-application-url");
        login();
    }

    public void login() {
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void testDataDisplay() {
        try {
            WebElement dataElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("data")));
            Assert.assertTrue(dataElement.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Data display test failed: " + e.getMessage());
        }
    }

    @Test
    public void testNavigation() {
        try {
            driver.findElement(By.id("navButton")).click();
            WebElement newPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPage")));
            Assert.assertTrue(newPage.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Navigation test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



