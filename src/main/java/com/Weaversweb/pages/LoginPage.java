package com.Weaversweb.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
     // Selectors
    private final String usernameInput = "//input[@id='email']";
    private final String passwordInput = "//input[@id='password']";
    private final String loginButton = "//button[@type='submit']";
    private final String text ="//div[@class='auth-card']/h3";
    private final String dashboardHeader = "//div[@class='title-wrap']"; // After login
    private final String errrorMessage = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--error']";
    private final String sucessMesaage = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']";
    private final String forgotpassword = "//a[normalize-space()='Forgot Password?']";
    private final String errorText = "//p[@id='email-helper-text']";
    private final String errorMessage = "//p[@id='password-helper-text']";

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

    public String getErrorMessageText()
    {
        logger.info("Fetching Error Message text");
        return getText(errrorMessage);

    }

    public String getSucessMessage()
    {
        logger.info("Fetching SucessFully Login Message ");
        return getText(sucessMesaage);
    }

    public String getWelComeText()
    {
        logger.info("Fetching Welcone Text");
        return getText(text);
    }

    public String geterrorMessageforEmail()
    {
        return getText(errorText);
    }

    public String getErrorPasswordText()
    {
        return getText(errorMessage);
    }



    public void forgotPassword()
    {
        logger.info("Redireacting to forgot password ");
        click(forgotpassword);
        
    }

    public void login(String username, String password) {
        logger.info("Performing login action with provided credentials");
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    }
    

    

