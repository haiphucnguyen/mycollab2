package org.vaadin.addon.customfield.demo.field;


import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.addon.customfield.demo.data.Address;

import com.vaadin.data.Buffered;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;

/**
 * Nested form for the Address object of the Person object
 */
public class AddressField extends CustomField {
    private static final long serialVersionUID = -7303229471599487430L;
    private Form addressForm;

    /**
     * Field factory creating a custom field for city selection.
     */
    protected static class AddressFieldFormFactory extends DefaultFieldFactory {
        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            if ("city".equals(propertyId)) {
                CityField field = new CityField();
                field.setCaption(createCaptionByPropertyId(propertyId));
                return field;
            }
            return super.createField(item, propertyId, uiContext);
        }
    }

    public AddressField() {
        this(null);
    }

    public AddressField(Form parentForm) {
        if (parentForm != null) {
            addressForm = new EmbeddedForm(parentForm);
        } else {
            addressForm = new Form();
        }
        addressForm.setCaption("Address");
        addressForm.setWriteThrough(false);
        addressForm.setFormFieldFactory(new AddressFieldFormFactory());
        setCompositionRoot(addressForm);
    }

    @Override
    public void setInternalValue(Object newValue) throws ReadOnlyException,
            ConversionException {
        // create the address if not given
        Address address = (newValue instanceof Address) ? (Address) newValue
                : new Address();

        super.setInternalValue(address);

        // set item data source and visible properties in a single operation to
        // avoid creating fields multiple times
        List<String> visibleProperties = new ArrayList<String>();
        visibleProperties.add("streetAddress");
        visibleProperties.add("postalCode");
        visibleProperties.add("city");
        addressForm.setItemDataSource(new BeanItem<Address>(address),
                visibleProperties);
    }

    /**
     * commit changes of the address form
     */
    @Override
    public void commit() throws Buffered.SourceException, InvalidValueException {
        super.commit();
        addressForm.commit();
    }
    
    /**
     * discard changes of the address form
     */
    @Override
    public void discard() throws Buffered.SourceException {
        super.discard();
        addressForm.discard();
    }
    
    @Override
    public Class<?> getType() {
        return Address.class;
    }
}