package org.vaadin.addon.customfield.demo.data;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private static final long serialVersionUID = -8008027767038119796L;
    private String firstName = "";
    private String lastName = "";
    private Date birthdate;
    private Address address;

    public Person() {
    }
    
    public Person(String firstName, String lastName, Date birthdate,
            Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
