package Weaversweb.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Weaversweb.pages.ForgotPassword;
import com.Weaversweb.pages.LoginPage;
import com.Weaversweb.pages.MenuPage;
import com.Weaversweb.utils.ConfigReader;

import Weaversweb.basetest.BaseTest;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    LoginPage loginPage;
    MenuPage menuPage;
    ForgotPassword forgotpPassword;
     SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1, groups = {"smoke"})
    public void Verify_user_can_login_with_valid_credentials() {
       
        try {
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("validUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);
            String successMessage = loginPage.getSucessMessage();
            softAssert.assertEquals(successMessage, ConfigReader.get("SucessFullLoginMessage"), "Success message mismatch");

            String actualHeader = loginPage.getDashboardText();
            softAssert.assertTrue(actualHeader.contains("Dashboard"), "Dashboard not found after login");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority=2)
    public void Verify_user_can_login_with_Invalid_credentias() {
       
        try {
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("invalidUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);
            String errorMessageText = loginPage.getErrorMessageText();
            softAssert.assertEquals(errorMessageText, ConfigReader.get("InvalidUsernameErrorMessage"),
                    "Invalid username error message mismatch");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority=3)
    public void Verify_user_can_login_with_Invalid_UsernameAndPassword() {
      
        try {
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("invalidUsername");
            String password = ConfigReader.get("invalidPassword");

            loginPage.login(username, password);
            String errorMessageText = loginPage.getErrorMessageText();
            softAssert.assertEquals(errorMessageText, ConfigReader.get("InvalidUsernameErrorMessage"),
                    "Invalid username error message mismatch");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }


@Test(priority=3)
    public void Verify_Login_Empty_field() {
      
        try {
            loginPage = new LoginPage(page);
            loginPage.clickLogin();

            String errorMessageText = loginPage.geterrorMessageforEmail();
            softAssert.assertEquals(errorMessageText, ConfigReader.get("emailErrorMessage"),
                    " error message mismatch");
            String passordErrorMessage = loginPage.getErrorPasswordText();
            softAssert.assertEquals(passordErrorMessage, ConfigReader.get("passworderrorMessage"),"Password Error mismatch");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }



    @Test(priority=4,groups={"smoke"})
    public void SucessFullyLoginAndLogout() {
     
        try {
            loginPage = new LoginPage(page);
            menuPage = new MenuPage(page);

            String username = ConfigReader.get("validUsername");
            String password = ConfigReader.get("validPassword");

            loginPage.login(username, password);

            String successMessage = loginPage.getSucessMessage();
            softAssert.assertEquals(successMessage, ConfigReader.get("SucessFullLoginMessage"), "Login message mismatch");

            String actualHeader = loginPage.getDashboardText();
            softAssert.assertTrue(actualHeader.contains("Dashboard"), "Dashboard not found after login");

            // menuPage.ClicklogOut();

            String actualMessage = loginPage.getWelComeText();
            softAssert.assertEquals(actualMessage, ConfigReader.get("welcomeText"), "Logout welcome text mismatch");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority=5,groups={"smoke"})
    public void loginWithVendorMailandPassword() {
       
        try {
            loginPage = new LoginPage(page);
            String username = ConfigReader.get("vendorMail");
            String password = ConfigReader.get("vendorPassword");

            loginPage.login(username, password);
            String errorMessageText = loginPage.getErrorMessageText();

            softAssert.assertEquals(errorMessageText, ConfigReader.get("vendormailMessage"),
                    "Vendor email error message mismatch");

        } catch (Exception e) {
            softAssert.fail("Test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority=6,groups={"funtional"})
    public void forgotPasword() {
       
        try {
            loginPage = new LoginPage(page);
            loginPage.forgotPassword();

            forgotpPassword = new ForgotPassword(page);
            forgotpPassword.enterEmail(ConfigReader.get("validUsername"));
            forgotpPassword.ClickSendButton();

            String otp = ForgotPassword.fetchOtpFromYopmail(context, ConfigReader.get("validUsername"));

            bringtoFrontForPage();
            forgotpPassword.enterOTP(otp);

            // Optionally add assertion after OTP verification
            // String confirmation = forgotpPassword.getOtpVerificationMessage();
            // softAssert.assertEquals(confirmation, "Expected message", "OTP confirmation failed");

        } catch (Exception e) {
            softAssert.fail("Forgot password test failed due to exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}
