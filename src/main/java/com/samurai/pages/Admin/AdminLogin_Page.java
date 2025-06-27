package com.samurai.pages.Admin;

import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class AdminLogin_Page extends BasePage {

    public AdminLogin_Page(Page page)
    {
        super(page);
    }
    

    private final String username = "//input[@id='Email']";
    private final String password = "//input[@id='Password']";
    private final String loginButton = "//button[normalize-space()='login']";




    public Dashboard_page adminLogin(String user, String pass)
    {
        fill( username,user);
        fill(password,pass) ;
        click(loginButton);

        return new Dashboard_page(page);
    }

    
}
