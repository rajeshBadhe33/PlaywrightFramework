package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class AccountPage {

    Page page;

    // 1. String Locators - OR
    private String accountLink = ".breadcrumb > li a:text('Account')";
    private String rightColumnList = "#column-right div a:text('#MENU')";
    private String addressBookLink = ".list-group a:text('Address Book')";


    // 2. Page Constructor
    public AccountPage(Page page) {
        this.page = page;
    }


    // 3. Page actions/methods
    public String getPageHeader() {
//        page.pause();
        return page.textContent(accountLink);
    }

    public String getRightColumnList(String expectedOption) {
        return page.textContent(rightColumnList.replace("#MENU", expectedOption));
    }

    public AddressBookPage navigateToAddressBookPage() {
        page.click(addressBookLink);
        return new AddressBookPage(page);
    }
}
