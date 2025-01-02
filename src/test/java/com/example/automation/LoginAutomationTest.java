package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set the path to Edge WebDriver
        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");

        // Configure EdgeOptions to avoid session issues
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Initialize Edge WebDriver with options
        WebDriver driver = new EdgeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            // Interact with the login form
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("tomsmith");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("SuperSecretPassword!");

            WebElement loginButton = driver.findElement(By.className("radius"));
            loginButton.click();

            // Assert success message
            WebElement successMessage = driver.findElement(By.id("flash"));
            assertTrue(successMessage.getText().contains("You logged into a secure area!"));
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
