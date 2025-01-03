package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginAutomationTest {

    @Autowired
    private WebDriver driver;

    @Autowired
    private LoginPage loginPage;

    @Test
    public void testLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open login page
            loginPage.openLoginPage("https://the-internet.herokuapp.com/login");

            // Interact with login page
            loginPage.enterUsername("tomsmith");
            loginPage.enterPassword("SuperSecretPassword!");
            loginPage.clickLoginButton();

            // Wait for success message
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
