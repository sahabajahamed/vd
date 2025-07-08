package Weaversweb.basetest;

import java.io.File;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Weaversweb.utils.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {
      private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected static BrowserContext context;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browserName) {
        //  Read browser and URL from config.properties
        // String browserName = ConfigReader.get("browser");
        String baseUrl = ConfigReader.get("baseUrl");
        String headless = ConfigReader.get("headless");

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
                break;
        }

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        page = context.newPage();
        page.navigate(baseUrl);
    }

    @AfterClass
    public void tearDown() {
         try {
        Thread.sleep(2000); // TEMPORARY: let async actions complete
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    if (page != null) page.close();
    if (context != null) context.close();
    if (browser != null) browser.close();
    if (playwright != null) playwright.close();
}

    public String captureFailedScreenshot(String testName) {
        String fileName = testName + "_" + System.currentTimeMillis() + ".png";
        String screenshotPath = "reports/screenshots/failed/" + fileName;

        // Ensure directory exists
        new File("reports/screenshots/failed/").mkdirs();

        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));

            logger.error("FAILED - Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}