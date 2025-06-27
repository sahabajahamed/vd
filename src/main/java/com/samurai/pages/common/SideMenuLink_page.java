package com.samurai.pages.common;

import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;
import com.samurai.pages.Hr_manager.HR_management;

public class SideMenuLink_page extends BasePage {
    
    public SideMenuLink_page(Page page)
    {
        super(page);
    }




    private final String hrmangement = "//a[normalize-space()='HR Management']";
    private final String jobRequest = "//a[normalize-space()='Job Requests']";
    private final String cancellationInteriew = "//a[normalize-space()='Candidates & Interviews']";
    private final String engamments = "//a[normalize-space()='Engagements']";
    private final String JOB_REQUEST_BTN = "//a[.='Create Job Request']";





    public HR_management clickHrManagement()
    {
        click(hrmangement);
        return new HR_management(page);
    }

    public void clickJobRequest()
    {
        click(JOB_REQUEST_BTN);
    }

    

    
}
