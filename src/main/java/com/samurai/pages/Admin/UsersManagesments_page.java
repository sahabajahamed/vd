package com.samurai.pages.Admin;

import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class UsersManagesments_page extends BasePage {
    
    public UsersManagesments_page(Page page)
    {
        super(page);
    }


    private final String addAdmin = "//button[normalize-space()='ADD ADMIN']";
    private final String fullName = "//input[@id='fullName']";
    private final String email = "//input[@id='email']";
    private final String phone = "//input[@id='phone']";
    private final String role = "//div[@class='css-19bb58m']";
    private final String saveButton = "//button[normalize-space()='Save']";





    
    
}
