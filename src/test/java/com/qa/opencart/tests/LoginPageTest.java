package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {


    @Test(priority = 50)
    public void login() {
        String userName = prop.getProperty(AppConstuns.USER_NAME);
        String userPass = prop.getProperty(AppConstuns.USER_PASS);
        loginPage.doLogIn(userName, userPass);
    }

    @Test(priority = 1)
    public void verifyUrl() {
        loginPage = homePage.navigateToLoginPage();
        String actualUrl = loginPage.getUrl();
        Assert.assertEquals(actualUrl, "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
    }

    @Test(priority = 2)
    public void verifyTitle() {
        String actualTitle = loginPage.getTitle();
        Assert.assertEquals(actualTitle, AppConstuns.LOGIN_PAGE_TITLE);
    }


//    @Test
//    public void aVerifyHeader() {
//        getLoggedIn();
//        String actualHeader = loginPage.getHeader();
//        Assert.assertEquals("My Account", actualHeader);
//    }

}
