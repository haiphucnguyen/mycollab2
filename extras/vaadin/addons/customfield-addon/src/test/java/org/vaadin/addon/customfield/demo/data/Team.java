package org.vaadin.addon.customfield.demo.data;

import java.io.Serializable;
import java.util.Set;

public class Team implements Serializable {
    private Person manager;
    private Set<Person> members;

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public Person getManager() {
        return manager;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public Set<Person> getMembers() {
        return members;
    }

}
