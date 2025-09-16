package Weaversweb.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Weaversweb.pages.LoginPage;
import com.Weaversweb.pages.MenuPage;
import com.Weaversweb.pages.UserManagement;
import com.Weaversweb.utils.ConfigReader;

import Weaversweb.basetest.BaseTest;

@Listeners(TestListener.class)
public class UserManagement_Test extends BaseTest {
    
    LoginPage loginPage;
    MenuPage menuPage;
    UserManagement usermanagement;
    SoftAssert softAssert = new SoftAssert();

    

    @Test(priority = 1, groups = {"smoke"})
    public void Verify_user_can_login_with_valid_credentials() {

        try {
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("validUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);
            String successMessage = loginPage.getSucessMessage();
            softAssert.assertEquals(successMessage, ConfigReader.get("SucessFullLoginMessage"),
                    "Success message mismatch");

            String actualHeader = loginPage.getDashboardText();
            softAssert.assertTrue(actualHeader.contains("Dashboard"), "Dashboard not found after login");
            menuPage = new MenuPage(page);
            menuPage.clickUserManagement();
            usermanagement = new UserManagement(page);
            usermanagement.search("paul");
            boolean result = usermanagement.isResultContainingKeyword("paul");
            Assert.assertTrue(result, "No search result contains the keyword: " + "paul");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        // softAssert.assertAll();
    }@Test
     public void Verify_user_can_Search() {
       

        
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("validUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);
            String successMessage = loginPage.getSucessMessage();
            softAssert.assertEquals(successMessage, ConfigReader.get("SucessFullLoginMessage"), "Success message mismatch");

            String actualHeader = loginPage.getDashboardText();
            softAssert.assertTrue(actualHeader.contains("Dashboard"), "Dashboard not found after login");
            menuPage = new MenuPage(page);
            menuPage.clickUserManagement();
            usermanagement = new UserManagement(page);
            usermanagement.search("paul");
            boolean result = usermanagement.result("Bishal Paul");
            
           
            try {
                 Assert.assertTrue(result, "No search result contains the keyword: " + "Bishal Paul");
                
             } catch (Exception e) {

                 Assert.assertEquals(loginPage.getErrorMessageText(), "User Not Found ",
                         "Not matched as per the design");
                 String messsgae = loginPage.getErrorMessageText();
                 System.out.println(messsgae);
               
            }
            





        
        }
        // softAssert.assertAll();
    }

