package debug;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class LearnPlaywrightInspector {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page mainPage = context.newPage();

        // 1. Go to app and start forgot password
        mainPage.navigate("https://vd-admin.weavers-web.com/login");
        mainPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Forgot Password?")).click();
        mainPage.getByPlaceholder("Enter email").fill("vdadmin@yopmail.com");
        mainPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send Reset Link")).click();

        // 2. Open new tab for Yopmail
        Page yopmailPage = context.newPage();
        yopmailPage.navigate("https://yopmail.com/");
        yopmailPage.locator("#login").fill("vdadmin@yopmail.com");
        yopmailPage.keyboard().press("Enter");

        // 3. Wait and switch to iframe
        yopmailPage.waitForTimeout(3000); // Optional wait to allow email to load

        FrameLocator mailFrame = yopmailPage.frameLocator("#ifmail");
        Locator otpLocator = mailFrame.locator("text=OTP:");

        // 4. Wait for OTP to appear and extract it
        otpLocator.waitFor(new Locator.WaitForOptions().setTimeout(10000)); // Wait max 10s
        String fullOtpText = otpLocator.textContent().trim();

        // Extract OTP from text (e.g., "OTP: 123456")
        String otp = fullOtpText.replaceAll("[^0-9]", "");
        System.out.println("Fetched OTP: " + otp);

        // 5. Go back to main tab and enter OTP
        mainPage.bringToFront();
        mainPage.getByPlaceholder("Enter verify OTP").fill(otp);
        
        mainPage.locator("button[type='submit']").click();

        // Optionally pause the page for inspection
        // mainPage.pause();

        // Clean up resources
        // browser.close();
        // playwright.close();
    }
}
