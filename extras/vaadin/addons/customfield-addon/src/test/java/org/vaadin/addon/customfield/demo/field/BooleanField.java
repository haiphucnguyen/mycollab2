package org.vaadin.addon.customfield.demo.field;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * An example of a custom field for editing a boolean value. The field is
 * composed of multiple components, and could also edit a more complex data
 * structures. Here, the commit etc. logic is not overridden.
 */
public class BooleanField extends CustomField {

    public BooleanField() {
        VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new Label("Please click the button"));

        final Button button = new Button("Click me");
        button.addListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = getValue();
                boolean newValue = true;
                if ((value instanceof Boolean) && ((Boolean) value)) {
                    newValue = false;
                }
                setValue(newValue);
                button.setCaption(newValue ? "On" : "Off");
            }
        });
        layout.addComponent(button);

        setCompositionRoot(layout);
    }

    @Override
    public Class<?> getType() {
        return Boolean.class;
    }
}