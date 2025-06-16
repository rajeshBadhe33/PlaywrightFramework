package com.qa.opencart.tests;

import com.qa.opencart.baseTest.BaseTest;
import com.qa.opencart.constuns.AppConstuns;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, AppConstuns.HOME_PAGE_TITLE);
    }

    @Test
    public void homePageUrlTest() {
        String actualUrl = homePage.getPageURL();
        Assert.assertEquals(actualUrl, prop.getProperty(AppConstuns.URL));
    }

    @DataProvider
    public Object[][] productNames() {
        return new Object[][]{
                {"mackbook"},
                {"iMac"},
                {"Samsung"}
        };
    }

    @Test(dataProvider = "productNames")
    public void searchTest(String productName) {
        String actualSearchResult = homePage.doSearchGetResultString(productName);
        Assert.assertEquals(actualSearchResult, "Search - " + productName);
    }


    @DataProvider
    public Object[][] menuList() {
        return new Object[][]{
                {"Desktops"},
                {"Laptops & Notebooks"},
                {"Components"},
                {"Tablets"},
                {"Software"},
                {"Phones & PDAs"},
                {"Cameras"},
                {"MP3 Players"}
        };
    }

    @Test(dataProvider = "menuList")
    public void verifyMenuOnHomePage(String menu) {
        String menuName = homePage.getMenu(menu);
        Assert.assertEquals(menuName, menu);
    }




}
