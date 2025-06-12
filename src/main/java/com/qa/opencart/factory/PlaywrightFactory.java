package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import com.qa.opencart.constuns.AppConstuns;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    /**
     * this method used to initialize the browser
     * @param prop
     * @return
     */
    public Page initBrowser(Properties prop) {

        playwright = Playwright.create();
        String browserName = prop.getProperty("browser").trim();
        String url = prop.getProperty("url").trim();
        boolean headlessFlag = Boolean.parseBoolean(prop.getProperty("headless").trim());

        System.out.println("browserName = " + browserName);
        switch (browserName.toLowerCase()) {
            case AppConstuns.CHROMIUM:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                break;
            case AppConstuns.FIREFOX:
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                break;
            case AppConstuns.SAFARI:
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                break;
            case AppConstuns.CHROME:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessFlag));
                break;
            default:
                System.out.println("Please pass correct browser name..........");
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        System.out.println("url = " + url);
        page.navigate(url);

        return page;
    }

    /**
     * this method used to initialize the properties file from config
     */
    public Properties initProp() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(AppConstuns.CONFIG_FILE_PATH);

        prop = new Properties();
        prop.load(fileInputStream);

        return prop;
    }
}
