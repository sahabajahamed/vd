package com.Weaversweb.pages;
import com.Weaversweb.base.BasePage;
import com.Weaversweb.utils.LoggerUtil;
import com.microsoft.playwright.Page;
public class LoginPage extends BasePage {
    
     // Selectors
    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";
    private final String dashboardHeader = "h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module"; // After login

    public LoginPage(Page page) {
        super(page); // Calls BasePage constructor
    }

    public void navigateToLoginPage() {
        LoggerUtil.info("Navigating to OrangeHRM login page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void enterUsername(String username) {
         LoggerUtil.info("Entering username: " + username);
        fill(usernameInput, username);
    }

    public void enterPassword(String password) {
         LoggerUtil.info("Entering password: " + password);
        fill(passwordInput, password);
    }

    public void clickLogin() {
           LoggerUtil.info("Clicking login button");
        click(loginButton);
    }

    public String getDashboardText() {
         LoggerUtil.info("Fetching dashboard header text");
        return getText(dashboardHeader);
    }

    public void login(String username, String password) {
          LoggerUtil.info("Performing login action with provided credentials");
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    }
    

    

