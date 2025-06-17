package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

import java.util.List;

public class AddressBookPage {

    Page page;


    // 1. String Locators - OR
    private String addressBookEntriesHeader = "h2:text('Address Book Entries')";
    private String newAddressButton = "a:text('New Address')";
    private String addressDetail = ".table-responsive tbody>tr>td.text-left";



    // 2. Page Constructor
    public AddressBookPage(Page page) {
        this.page = page;
    }


    // 3. Page actions/methods
    public String getHeader(){
        return page.textContent(addressBookEntriesHeader);
    }

    public AddNewAddressPage navigateToAddNewAddress(){
        page.click(newAddressButton);
        return new AddNewAddressPage(page);
    }

    public List<String> getAddress(){
        List<String> addressDetails = page.locator(addressDetail).allTextContents();
        return addressDetails;
    }


}
