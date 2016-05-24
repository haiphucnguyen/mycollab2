/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.web.ui;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TimeZoneSelectionField extends CustomField<String> {
    private boolean isVerticalDisplay = true;
    private ValueComboBox areaSelection;
    private ComboBox timezoneSelection;

    public TimeZoneSelectionField(boolean isVerticalDisplay) {
        this.isVerticalDisplay = isVerticalDisplay;
        areaSelection = new ValueComboBox(false, TimezoneMapper.AREAS);
        areaSelection.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                setCboTimeZone((String) areaSelection.getValue());
            }
        });
        timezoneSelection = new ComboBox();
        timezoneSelection.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
    }

    @Override
    protected Component initContent() {
        if (isVerticalDisplay) {
            MVerticalLayout layout = new MVerticalLayout().withMargin(false);
            layout.with(areaSelection, timezoneSelection);
            return layout;
        } else {
            MHorizontalLayout layout = new MHorizontalLayout();
            layout.with(areaSelection, timezoneSelection);
            return layout;
        }
    }

    private void setCboTimeZone(String area) {
        timezoneSelection.removeAllItems();

    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        String value = (String) newDataSource.getValue();
        if (value != null) {

        }
        super.setPropertyDataSource(newDataSource);
    }

    @Override
    public String getValue() {
//        TimezoneExt timezoneExt = getTimeZone();
//        if (timezoneExt != null) {
//            return timezoneExt.getId();
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public void setValue(String newFieldValue) throws ReadOnlyException, Converter.ConversionException {
        super.setValue(newFieldValue);
    }

    @Override
    public void commit() throws SourceException, Validator.InvalidValueException {
//        TimezoneExt timezoneExt = getTimeZone();
//        if (timezoneExt != null) {
//            setInternalValue(timezoneExt.getId());
//        }
        super.commit();
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
