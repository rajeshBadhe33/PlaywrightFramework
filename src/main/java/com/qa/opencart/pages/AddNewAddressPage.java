package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

import java.util.Map;

public class AddNewAddressPage {

    Page page;

    // 1. String Locators - OR
    private String firstNameInputBox = "#input-firstname";
    private String lastNameInputBox = "#input-lastname";
    private String address1InputBox = "#input-address-1";
    private String cityInputBox = "#input-city";
    private String postcodeInputBox = "#input-postcode";
    private String RegionOrStateDropdwon = "#input-zone";
    private String countryDropdwon = "#input-country";
    private String countryDropdwonOptions = "#input-country>option";
    private String continueButton = "input[type='submit']";


    // 2. Page Constructor
    public AddNewAddressPage(Page page) {
        this.page = page;
    }


    // 3. Page actions/methods
    public AddressBookPage addNewAddress( Map<String,String> addressData) {
        page.fill(firstNameInputBox, addressData.get("firstName"));
        page.fill(lastNameInputBox, addressData.get("lastName"));
        page.fill(address1InputBox, addressData.get("address1"));
        page.fill(cityInputBox, addressData.get("city"));
        page.fill(postcodeInputBox, addressData.get("postcode"));
        page.click(RegionOrStateDropdwon);
        page.selectOption(RegionOrStateDropdwon,addressData.get("regionOrState"));
        page.click(continueButton);
        return new AddressBookPage(page);
    }
}
