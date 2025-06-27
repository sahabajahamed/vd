package com.samurai.pages.Hr_manager;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class HR_management extends BasePage {

public String generatedName;
public String generatedEmail;
public String phNumber;
    
    public HR_management(Page page )
    {
        super(page);

    }


    private final String addNewhr = "//a[@href='/hrmanagement/create']";
    private final String name = "//input[@placeholder='Enter full name']";
    private final String email = "//input[@placeholder='Enter email address']";
    private final String phoneNumber = "//input[@placeholder='Enter phone number']";
    private final String creteProfile = "//button[@type='submit']";
    private final String DELETE_ICON = "//table//td//img[@alt='delete']";
    private final String DELETE_BTN = "//button[.='Delete']";
    private final String EDIT_BTN = "//table//td//img[@alt='edit']";




    

    public void creteNewHR()
    {
        this.generatedName = firstName();
        this.generatedEmail = randomEmail();
        this.phNumber = RandomPhoneNumber();

        click(addNewhr);
        fill(name, generatedName);
        fill(email, generatedEmail);
        fill(phoneNumber, phNumber);
        click(creteProfile);

        System.out.println(generatedName + " Page  ");
        System.out.println(generatedEmail + " Page  ");
        System.out.println(phNumber + " Page ");

    }
    
     public void deleteHR() throws InterruptedException
     {
         try {
             Locator deleteButtons = page.locator(DELETE_ICON);
             while (deleteButtons.count() > 0) {
                 deleteButtons.nth(0).click();
                 // Wait for the confirmation dialog/button to appear
                 page.waitForSelector(DELETE_BTN, new Page.WaitForSelectorOptions().setTimeout(5000));
                 click(DELETE_BTN);
                 // Wait for the row to be removed from the table
                 page.waitForTimeout(500);
                 deleteButtons = page.locator(DELETE_ICON);
             }
         } catch (Exception e) {
             System.err.println("Error while deleting HR: " + e.getMessage());
             e.printStackTrace();

         }
     }

    public void editHrProfile()
    {
        Locator editprofile = page.locator(EDIT_BTN);
        while(editprofile.count()>0)
        {
            editprofile.nth(0).click();

            //pending work 
             

        }



        
     }
    
    
}
