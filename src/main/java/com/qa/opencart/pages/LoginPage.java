package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String inputEmail = "#input-email";
    private String inputPassword = "#input-password";
    private String btnLogin = "input[type='submit']";
    private String lblHeader = "h2:has-text('My Account')";

    public LoginPage(Page page) {
       this.page = page;
    }


    public void doLogIn(String inputEmailId, String inputPass){
        page.fill(inputEmail,inputEmailId);
        page.fill(inputPassword,inputPass);
        page.click(btnLogin);
    }

    public String getUrl(){
        page.waitForLoadState();
        return page.url();
    }

    public String getTitle(){
        page.waitForLoadState();
        return page.title();
    }


    public String getHeader(){
        return page.textContent(lblHeader);
    }
}
