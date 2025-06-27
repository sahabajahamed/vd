package samurai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Playwright_test {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://flinki-admin.weavers-web.com/");
    }

    @Test(priority = 1)
    public void loginWithBlankField() {

        Locator bitton = page.locator("//button[normalize-space()='Sign In']");
        bitton.click();
        Locator errors = page.locator("//p[@class='text-red']");
        errors.first().waitFor(new Locator.WaitForOptions().setTimeout(3000));

        List<String> actualMessages = new ArrayList<>();
        for (int i = 0; i < errors.count(); i++) {
            actualMessages.add(errors.nth(i).textContent().trim());
        }

        // Expected error messages (order matters here)
        List<String> expectedMessages = Arrays.asList(
                "Email is a required field",
                "Password is a required field");

        // Verify
        Assert.assertEquals(actualMessages, expectedMessages, "Error messages do not match");
    }

    public void login() {
        Locator username = page.locator("//input[@id=':r0:']");
        Locator password = page.locator("//input[@id=':r1:']");
        Locator bitton = page.locator("//button[normalize-space()='Sign In']");
        username.clear();
        username.fill("masteradmin@admin.com");
        password.clear();
        password.fill("123456");
        bitton.click();

        Locator toast = page.locator("//div[contains(@class, 'Toastify__toast') and contains(., 'Login successful')]")
                .first();
        toast.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        String actualMessage = toast.textContent().trim();
        Assert.assertEquals(actualMessage, "Login successfull.", "Toast message did not match");

    }

    @Test(priority = 2)
    public void testLoginToast() {
        login();
        page.locator("//span[normalize-space()='Countries']").click();
        page.locator("//button[normalize-space()='Add Country']").click();
        page.locator("input[id=':r2:']").fill("Playwright");
        page.locator("//input[@id=':r3:']").fill("National");
        page.locator("//button[normalize-space()='Add']").click();
        page.waitForTimeout(2000);

    }

    @Test(priority = 3)
    public void ScollSideMenu() throws InterruptedException {
        login();
        page.waitForSelector(".custom-scrollbar");
        page.evaluate("document.querySelector('.custom-scrollbar').scrollTop += 1000;");
        Thread.sleep(4000);

    }

    @Test(priority = 4)
    public void addSports()

    {
        login();
        page.locator("//a[@href='/sports']").click();

        // Locator tecxt = page.locator("//input[@id=':r7:']");

        Faker fake = new Faker();
        int count = 0;

        while (count < 10) {
            String sports = fake.name().firstName();
            Locator loc = page.locator("//button[normalize-space()='Add Sport']");
            loc.click();

            Locator tecxt = page.getByPlaceholder("Enter sport name");
            tecxt.clear();
            tecxt.fill(sports);
            page.locator("//button[normalize-space()='Add']").click();
            count++;

        }

    }

    @Test(priority = 5)
    public void deleteSports() throws InterruptedException {

        login();

        page.locator("//a[@href='/sports']").click();

        Locator deleteButtons = page.locator("button.text-red-500");
        Thread.sleep(3000);

        int total = deleteButtons.count();
        

        for (int i = 0; i < total; i++) {
            // Re-fetch to ensure locator is fresh after DOM changes
            deleteButtons = page.locator("button.text-red-500");

            if (deleteButtons.count() > 0) {
                deleteButtons.nth(0).click();

                // Handle confirmation dialog
                Locator confirm = page.locator("//button[normalize-space()='Delete']");
                if (confirm.isVisible()) {
                    confirm.click();
                }

                // Wait for deletion to reflect
                page.waitForTimeout(1000);
            }

        }
    }
}
