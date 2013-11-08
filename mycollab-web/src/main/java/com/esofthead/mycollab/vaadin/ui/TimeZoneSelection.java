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
package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TimeZoneSelection extends CustomField{
	
	private ValueComboBox comboArea;
	private ValueComboBox comboTimezone;
	private List<String> lstLimeZoneArea = new ArrayList<String>();
	
	public TimeZoneSelection() {
		initUI();
	}
	
	private void initUI() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		
		comboArea = new ValueComboBox(false, TimezoneMapper.AREAS);
		comboArea.setWidth("100%");
		comboArea.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				lstLimeZoneArea.removeAll(lstLimeZoneArea);
				setCboTimeZone(event.getProperty().getValue().toString().trim());
			}
		});
		layout.addComponent(comboArea);
		
		lstLimeZoneArea.removeAll(lstLimeZoneArea);
		for (TimezoneExt timezone : TimezoneMapper.timeMap.values()) {
			if (timezone.getArea().equals(comboArea.getValue())) {
				lstLimeZoneArea.add(timezone.getDisplayName());
			}
		}
		
		String[] arrayTimezone = lstLimeZoneArea.toArray(new String[lstLimeZoneArea.size()]);
		
		comboTimezone = new ValueComboBox(false, arrayTimezone);
		comboTimezone.setWidth("100%");
		layout.addComponent(comboTimezone);
		
		this.setCompositionRoot(layout);
	}
	
	private void setCboTimeZone(String area) {
		
		for (TimezoneExt timezone : TimezoneMapper.timeMap.values()) {
			if (timezone.getArea().trim().equals(area)) {
				lstLimeZoneArea.add(timezone.getDisplayName());
			}
		}
		
		comboTimezone.removeAllItems();
		String[] arrayTimezone = lstLimeZoneArea.toArray(new String[lstLimeZoneArea.size()]);
		comboTimezone.loadData(arrayTimezone);
	}
	
	public void setTimeZone(TimezoneExt timeZone) {
		if (timeZone != null && !timeZone.getArea().equals("")) {
			comboArea.select(timeZone.getArea());
			setCboTimeZone(timeZone.getArea());
			comboTimezone.select(timeZone.getDisplayName());
		}
	}
	
	public TimezoneExt getTimeZone() {
		for (TimezoneExt timezone : TimezoneMapper.timeMap.values()) {
			if (timezone.getDisplayName().trim().equals(comboTimezone.getValue())) {
				return timezone;
			}
		}
		return null;
	}

	@Override
	public Class<?> getType() {
		return null;
	}

}
