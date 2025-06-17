package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import com.qa.opencart.util.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddNewAddressPageTest extends BaseTest {
    TestDataGenerator testDataGenerator;
    Map<String, String> data;

    @Test(priority = 1)
    public void addNewAddress() {
        testDataGenerator = new TestDataGenerator();

        loginPage = homePage.navigateToLoginPage();
        String userName = prop.getProperty(AppConstuns.USER_NAME);
        String userPass = prop.getProperty(AppConstuns.USER_PASS);
        accountPage = loginPage.doLogIn(userName, userPass);
        addressBookPage = accountPage.navigateToAddressBookPage();
        addNewAddressPage = addressBookPage.navigateToAddNewAddress();
        data = testDataGenerator.generateAddressData("en-GB");
        addressBookPage = addNewAddressPage.addNewAddress(data);
    }

    @Test(priority = 2)
    public void verifyNewlyAddedAddress() {
        List<String> addressDetail = addressBookPage.getAddress();
        AtomicBoolean flag = new AtomicBoolean(false);
        System.out.println("Expected = " + data);
        addressDetail.forEach((string) -> {
            if (string.contains(data.get("firstName"))) {
                flag.set(true);
                System.out.println("Actual = " + string);
            }
        });
        Assert.assertTrue(flag.get(),"Address Not added.");
    }
}
