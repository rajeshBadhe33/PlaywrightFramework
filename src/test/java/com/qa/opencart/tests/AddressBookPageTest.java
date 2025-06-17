package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddressBookPageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyLandedOnAddressBookPage(){
        loginPage = homePage.navigateToLoginPage();
        String userName = prop.getProperty(AppConstuns.USER_NAME);
        String userPass = prop.getProperty(AppConstuns.USER_PASS);
        accountPage = loginPage.doLogIn(userName, userPass);
        addressBookPage = accountPage.navigateToAddressBookPage();
        String actualHeaderText = addressBookPage.getHeader();
        Assert.assertEquals(actualHeaderText,"Address Book Entries");
    }


}
