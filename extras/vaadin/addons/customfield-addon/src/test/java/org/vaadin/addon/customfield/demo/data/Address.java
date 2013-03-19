package org.vaadin.addon.customfield.demo.data;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 9082277794376939663L;

    private String streetAddress = "";
    private String postalCode = null;
    private City city;

    public Address() {
    }

    public Address(String streetAddress, String postalCode, City city) {
        super();
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
