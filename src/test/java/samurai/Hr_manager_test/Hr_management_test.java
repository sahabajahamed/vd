package samurai.Hr_manager_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.samurai.pages.Hr_manager.HR_management;
import com.samurai.pages.common.LoginPage;
import com.samurai.pages.common.SideMenuLink_page;

import samurai.basetest.BaseTest;

public class Hr_management_test extends BaseTest {

    private LoginPage loginpage;
    private HR_management hrManager;
    private SideMenuLink_page sideMenu;

    @BeforeMethod
    public void sateup() {
        loginpage = new LoginPage(page);
        hrManager = new HR_management(page);
        sideMenu = new SideMenuLink_page(page);

    }

    @Test
    public void HRMCreateNewHr() throws InterruptedException {
        loginpage.login("sahabajhrm@yopmail.com", "Sahabaj@800");
         int count = 100;
         while (count > 0) {
             sideMenu.clickHrManagement();
             hrManager.creteNewHR();
             Thread.sleep(5000); // Consider replacing with Playwright's wait

             String tableHRName = page.locator("(//table//tbody//tr[1]//td[1])").innerText().trim();
             String tableEmail = page.locator("(//table//tbody//tr[1]//td[2])").innerText().trim();
             String PhoneNumber = page.locator("(//table//tbody//tr[1]//td[3])").innerText().trim();
             System.out.println(tableHRName + " test");
             System.out.println(tableEmail + " test");
             System.out.println(PhoneNumber + " test");

             Assert.assertEquals(tableHRName, hrManager.generatedName, "Not Matched Hr name ");
             Assert.assertEquals(tableEmail, hrManager.generatedEmail, "Not matched Email ID");
             Assert.assertEquals(PhoneNumber, hrManager.phNumber, "Not Matched Phone Number ");
             count--;
         }
    }

    @Test
    public void deleteCreatedHR() throws InterruptedException
    {

        loginpage.login("sahabajhrm@yopmail.com", "Sahabaj@800");
        int count = 100;

        while (count > 0) {
            sideMenu.clickHrManagement();
            hrManager.deleteHR();
            count--;
        }

    }
    

    public void editCreatedHR()
    {
        
    }
  

       
    
}
