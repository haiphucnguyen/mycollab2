package org.vaadin.addon.customfield.demo;

import org.vaadin.addon.customfield.demo.data.City;
import org.vaadin.addon.customfield.demo.field.CityField;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Demonstrate a custom field which wraps another field and provides additional
 * layout and control elements.
 */
public class CityFieldExample extends CustomComponent implements Component {

    private VerticalLayout layout;

    public CityFieldExample() {
        layout = new VerticalLayout();
        layout.setMargin(true);

        layout.addComponent(new Label("Custom field for selecting a city"));

        final CityField field = new CityField();
        field.setImmediate(true);
        layout.addComponent(field);

        field.addListener(new ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                if (value instanceof City) {
                    layout.getWindow().showNotification(
                            "The selected city is " + value + ".");
                } else {
                    layout.getWindow().showNotification("No city selected.");
                }
            }
        });

        setCompositionRoot(layout);
    }

}
