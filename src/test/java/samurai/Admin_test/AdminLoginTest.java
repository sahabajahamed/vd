package samurai.Admin_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.samurai.pages.Admin.AdminLogin_Page;
import com.samurai.pages.Admin.SchoolMangement_page;

import samurai.basetest.AdminBasetest;

public class AdminLoginTest extends AdminBasetest {
    
    AdminLogin_Page loginpage;
    SchoolMangement_page Schoolmanagement;
     Faker faker = new Faker();

    @BeforeMethod
    public void testStep()
    {
        loginpage = new AdminLogin_Page(page);
        Schoolmanagement = new SchoolMangement_page(page);

    }
 
    @Test
    public void adminLoginWithvalidCred()
    {


        loginpage.adminLogin("samurai@yopmail.com", "zi00EKObi44k4fP").click_School_Management();
        Schoolmanagement.addHRM(faker.name().fullName(), faker.internet().emailAddress() ,faker.phoneNumber().cellPhone());
    }
    
}
