package Weaversweb.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Weaversweb.pages.LoginPage;
import com.Weaversweb.utils.ConfigReader;

import Weaversweb.basetest.BaseTest;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    
    @Test
    public void testValidLogin() {
          LoginPage loginPage = new LoginPage(page);
         String username = ConfigReader.get("validUsername");
        String password = ConfigReader.get("validPassword");

        loginPage.login(username, password);

        // Verify dashboard header appears
        String actualHeader = loginPage.getDashboardText();
        Assert.assertTrue(actualHeader.contains("Dashboard"), "Login failed: Dashboard not found");
    }



    
}
