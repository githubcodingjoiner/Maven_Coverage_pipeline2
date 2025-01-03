package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginAutomationTest {

    @Autowired
    private LoginPage loginPage;

    @Test
    public void testLogin() {
        // ChromeDriver configuration
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open login page
            loginPage.openLoginPage("https://the-internet.herokuapp.com/login");

            // Enter login credentials
            loginPage.enterUsername("tomsmith");
            loginPage.enterPassword("SuperSecretPassword!");
            loginPage.clickLoginButton();

            // Verify login success
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            assertTrue(successMessage.getText().contains("You logged into a secure area!"),
                    "Login success message not found or incorrect!");

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
