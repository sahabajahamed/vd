package com.samurai.pages.common;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.samurai.base.BasePage;

public class LoginPage extends BasePage {
    
    public LoginPage(Page page)
    {
        super(page);
    }


    private final String usernameField = "//input[@placeholder='john@acme.com']";
    private final String passwordField = "//input[@placeholder='Enter your password']";
    private final String loginButton = "//button[@type='submit']";
    private final String text = "//h1[normalize-space()='Login to Get Started']";
    private final String gotoDashborad = "//button[.='Go to Dashboard']";
    private final String toasterInvalid = "//h2[@id='swal2-title']";
               
    


    public void login(String username , String password)
    {
        fill(usernameField, username);
        fill(passwordField, password);
        click(loginButton);
        click(gotoDashborad);
    }

    public String getTxt()
    {
        String message = getText(text);
        return message;
    }

    public String getErrorMessage()
    {
      String toastXpath = "//h2[@id='swal2-title']";
        page.waitForSelector(toastXpath, new Page.WaitForSelectorOptions()
            .setTimeout(5000)
            .setState(WaitForSelectorState.VISIBLE));
        return page.locator(toastXpath).innerText().trim();
    }
    }
    

    

