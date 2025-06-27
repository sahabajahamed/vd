package samurai.basetest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected static BrowserContext context;

    @Parameters("browserName")
    @BeforeMethod
    public void setup(@Optional("chromium") String browserName) {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
        }

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        page = context.newPage();
        page.navigate("https://samurai.weavers-web.com/auth/login");
    }

    

    @AfterClass
       public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

}
