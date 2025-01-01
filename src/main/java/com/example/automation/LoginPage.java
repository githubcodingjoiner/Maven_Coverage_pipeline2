package com.example.automation;

public class LoginPage {
    private String username;
    private String password;

    public LoginPage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String authenticate() {
        // Simulate authentication logic
        if ("testUser".equals(username) && "testPassword".equals(password)) {
            return "Dashboard";
        } else {
            return "Login Failed";
        }
    }
}
