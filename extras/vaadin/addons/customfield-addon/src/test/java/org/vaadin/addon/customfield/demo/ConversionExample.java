package org.vaadin.addon.customfield.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.vaadin.addon.customfield.FieldWrapper;
import org.vaadin.addon.customfield.demo.data.City;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

/**
 * Demonstrate a custom field which wraps another field and provides additional
 * layout and control elements.
 * 
 * The multi-select component operates on strings, while the underlying property
 * contains a set of City POJOs. Conversions are made between the two by the
 * field wrapper.
 */
public class ConversionExample extends CustomComponent implements Component {

    private final class CityStringSelect extends
            FieldWrapper<Set> {
        private CityStringSelect(Field wrappedField,
                Class<? extends Set> propertyType) {
            super(wrappedField, propertyType);

            VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(wrappedField);
            setCompositionRoot(layout);
        }

        @Override
        protected Object format(Set value) {
            // map a set of cities to a set of city names
            Set<String> set = new HashSet<String>();
            if (value != null) {
                for (Object city : value) {
                    if (city instanceof City) {
                        set.add(((City) city).getCity());
                    }
                }
            }
            return set;
        }

        @Override
        protected Set parse(Object formattedValue)
                throws ConversionException {
            // map a set of cities to a set of city names
            Set<City> set = new HashSet<City>();
            if (formattedValue instanceof Set) {
                for (Object city : (Set) formattedValue) {
                    set.add(cityMap.get(city));
                }
            }
            return set;
        }
    }

    private Map<String, City> cityMap = new HashMap<String, City>();
    private BeanItemContainer<String> cityNameContainer = new BeanItemContainer<String>(
            String.class);

    private VerticalLayout layout;

    public ConversionExample() {
        layout = new VerticalLayout();
        layout.setMargin(true);

        layout.addComponent(new Label(
                "Conversion between city names and a set of City instances for a multi-select component."));

        for (City city : City.cities()) {
            cityNameContainer.addBean(city.getCity());
            cityMap.put(city.getCity(), city);
        }
        
        // the select contains strings
        TwinColSelect select = new TwinColSelect(null, cityNameContainer);
        final FieldWrapper<Set> field = new CityStringSelect(select,
                Set.class);

        final ObjectProperty objectProperty = new ObjectProperty(
                new HashSet<City>());
        field.setPropertyDataSource(objectProperty);

        field.setImmediate(true);
        layout.addComponent(field);

        field.addListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                if (value instanceof Set) {
                    layout.getWindow().showNotification(
                            "The selected cities are " + value + ".");
                } else {
                    layout.getWindow().showNotification("No cities selected.");
                }
            }
        });

        setCompositionRoot(layout);
    }

}
