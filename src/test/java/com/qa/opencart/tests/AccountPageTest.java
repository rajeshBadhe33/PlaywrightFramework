package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountPageTest extends BaseTest {

    @Test
    public void verifyLandedOnAccountPage() {
        loginPage = homePage.navigateToLoginPage();
        String userName = prop.getProperty(AppConstuns.USER_NAME);
        String userPass = prop.getProperty(AppConstuns.USER_PASS);
        accountPage = loginPage.doLogIn(userName, userPass);
        String actualHeader = accountPage.getPageHeader();
        Assert.assertEquals(actualHeader, "Account");
    }

    @DataProvider
    public Object[][] rightColumnsOptionList() {
        return new Object[][]{
                {"My Account"},
                {"Edit Account"},
                {"Password"},
                {"Address Book"},
                {"Wish List"},
                {"Order History"},
                {"Recurring payments"},
                {"Reward Points"},
                {"Returns"},
                {"Transactions"},
                {"Newsletter"},
                {"Logout"},
                {"Downloads"}
        };
    }
    @Test(dataProvider = "rightColumnsOptionList")
    public void verifyRightColumns(String expectedOption) {
        String actualOption = accountPage.getRightColumnList(expectedOption);
        Assert.assertEquals(actualOption, expectedOption);
    }
}
