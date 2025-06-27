package com.samurai.pages.Admin;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class SchoolMangement_page extends BasePage {
     Faker faker = new Faker();

    public SchoolMangement_page(Page page)
    {
        super(page);
    }
    
    private final String addSchool = "//button[normalize-space()='ADD School']";    
    private final String schoolName = "//input[@id='School Name']";
    private final String contactNumber = "//input[@id='Contact Number']";
    private final String region = "//div[@id='region']";
    private final String hrmFullName = "//input[@id='Full Name ']";
    private final String email = "//input[@id='Email']";
    private final String phone = "//input[@id='Phone']";
    private final String SaveButton = "//button[normalize-space()='Save']";


//Add School 
    public void click_add_School()
    {
        click(addSchool);
    }

    public void Enter_School_Name(String text)
    {
        fill(schoolName, text);
    }

    public void Enter_Contact_Number(String num)
    
    {
        fill(contactNumber, num);

    }

    //Add HRM 

    public void addHRM(String fullname, String ema,String pho)
    {
        fill(hrmFullName, fullname);
        fill(email, ema);
        fill(phone, pho);
        click(SaveButton);


    }



     


    
}
