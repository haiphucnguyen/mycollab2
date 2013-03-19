package org.vaadin.addon.customfield.demo;

import org.vaadin.addon.customfield.demo.data.Person;
import org.vaadin.addon.customfield.demo.field.AddressField;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

/**
 * Example of nested forms
 */
public class NestedPersonForm extends Form {
    private static final long serialVersionUID = 5527857935849023544L;
    private BeanItem<Person> beanItem;
    private final boolean embeddedAddress;

    /**
     * Creates a person form which contains nested form for the persons address
     */
    public NestedPersonForm(Person person, boolean embeddedAddress) {
        this.embeddedAddress = embeddedAddress;

        beanItem = new BeanItem<Person>(person);
        setCaption("Update person details");
        setWriteThrough(false);
        setFormFieldFactory(new PersonFieldFactory());
        setItemDataSource(beanItem);
        // set the order of the fields
        setVisibleItemProperties(new String[] { "firstName", "lastName",
                "address", "birthdate" });
        getFooter().addComponent(getButtonsLayout());
        getFooter().setMargin(false, false, true, true);
    }

    /**
     * Get apply and discard button in the layout
     */
    private Component getButtonsLayout() {
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Discard changes",
                new Button.ClickListener() {
                    private static final long serialVersionUID = -7193071888873947950L;
                    public void buttonClick(ClickEvent event) {
                        NestedPersonForm.this.discard();
                    }
                });
        buttons.addComponent(discardChanges);
        buttons.setComponentAlignment(discardChanges, "middle");

        Button apply = new Button("Apply", new Button.ClickListener() {
            private static final long serialVersionUID = -6286247681458829334L;
            public void buttonClick(ClickEvent event) {
                try {
                    NestedPersonForm.this.commit();
                } catch (Exception e) {
                    // Ignored, we'll let the Form handle the errors
                }
            }
        });
        buttons.addComponent(apply);
        return buttons;
    }

    /**
     * Field factory for person form
     */
    private class PersonFieldFactory extends DefaultFieldFactory {
        private static final long serialVersionUID = -1842024616269507877L;
        // reuse the address field - required by EmbeddedForm
        private AddressField addressField;

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f = super.createField(item, propertyId, uiContext);
            if ("address".equals(propertyId)) {
                // create a custom field for the Address object
                if (addressField == null) {
                    Form form = (embeddedAddress && uiContext instanceof Form) ? (Form) uiContext
                            : null;
                    addressField = new AddressField(form);
                }
                f = addressField;
            }
            return f;
        }
    }
}
