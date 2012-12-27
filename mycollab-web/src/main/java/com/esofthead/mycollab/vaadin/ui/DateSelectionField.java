/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.Calendar;
import java.util.Date;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class DateSelectionField extends GridLayout{
	
	private DateField dateStart = new DateField();
	private DateField dateEnd = new DateField();
	
	DateSelectionComboBox dateSelectionBox;
	
	public DateSelectionField() {
		
		setDateWidth(80);
		this.setSpacing(true);
		dateSelectionBox = new DateSelectionComboBox();
		dateSelectionBox.setFilteringMode(Filtering.FILTERINGMODE_OFF);
		dateSelectionBox.setImmediate(true);
		
		dateStart.setResolution(PopupDateField.RESOLUTION_DAY);
		dateEnd.setResolution(PopupDateField.RESOLUTION_DAY);
		
        dateSelectionBox.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				setComponentByValue(event);
			}
		});
        this.addComponent(dateSelectionBox, 0, 0);
    }
	
	private void setComponentByValue(ValueChangeEvent event) {
		
		removeAllDatefield();
		
		String filterStr = (String) event.getProperty().getValue();
		filterStr = (filterStr != null) ? filterStr : "";
		if (filterStr.equals(DateSelectionComboBox.EQUAL)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.NOTON)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.AFTER)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.BEFORE)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.ISBETWEEN)) {
			addTwoDate();
		}
	}
	
	public SearchField getValues() {
		String filterStr = (String) dateSelectionBox.getValue();
		filterStr = (filterStr != null) ? filterStr : "";
		Date fDate = (Date) dateStart.getValue();
		Date tDate = (Date) dateEnd.getValue();
		
		if (filterStr.equals(DateSelectionComboBox.EQUAL)) {
			
			return new DateSearchField(SearchField.AND, DateSearchField.EQUAL, fDate);
			
		} else if (filterStr.equals(DateSelectionComboBox.NOTON)) {
			
			return new DateSearchField(SearchField.AND, DateSearchField.NOTEQUAL, fDate);
			
		} else if (filterStr.equals(DateSelectionComboBox.AFTER)) {
			
			return new DateSearchField(SearchField.AND, DateSearchField.GREATERTHAN, fDate);
			
		} else if (filterStr.equals(DateSelectionComboBox.BEFORE)) {
			
			return new DateSearchField(SearchField.AND, DateSearchField.LESSTHAN, fDate);
			
		} else if (filterStr.equals(DateSelectionComboBox.LAST7DAYS)) {
			
			return getLastNumberDays(7);
			
		} else if (filterStr.equals(DateSelectionComboBox.NEXT7DAYS)) {
			
			return getNextNumberDays(7);
			
		} else if (filterStr.equals(DateSelectionComboBox.LAST30DAYS)) {
			
			return getLastNumberDays(30);
			
		} else if (filterStr.equals(DateSelectionComboBox.NEXT30DAYS)) {
			
			return getNextNumberDays(30);
			
		} else if (filterStr.equals(DateSelectionComboBox.LASTMONTH)) {
			
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH) - 1);
			
		} else if (filterStr.equals(DateSelectionComboBox.THISMONTH)) {
			
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH));
			
		} else if (filterStr.equals(DateSelectionComboBox.NEXTMONTH)) {
			
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH) + 1);
			
		} else if (filterStr.equals(DateSelectionComboBox.LASTYEAR)) {
			
		} else if (filterStr.equals(DateSelectionComboBox.THISMONTH)) {
			
		} else if (filterStr.equals(DateSelectionComboBox.NEXTYEAR)) {
			
		} else if (filterStr.equals(DateSelectionComboBox.ISBETWEEN)) {
			
			return new RangeDateSearchField(fDate, tDate);
			
		}
		
		return null;
	}
	
	
	private RangeDateSearchField getLastNumberDays(int duration) {
		Date fDate = DateTimeUtils.subtractOrAddDayDuration(new Date(), duration);
		Date tDate = new Date();
		return new RangeDateSearchField(fDate, tDate);
	}
	
	private RangeDateSearchField getNextNumberDays(int duration) {
		Date fDate = new Date();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration(new Date(), duration);
		return new RangeDateSearchField(fDate, tDate);
	}
	
	
	private RangeDateSearchField getMonthFilterByDuration(int monthDuration) {
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.getInstance().get(Calendar.YEAR), monthDuration, 1);
		Date fDate = c.getTime();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration(new Date(), monthMaxDays);
		return new RangeDateSearchField(fDate, tDate);
	}
	
	private void addOneDate() {
		this.setRows(2);
		this.addComponent(dateStart, 0, 1);
	}
	
	private void setDateWidth (float width) {
		dateStart.setWidth(width, Sizeable.UNITS_PIXELS);
		dateEnd.setWidth(width, Sizeable.UNITS_PIXELS);
	}
	
	private void addTwoDate() {
		this.setRows(2);
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		hLayout.addComponent(dateStart);
		hLayout.addComponent(dateEnd);
		this.addComponent(hLayout, 0, 1);
	}
	
	private void removeAllDatefield() {
		for (int i = 0; i < this.getColumns(); i++) {
			removeComponent(i, 1);
		}
	}
    
    public void setDateFormat(String dateFormat) {
    	dateStart.setDateFormat(dateFormat);
    	dateEnd.setDateFormat(dateFormat);
    }
}
