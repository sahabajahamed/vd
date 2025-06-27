package samurai.basetest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AdminBasetest {
     protected Playwright playwright;
    protected Browser browser;
    protected Page page;
     protected static BrowserContext context;
    

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Browser.NewContextOptions options = new Browser.NewContextOptions().setViewportSize(1920, 1080);
        context = browser.newContext(options);

        page = browser.newPage();
        page.navigate("https://samurai-admin.weavers-web.com");
    }

    @AfterClass
       public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    
    
}
