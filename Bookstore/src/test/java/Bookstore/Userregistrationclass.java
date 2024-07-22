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

public class Userregistrationclass {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://web-application-url");
    }

    @Test
    public void registerWithValidInputs() {
        try {
            driver.findElement(By.id("register")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("validUser");
            driver.findElement(By.id("password")).sendKeys("validPassword");
            driver.findElement(By.id("confirmPassword")).sendKeys("validPassword");
            driver.findElement(By.id("submit")).click();

            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMessage")));
            Assert.assertTrue(successMessage.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Registration with valid inputs failed: " + e.getMessage());
        }
    }

    @Test
    public void registerWithInvalidInputs() {
        try {
            driver.findElement(By.id("register")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("");
            driver.findElement(By.id("password")).sendKeys("short");
            driver.findElement(By.id("confirmPassword")).sendKeys("short");
            driver.findElement(By.id("submit")).click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
            Assert.assertTrue(errorMessage.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Registration with invalid inputs failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





