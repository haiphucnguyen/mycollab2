package org.vaadin.addon.customfield.demo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class City {
    public static City TURKU = new City("Turku", "Finland");
    public static City HELSINKI = new City("Helsinki", "Finland");
    public static City PARIS = new City("Paris", "France");
    public static City NICE = new City("Nice", "France");
    public static City MADRID = new City("Madrid", "Spain");

    private static List<City> cities;

    public static Collection<City> cities() {
        if (cities == null) {
            cities = new ArrayList<City>();
            cities.add(TURKU);
            cities.add(HELSINKI);
            cities.add(PARIS);
            cities.add(NICE);
            cities.add(MADRID);
        }
        return cities;
    }

    private final String city;
    private final String country;

    protected City(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}