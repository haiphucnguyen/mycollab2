package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.common.TimezoneMapper;
import com.esofthead.mycollab.common.TimezoneMapper.TimezoneExt;
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
		if (timeZone != null) {
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
