package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

import java.util.Map;

public class AddNewAddressPage {

    Page page;

    // 1. String Locators - OR
    private final String firstNameInputBox = "#input-firstname";
    private final String lastNameInputBox = "#input-lastname";
    private final String address1InputBox = "#input-address-1";
    private final String cityInputBox = "#input-city";
    private final String postcodeInputBox = "#input-postcode";
    private final String RegionOrStateDropdown = "#input-zone";
    private final String countryDropdown = "#input-country";
    private final String countryDropdownOptions = "#input-country>option";
    private final String continueButton = "input[type='submit']";


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
        page.click(RegionOrStateDropdown);
        page.selectOption(RegionOrStateDropdown,addressData.get("regionOrState"));
        page.click(continueButton);
        return new AddressBookPage(page);
    }
}
