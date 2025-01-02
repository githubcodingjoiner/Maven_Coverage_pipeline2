package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set the path to Edge WebDriver (msedgedriver.exe)
        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");

        // Initialize Edge WebDriver
        WebDriver driver = new EdgeDriver();

        // Navigate to "The Internet" login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Find username field and enter username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        // Find password field and enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        // Find and click the login button
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        // Wait for the next page to load (can be replaced with WebDriverWait for better synchronization)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert the success message on the next page
        WebElement successMessage = driver.findElement(By.id("flash"));
        assertTrue(successMessage.getText().contains("You logged into a secure area!"));

        // Close the driver
        driver.quit();
    }
}
