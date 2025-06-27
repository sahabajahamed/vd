package com.samurai.pages.Admin;

import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class Dashboard_page extends BasePage {

    public Dashboard_page(Page page) {
        super(page);
    }




    private final String schoolManagement = "//div[normalize-space()='School Management']";
    private final String usersMangement = "//div[normalize-space()='Users Management']";
    private final String jobMangements = "//div[normalize-space()='Job Management']";
    private final String engagments = "//div[normalize-space()='Engagement']";
    private final String aiMatching = "//div[normalize-space()='AI Matching']";
    private final String invoicemangement = "//div[normalize-space()='Invoice Management']";
    private final String settings = "//div[normalize-space()='Settings']";
    private final String logOut = "//div[contains(text(),'Logout')]";


    // click School management page and redirect to School management page
    public SchoolMangement_page click_School_Management()
    {
        click(schoolManagement);
        return new SchoolMangement_page(page);
    }

    public UsersManagesments_page click_Users_Management()
    {
        click(usersMangement);
        return new UsersManagesments_page(page);
    }
    




    


}



