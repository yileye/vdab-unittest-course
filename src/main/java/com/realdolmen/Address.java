package com.realdolmen;

public class Address {
    private String street;
    private String number;
    private City city;

    public Address(String street, String number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public City getCity() {
        return city;
    }
}
