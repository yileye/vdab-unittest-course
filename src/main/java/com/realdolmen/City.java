package com.realdolmen;

public class City {
    private String name;
    private String postalCode;

    public City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
