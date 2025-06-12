package com.qa.opencart.factory;

import com.google.common.io.Files;
import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        System.out.println("browserName = " + browserName);
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
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
        FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");

        prop = new Properties();
        prop.load(fileInputStream);

        return prop;
    }
}
