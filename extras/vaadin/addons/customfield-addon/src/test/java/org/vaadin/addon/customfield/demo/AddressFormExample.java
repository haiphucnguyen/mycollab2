package org.vaadin.addon.customfield.demo;

import org.vaadin.addon.customfield.demo.data.Address;
import org.vaadin.addon.customfield.demo.data.City;
import org.vaadin.addon.customfield.demo.field.AddressField;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Demonstrate a custom field which is a form, and contains another custom field
 * for the selection of a city.
 */
public class AddressFormExample extends CustomComponent implements Component {

    private VerticalLayout layout;

    public AddressFormExample() {
        layout = new VerticalLayout();
        layout.setMargin(true);

        layout.addComponent(new Label("Custom field for editing an Address"));

        Address address = new Address("Ruukinkatu 2-4", "20540", City.TURKU);
        AddressField field = new AddressField();
        field.setValue(address);
        layout.addComponent(field);

        setCompositionRoot(layout);
    }

}
