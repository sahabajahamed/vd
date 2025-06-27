package com.samurai.base;

import java.util.Random;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.samurai.utils.LoggerUtil;

public class BasePage {
    Faker faker = new Faker();

    public Page page;

    public BasePage(Page page) {
        this.page = page;
        LoggerUtil.info("Initialized page: " + this.getClass().getSimpleName());
    }

   public void click(String selector) {
        LoggerUtil.info("Clicking: " + selector);
        page.click(selector);
    }

    public void fill(String xpath, String value) {
        LoggerUtil.info("Typing '" + value + "' in: " + xpath);
        page.fill(xpath, value);
    }


    public String getText(String xpath) {
        
        return page.locator(xpath).innerText().trim();
    }

    public boolean isVisible(String selector) {
        LoggerUtil.info("Checking visibility: " + selector);
        return page.isVisible(selector);
    }

    public void waitForSeconds(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }

    public String waitForToastAndGetMessage(String toastXpath, int timeoutSeconds) {
        page.waitForSelector(toastXpath, new Page.WaitForSelectorOptions()
                .setTimeout(timeoutSeconds * 1000)
                .setState(WaitForSelectorState.VISIBLE));
        return page.locator(toastXpath).innerText().trim();
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

