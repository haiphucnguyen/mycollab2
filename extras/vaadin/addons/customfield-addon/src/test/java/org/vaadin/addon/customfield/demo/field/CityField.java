package org.vaadin.addon.customfield.demo.field;

import org.vaadin.addon.customfield.FieldWrapper;
import org.vaadin.addon.customfield.demo.data.City;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

/**
 * Field for selecting a city, with optional filtering by country. The value
 * etc. are handled by forwarding requests to the wrapped combo box, and another
 * combo box controls filtering of the main combo.
 */
public class CityField extends FieldWrapper<City> {

    private BeanItemContainer<City> cityContainer;
    private BeanItemContainer<String> countryContainer;

    private VerticalLayout layout;

    public CityField() {
        // no conversions
        super(new ComboBox(), null, City.class);

        layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSpacing(true);

        final ComboBox countryCombo = new ComboBox(null, getCountryContainer());
        countryCombo.setImmediate(true);
        countryCombo.setInputPrompt("Filter by country");
        layout.addComponent(countryCombo);

        final ComboBox combo = (ComboBox) getWrappedField();
        combo.setContainerDataSource(getCityContainer());
        combo.setItemCaptionPropertyId("city");
        layout.addComponent(combo);

        countryCombo.addListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                // filter by country
                getCityContainer().removeAllContainerFilters();
                getCityContainer().addContainerFilter("country",
                        (String) event.getProperty().getValue(), true, true);
            }
        });

        setCompositionRoot(layout);
    }

    protected BeanItemContainer<City> getCityContainer() {
        if (cityContainer == null) {
            cityContainer = new BeanItemContainer<City>(City.class);
            for (City city : City.cities()) {
                cityContainer.addBean(city);
            }
        }
        return cityContainer;
    }

    protected BeanItemContainer<String> getCountryContainer() {
        if (countryContainer == null) {
            countryContainer = new BeanItemContainer<String>(String.class);
            for (City city : City.cities()) {
                countryContainer.addBean(city.getCountry());
            }
        }
        return countryContainer;
    }
}
