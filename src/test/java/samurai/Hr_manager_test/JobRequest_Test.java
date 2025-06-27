package samurai.Hr_manager_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.samurai.pages.common.JobRequest;
import com.samurai.pages.common.LoginPage;
import com.samurai.pages.common.SideMenuLink_page;

import samurai.basetest.BaseTest;

public class JobRequest_Test extends BaseTest {

    public LoginPage loginpage;
    public SideMenuLink_page sidemneu;
    JobRequest jobreq;
    
   
    @BeforeMethod
    public void newSetup()
    {
        loginpage = new LoginPage(page);
        sidemneu = new SideMenuLink_page(page);
        jobreq = new JobRequest(page);
    }
    


    @Test
    public void createJob() throws InterruptedException
    {
        LoginPage login = new LoginPage(page);
        login.login("sahabajhrm@yopmail.com", "Sahabaj@800");
        JobRequest jobreq = new JobRequest(page);
        jobreq.clickJobreq();
        jobreq.createJobreq();
        Thread.sleep(3000);
        jobreq.JobPost();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Test
    public void createUrgentJobPost() throws InterruptedException
    {
        loginpage.login("sahabajhrm@yopmail.com", "Sahabaj@800");
        sidemneu.clickJobRequest();
        jobreq.JobPost();
        Thread.sleep(5000);

    }

    
















    public void createNormalJobPost()
    {

    }
    
}
