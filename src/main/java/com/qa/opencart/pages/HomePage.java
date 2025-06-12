package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;


    // 1. String Locators - OR
    private final String inputSearchBar = "input[name='search']";
    private final String iconSearchBar = "#search>span button";
    private final String lblSearchedHeader = "#content h1";
    private String menuList = "#menu ul.nav > li > a:has-text('#MENU')";


    // 2. Page Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    // 3. Page actions/methods
    public String getPageURL() {
        String url = page.url();
        System.out.println("Page URL is = " + url);
        return url;
    }

    public String getPageTitle() {
        page.waitForLoadState();
        String title = page.title();
        System.out.println("Page Title is = " + title);
        return title;
    }

    public void doSearch(String productName) {
        page.fill(inputSearchBar, productName);
        page.click(iconSearchBar);
    }

    public String doSearchGetResultString(String productName) {
        page.fill(inputSearchBar, productName);
        page.click(iconSearchBar);
        String actualSearchResult = page.textContent(lblSearchedHeader);
        System.out.println("Actual Search Result = " + actualSearchResult);
        return actualSearchResult;
    }

    public String getMenu(String menuName){
        String name = page.textContent(menuList.replace("#MENU",menuName));
        System.out.println("Menu name = " + name);
        return name;
    }


}
