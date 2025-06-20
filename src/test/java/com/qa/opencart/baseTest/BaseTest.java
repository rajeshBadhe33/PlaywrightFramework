package com.qa.opencart.baseTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    protected HomePage homePage;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AccountPage accountPage;
    protected AddressBookPage addressBookPage;
    protected AddNewAddressPage addNewAddressPage;

    @Parameters({"browsers"})
    @BeforeTest
    public void setup(@Optional String browserName) throws IOException {

        playwrightFactory = new PlaywrightFactory();
        prop = playwrightFactory.initProp();

        //check if browser param is coming from testng.xml
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        page = playwrightFactory.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}
