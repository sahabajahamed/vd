package samurai;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Basepage {
    private Page page;

    public Basepage(Page page) {
        this.page = page;
    }

    public void clickElement(String path) {
        try {

            Locator element = page.locator(path);
            element.waitFor();
            element.click();

        } catch (Exception e) {
            System.err.println(" Failed to click element: " + path);
            e.printStackTrace();

        }

    }

    public void fillText(String xpath, String value) {
        try {
            Locator input = page.locator(xpath);
            input.waitFor(); // Ensure element is attached
            input.clear();
            input.fill(value);

        } catch (Exception e) {
            System.err.println(" Failed to fill input: " + xpath);
            e.printStackTrace();
        }
    }

    public String getElementText(String xpath) {

        Locator element = page.locator(xpath);
        element.waitFor(); // Wait until the element is ready
        String text = element.innerText().trim();
        return text;

    }
}
