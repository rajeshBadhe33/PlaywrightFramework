package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import com.qa.opencart.util.TestDataGenerator;
import org.testng.annotations.Test;

public class AddNewAddressPageTest extends BaseTest {

    @Test(priority = 1)
    public void addNewAddress() {
        TestDataGenerator testDataGenerator = new TestDataGenerator();

        loginPage = homePage.navigateToLoginPage();
        String userName = prop.getProperty(AppConstuns.USER_NAME);
        String userPass = prop.getProperty(AppConstuns.USER_PASS);
        accountPage = loginPage.doLogIn(userName, userPass);
        addressBookPage = accountPage.navigateToAddressBookPage();
        addNewAddressPage = addressBookPage.navigateToAddNewAddress();
        addressBookPage = addNewAddressPage.addNewAddress(testDataGenerator.generateAddressData("en-GB"));
    }

    @Test(priority = 2)
    public void verifyNewlyAddedAddress() {
        String addressDetail = addressBookPage.getAddress();
        System.out.println("addressDetail = " + addressDetail);

    }
}
