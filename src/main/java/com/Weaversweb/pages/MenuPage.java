package com.Weaversweb.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.Page;

public class MenuPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public MenuPage(Page page) {
        super(page); // Calls BasePage constructor
    }


    //Selecotrs

    private final String logout = "//a[normalize-space()='Logout']";
    private final String settings = "//a[normalize-space()='Settings']";
    private final String Businessmanagement = "//a[normalize-space()='Business Management']";
    private final String usermanagement = "//a[normalize-space()='User Management']";
    private final String categorymanagement = "//a[normalize-space()='Category Management']";



   
    
   public UserManagement clickUserManagement()
   {
       click(usermanagement);
       return new UserManagement(page);
   }
    
}
