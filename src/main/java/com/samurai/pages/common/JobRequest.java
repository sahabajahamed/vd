package com.samurai.pages.common;

import com.microsoft.playwright.Page;
import com.samurai.base.BasePage;

public class JobRequest extends BasePage {
    public JobRequest(Page page)
    {
        super(page);
    }
    

    private final String jobReq = "//a[normalize-space()='Job Requests']";
    private final String creteJobReq = "//a[.='Create Job Request']";
    private final String positionType = "//select[@name='position_type']";
    private final String subjectOption = "//select[@name='subject']";
    private final String GradeLevel = "//select[@name='gradeLevel']";
    private final String MinimumYearsofExperience = "//select[@name='experiences']";
    private final String PreferredGender = "//select[@name='preferdGender']";
    private final String language = "//select[@name='language']";
    private final String hoursPerDay = "//select[@name='hoursPerDay']";
    private final String schoolLocation = "//input[@placeholder='Enter school location']";
    private final String startDate = "//input[@name='startdate']";
    private final String endDate = "//input[@name='enddate']";
    private final String radio_PayStracture = "//div[@class='col-span-2']//div[@class='radio-flex']";












    private final String submitJobReq = "//button[@type='submit']";

    

    
    public void clickJobreq()
    {
        click(jobReq);
    }

    public void createJobreq()
    {
        click(creteJobReq);
    }

    public void JobPost() throws InterruptedException
    {   try {
        page.waitForSelector(positionType);
        selectRandomFromDropdown(positionType);

        page.waitForSelector(subjectOption);
        selectRandomFromDropdown(subjectOption);

        page.waitForSelector(GradeLevel);
        selectRandomFromDropdown(GradeLevel);

        page.waitForSelector(MinimumYearsofExperience);
        selectRandomFromDropdown(MinimumYearsofExperience);

        page.waitForSelector(PreferredGender);
        selectRandomFromDropdown(PreferredGender);

        // Uncomment if language is needed
        // page.waitForSelector(language);
        // selectRandomFromDropdown(language);

        page.waitForSelector(hoursPerDay);
        selectRandomFromDropdown(hoursPerDay);

        // Fill location and ensure field is visible
        page.waitForSelector(schoolLocation);
        page.locator(schoolLocation).scrollIntoViewIfNeeded();
        fill(schoolLocation, "Doha Qatar");

        // Scroll to bottom after filling location
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

        // Fill start and end dates
        page.waitForSelector(startDate);
        fill(startDate, "25/06/2025");

        page.waitForSelector(endDate);
        fill(endDate, "10/07/2025");
        page.waitForSelector(radio_PayStracture);
        randomSelectRadioButton(radio_PayStracture);

        // Wait for the submit button and click
        page.waitForSelector(submitJobReq);
        click(submitJobReq);

        // Optionally, wait for a success message or navigation
        // page.waitForSelector("//div[contains(text(),'Job request created')]", new Page.WaitForSelectorOptions().setTimeout(5000));
    } catch (Exception e) {
        System.err.println("Error during JobPost: " + e.getMessage());
        e.printStackTrace();
    }

    }

    


     


}


