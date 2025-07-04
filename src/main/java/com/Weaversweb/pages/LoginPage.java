package com.Weaversweb.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
     // Selectors
    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";
    private final String dashboardHeader = "h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module"; // After login

    public LoginPage(Page page) {
        super(page); // Calls BasePage constructor
    }
    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password: " + password);
        fill(passwordInput, password);
    }

    public void clickLogin() {
        logger.info("Clicking login button");
        click(loginButton);
    }

    public String getDashboardText() {
        logger.info("Fetching dashboard header text");
        return getText(dashboardHeader);
    }

    public void login(String username, String password) {
        logger.info("Performing login action with provided credentials");
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    }
    

    

