package com.Weaversweb.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.Page;

public class UserManagement extends BasePage {
     private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public UserManagement(Page page)
    {
        super(page);
    }

    private final String searchField = "//input[@id='outlined-basic']";
    private final String searchResults = "//table[@aria-label='customer table']//tbody/tr/td[1]";
    private final String errorMessage = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--error]'";
    private final String active = "//tbody/tr[1]/td[7]/div[1]/div[1]";
    


     public void search(String keyword) {
         page.fill(searchField, keyword);
         page.keyboard().press("Enter");
         page.waitForTimeout(2000); // wait for results to load
     }
    
      public boolean isResultContainingKeyword(String keyword) {
        return page.locator(searchResults).allTextContents()
                   .stream()
                   .anyMatch(text -> text.toLowerCase().contains(keyword.toLowerCase()));
    }

     public boolean result(String keyword)
     {

         return page.locator(searchField).allTextContents().stream()
                 .allMatch(allmatch -> allmatch.toLowerCase().contains(keyword));
     }

    public String getErrorMessage()
    {
        return getText(errorMessage);
    }





     public void deactiveUsers()
     {
    

    }

    


    
    
}
