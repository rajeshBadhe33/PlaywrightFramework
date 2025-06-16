package com.qa.opencart.baseTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    protected HomePage homePage;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AccountPage accountPage;


    @BeforeTest
    public void setup() throws IOException {
        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.initProp();
        page = playwrightFactory.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
