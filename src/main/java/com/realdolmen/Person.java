package com.realdolmen;

import java.util.Date;

public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Address address;

    public Person(String firstName, String lastName, Date birthDate, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public String name() {
        return firstName + " " + lastName;
    }
}
