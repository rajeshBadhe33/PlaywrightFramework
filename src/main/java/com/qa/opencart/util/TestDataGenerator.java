package com.qa.opencart.util;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestDataGenerator {

    public Map<String, String> generateAddressData(String country){
        Map<String,String> addressData= new HashMap<>();
        Faker faker = new Faker(new Locale(country));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String postcode = faker.address().zipCode();
        String regionOrState = "Kent";
//        String country1= faker.address().country();

        addressData.put("firstName",firstName);
        addressData.put("lastName",lastName);
        addressData.put("address1",address);
        addressData.put("city",city);
        addressData.put("postcode",postcode);
        addressData.put("regionOrState",regionOrState);
//        addressData.put("country",country1);
        return addressData;
    }

//    Country	Locale Code	Example
//    United States	en-US	new Faker(new Locale("en-US"))
//    United Kingdom	en-GB	new Faker(new Locale("en-GB"))
//    Germany	de-DE	new Faker(new Locale("de-DE"))
//    France	fr-FR	new Faker(new Locale("fr-FR"))
//    India	en-IN	new Faker(new Locale("en-IN"))
//    Japan	ja-JP	new Faker(new Locale("ja-JP"))
//    China	zh-CN	new Faker(new Locale("zh-CN"))
//    Brazil	pt-BR	new Faker(new Locale("pt-BR"))
//    Russia	ru-RU	new Faker(new Locale("ru-RU"))


}
