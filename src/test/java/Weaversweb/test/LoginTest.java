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
        try {
            LoginPage loginPage = new LoginPage(page);
            String username = ConfigReader.get("validUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);

            String actualHeader = loginPage.getDashboardText();
            Assert.assertTrue(actualHeader.contains("Dashboard"), "Login failed: Dashboard not found");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

    }
}
