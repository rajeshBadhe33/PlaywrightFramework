package com.qa.opencart.tests;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import jdk.jfr.DataAmount;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    HomePage homePage;

    @BeforeTest
    public void setup() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser("Chromium");
        homePage = new HomePage(page);
    }

    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, "Your Store");
    }

    @Test
    public void homePageUrlTest() {
        String actualUrl = homePage.getPageURL();
        Assert.assertEquals(actualUrl, "https://naveenautomationlabs.com/opencart/");
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

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
