package org.vaadin.addon.customfield.demo;

import java.util.Date;

import org.vaadin.addon.customfield.demo.data.Address;
import org.vaadin.addon.customfield.demo.data.City;
import org.vaadin.addon.customfield.demo.data.Person;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * Demonstrate the use of a form as a custom field within another form.
 */
public class NestedFormExample extends CustomComponent {
    private NestedPersonForm personForm;
    private VerticalLayout layout;
    private boolean embeddedAddress;

    public NestedFormExample(boolean embeddedAddress) {
        this.embeddedAddress = embeddedAddress;

        layout = new VerticalLayout();
        layout.setMargin(true);

        layout.addComponent(new Label(
                "An address form nested in a person form."));
        if (embeddedAddress) {
            layout.addComponent(new Label(
                    "The address fields are placed in the layout of the parent (person) form."));
            layout.addComponent(new Label(
                    "Note that in many cases the same result can be achieved with a property that maps subfields to the top level."));
        }

        layout.addComponent(getPersonTable());

        setCompositionRoot(layout);
    }

    /**
     * Creates a table with two person objects
     */
    public Table getPersonTable() {
        Table table = new Table();
        table.setPageLength(5);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setNullSelectionAllowed(true);
        table.addContainerProperty("Name", String.class, null);
        table.addListener(getTableValueChangeListener());
        Address address = new Address("Ruukinkatu 2–4", "20540", City.TURKU);
        Address address2 = new Address("Ruukinkatu 2–4", "20540", City.TURKU);
        Person person = new Person("Teppo", "Testaaja", new Date(100000000l),
                address);
        Person person2 = new Person("Taina", "Testaaja", new Date(200000000l),
                address2);
        Item item = table.addItem(person);
        item.getItemProperty("Name").setValue(
                person.getFirstName() + " " + person.getLastName());
        item = table.addItem(person2);
        item.getItemProperty("Name").setValue(
                person2.getFirstName() + " " + person2.getLastName());
        return table;
    }

    /**
     * Creates value change listener for the table
     */
    private Property.ValueChangeListener getTableValueChangeListener() {
        return new Property.ValueChangeListener() {
            private static final long serialVersionUID = 3228117666786809997L;

            public void valueChange(ValueChangeEvent event) {
                if (personForm != null) {
                    layout.removeComponent(personForm);
                }
                if (event.getProperty().getValue() != null) {
                    personForm = new NestedPersonForm((Person) event
                            .getProperty().getValue(), embeddedAddress);
                    personForm.setWidth("350px");
                    layout.addComponent(personForm);
                }
            }

        };
    }

}
