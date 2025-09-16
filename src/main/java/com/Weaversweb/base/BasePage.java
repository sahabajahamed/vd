package com.Weaversweb.base;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.pages.ForgotPassword;
import com.github.javafaker.Faker;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

    
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    Faker faker = new Faker();

    public Page page;
    protected BrowserContext context;

    public BasePage(Page page) {
        this.page = page;
        this.context = page.context(); 
        logger.info("Initialized page: " + this.getClass().getSimpleName());
    }

   public void click(String selector) {
       logger.info("Clicking element: " + selector);
        waitForElementVisible(selector, 2);
        page.locator(selector).click();
    }

    public void click(Locator locator) {
        logger.info("Clicking element");
        locator.click();
    }

    public void fill(String selector, String value) {
        logger.info("Typing '" + value + "' in: " + selector);
        page.locator(selector).fill(value);
    }

    public void fill(Locator locator, String value) {
        logger.info("Typing '" + value + "'");
        locator.fill(value);
    }


     public String getText(String selector) {
        logger.info("Getting text from: " + selector);
        return page.locator(selector).innerText().trim();
    }

    public String getText(Locator locator) {
        logger.info("Getting text from element");
        return locator.innerText().trim();
    }

     public void waitForElementVisible(String selector, int timeoutSeconds) {
        logger.info("Waiting for element visible: " + selector);
        page.waitForSelector(selector,
            new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutSeconds * 1000));
                
    }



 public String waitForToastAndGetMessage(String toastXpath, int timeoutSeconds) {
        waitForElementVisible(toastXpath, timeoutSeconds);
        return getText(toastXpath);
    }



    public boolean isVisible(String selector, int timeoutSeconds) {
        logger.info("Checking visibility: " + selector);
        try {
            page.waitForSelector(selector,
                    new Page.WaitForSelectorOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(timeoutSeconds * 1000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectDropdownByValue(String selectXpath, String value) {
        logger.info("Selecting dropdown value: " + value);
        page.locator(selectXpath).selectOption(value);
    }
    
    public void selectDropdownByIndex(String selectXpath, int index) {
        logger.info("Selecting dropdown index: " + index);
        page.locator(selectXpath).selectOption(new SelectOption().setIndex(index));
    }
    
    
    public void acceptAlert() {
        logger.info("Accepting alert");
        page.onDialog(dialog -> dialog.accept());
    }
      public void dismissAlert() {
        logger.info("Dismissing alert");
        page.onDialog(dialog -> dialog.dismiss());
    }
    
    // New window/tab handling
    public Page openNewTab(String buttonSelector) {
        logger.info("Opening new tab via: " + buttonSelector);
        return page.waitForPopup(() -> click(buttonSelector));
    }
    
    public void switchToTab(int index) {
        logger.info("Switching to tab index: " + index);
        Page targetPage = page.context().pages().get(index);
        targetPage.bringToFront();
    }

    public void selectRadioByValue(String radioGroupSelector, String value) {
        logger.info("Selecting radio by value: " + value);
        page.locator(radioGroupSelector + "[value='" + value + "']").click();
    }

    public void BringOTPPage(BrowserContext context, String email) {
    page.bringToFront(); // Custom method from BasePage
    ForgotPassword.fetchOtpFromYopmail(context, email);
}

    



    public void waitForSeconds(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }

   



    public void selectRandomFromDropdown(String selectXpath) {
        Locator dropdown = page.locator(selectXpath);
        int optionCount = dropdown.locator("option").count();

        // Exclude first "Select an option" if needed
        int start = 1; // Skip first default option
        if (optionCount <= 1) {
            System.out.println(" No options available to select.");
            return;
            
        }

        int randomIndex = new Random().nextInt(optionCount - start) + start;
        String valueToSelect = dropdown.locator("option").nth(randomIndex).getAttribute("value");
        dropdown.selectOption(valueToSelect);
         page.waitForTimeout(200);
    }
    public String firstName() {
    return faker.name().firstName();
}

    public String randomEmail() {
    return faker.name().firstName().toLowerCase() + "@yopmail.com";
}

    public String RandomPhoneNumber() {
    return faker.number().digits(10);
}

public void randomSelectRadioButton(String radioXpath)
{   Locator radios = page.locator(radioXpath);
    int count = radios.count();
    if (count == 0) {
        System.out.println("No radio buttons found for selector: " + radioXpath);
        return;
    }
    int randomIndex = new Random().nextInt(count);
    radios.nth(randomIndex).click();
    page.waitForTimeout(200);
    
  }

  public Page openNewTabAndReturn(String buttonId) {
    return page.waitForPopup(() -> page.click(buttonId));
}
 



}

