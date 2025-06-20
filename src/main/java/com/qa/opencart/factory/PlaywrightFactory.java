package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.qa.opencart.constuns.AppConstuns;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class PlaywrightFactory {

//    Playwright playwright;
//    Browser browser;
//    BrowserContext browserContext;
//    Page page;
    Properties prop;

    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public Playwright getPlaywrightThreadLocal() {
        return playwrightThreadLocal.get();
    }

    public void setPlaywrightThreadLocal(Playwright playwrightThreadLocal) {
        this.playwrightThreadLocal.set(playwrightThreadLocal);
    }

    public Browser getBrowserThreadLocal() {
        return browserThreadLocal.get();
    }

    public void setBrowserThreadLocal(Browser browserThreadLocal) {
        this.browserThreadLocal.set(browserThreadLocal);
    }

    public BrowserContext getBrowserContextThreadLocal() {
        return browserContextThreadLocal.get();
    }

    public void setBrowserContextThreadLocal(BrowserContext browserContextThreadLocal) {
        this.browserContextThreadLocal.set(browserContextThreadLocal);
    }

    public static Page getPageThreadLocal() {
        return pageThreadLocal.get();
    }

    public void setPageThreadLocal(Page pageThreadLocal) {
        this.pageThreadLocal.set(pageThreadLocal);
    }

    /**
     * this method used to initialize the browser
     *
     * @param prop
     * @return
     */
    public Page initBrowser(Properties prop) {

        //playwright = Playwright.create();
        setPlaywrightThreadLocal(Playwright.create());

        String browserName = prop.getProperty(AppConstuns.BROWSER).trim();
        String url = prop.getProperty(AppConstuns.URL).trim();
        boolean headlessFlag = Boolean.parseBoolean(prop.getProperty(AppConstuns.HEADLESS).trim());

        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("--start-maximized");

        System.out.println("browserName = " + browserName);
        switch (browserName.toLowerCase()) {
            case AppConstuns.CHROMIUM:
                // browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                setBrowserThreadLocal(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag).setArgs(arguments)));
                break;
            case AppConstuns.FIREFOX:
                // browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                setBrowserThreadLocal(getPlaywrightThreadLocal().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag).setArgs(arguments)));
                break;
            case AppConstuns.SAFARI:
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag));
                setBrowserThreadLocal(getPlaywrightThreadLocal().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessFlag).setArgs(arguments)));
                break;
            case AppConstuns.CHROME:
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessFlag));
                setBrowserThreadLocal(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessFlag).setArgs(arguments)));
                break;
            case AppConstuns.EDGE:
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headlessFlag));
                setBrowserThreadLocal(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headlessFlag).setArgs(arguments)));
                break;
            default:
                System.out.println("Please pass correct browser name..........");
                break;
        }

        System.out.println("url = " + url);

//        browserContext = browser.newContext();
//        page = browserContext.newPage();
//        page.navigate(url);

        setBrowserContextThreadLocal(getBrowserThreadLocal().newContext(new Browser.NewContextOptions().setViewportSize(null)));
        setPageThreadLocal(getBrowserContextThreadLocal().newPage());
        getPageThreadLocal().navigate(url);
        getPageThreadLocal().waitForLoadState(LoadState.DOMCONTENTLOADED);
        return getPageThreadLocal();
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

    /**
     * take screenshot
     *
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") +  "./screenshot/" + System.currentTimeMillis() + ".png";

        getPageThreadLocal().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));
        return path;
    }
}
