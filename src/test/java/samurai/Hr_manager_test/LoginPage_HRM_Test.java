package samurai.Hr_manager_test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.samurai.pages.common.LoginPage;

import samurai.basetest.BaseTest;



public class LoginPage_HRM_Test extends BaseTest {

    @Test
    public void VerifyValidLogintest()
    {
        LoginPage login = new LoginPage(page);
        Assert.assertEquals("Login to Get Started", login.getTxt());
        login.login("sahabajhrm@yopmail.com", "Sahabaj@800");
    }

    @Test
    public void inVerifyValidLogintest()
    
        
    {
        LoginPage login = new LoginPage(page);
        Assert.assertEquals("Login to Get Started", login.getTxt());
        login.login("sahabajhrm@yopmail.com", "Wasim@800");
        String actualMessage = login.getErrorMessage();
         Assert.assertEquals(actualMessage, "Invalid email or password");
    }
    }

 


    

