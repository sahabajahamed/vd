package com.Weaversweb.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Weaversweb.base.BasePage;
import com.microsoft.playwright.Page;

public class CatagoryManagment extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
    public CatagoryManagment(Page page)
    {
        super(page);
    }

    private String categorymanagementText = "//h1[normalize-space()='Category Management']";
    private String addcatBTN = "//button[.='Add Category']";
    private String addSubCatBTN = "//button[.='Add Sub-Category']";
    private String searchFiled = "//input[@id='common-text-field']";
    private String enterCategoryField = "/input[@placeholder='Enter Name']";
    private String desc = "//textarea[@id='common-text-field']";
    private String submitBTN = "//button[.='Submit']";
    private String CateforySucessMessage = "//div[@class='Toastify__toast Toastify__toast-theme--light Toastify__toast--success']";
    private String editCategory = "//table[@class='MuiTable-root css-xiihcm']//button[@title='Edit']";
    private String deleteCategory = "//table[@class='MuiTable-root css-xiihcm']//button[@title='Delete']";
    private String deletePopupButton = "//button[normalize-space()='Delete']";
    private String enterSubcategoryName = "//input[@placeholder='Enter Subcategory Name']";
    private String keywords = "//input[@id='«r37»']";
    private String services = "//input[@id='«r39»']";
    private String amenties = "//input[@id='«r3b»']";

    
}
