package com.Weaversweb.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ForgotPassword extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
    // Removed duplicate declaration of otp
    

    public ForgotPassword(Page page)
    {
        super(page);
    }

    private final String emailField = "//input[@id='email']";
    private final String errorMessage = "//p[@id='email-helper-text']";
    private final String sendButton = "//button[@type='submit']";
    private final String sucessOTPsend = "Toastify__toast Toastify__toast-theme--light Toastify__toast--success";
    private final String verifyNow = "//button[@type='submit']";
    

    public void enterEmail(String email)
    {
        fill(emailField, email);
        logger.info("Enter Email id ");
         
       
    }

    public String getErrorText() {
        return getText(errorMessage);

    }

    public String getSuccesOtpMessage()
    {
        return getText(sucessOTPsend);
    }

    public void ClickSendButton()
    {
        click(sendButton);
        logger.info("Enter Send Button ");

    }

    

    public static String fetchOtpFromYopmail(BrowserContext context, String email) {
        Page yopmailPage = context.newPage();
        yopmailPage.navigate("https://yopmail.com/");
        yopmailPage.locator("#login").fill(email);
        yopmailPage.keyboard().press("Enter");

        yopmailPage.waitForTimeout(3000); // Optional: wait for inbox to load

        FrameLocator mailFrame = yopmailPage.frameLocator("#ifmail");
        Locator otpLocator = mailFrame.locator("text=OTP:");

        otpLocator.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        String fullOtpText = otpLocator.textContent().trim();

        // Extract OTP from the string, e.g., "OTP: 123456"
        String otp = fullOtpText.replaceAll("[^0-9]", "");
        System.out.println("Fetched OTP: " + otp);
        return otp;

    }

    public void enterOTP(String otp)
    {
        logger.info("Entering OTP: " + otp);
        fill(emailField, otp);
         click(verifyNow);
       
    }
 

  




    




    
    
}
