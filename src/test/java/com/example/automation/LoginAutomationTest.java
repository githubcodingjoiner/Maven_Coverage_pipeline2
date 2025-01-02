package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set the path to ChromeDriver
        String chromeDriverPath = "C:\\Program Files\\chromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Configure ChromeOptions to avoid session issues
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = null;

        try {
            // Initialize Chrome WebDriver with options
            driver = new ChromeDriver(options);

            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            // Create an explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the username field to be visible and interact with the login form
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("tomsmith");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("SuperSecretPassword!");

            WebElement loginButton = driver.findElement(By.className("radius"));
            loginButton.click();

            // Wait for and assert the success message
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            assertTrue(successMessage.getText().contains("You logged into a secure area!"),
                    "Login success message not found or incorrect!");

        } catch (Exception e) {
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to ensure test failure is logged
        } finally {
            if (driver != null) {
                // Close the browser
                driver.quit();
            }
        }
    }
}
