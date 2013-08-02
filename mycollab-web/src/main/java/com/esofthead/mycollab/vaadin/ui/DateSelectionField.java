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
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class DateSelectionField extends GridLayout{
	
	private DateField dateStart = new DateField();
	private DateField dateEnd = new DateField();
	
	DateSelectionComboBox dateSelectionBox;
	
	public DateField getDateStart() {
		return dateStart;
	}

	public void setDateStart(DateField dateStart) {
		this.dateStart = dateStart;
	}

	public DateField getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(DateField dateEnd) {
		this.dateEnd = dateEnd;
	}

	public DateSelectionComboBox getDateSelectionBox() {
		return dateSelectionBox;
	}

	public void setDateSelectionBox(DateSelectionComboBox dateSelectionBox) {
		this.dateSelectionBox = dateSelectionBox;
	}

	public DateSelectionField(String width) {
		this();
		dateSelectionBox.setWidth(width);
	}
	
	public DateSelectionField() {
		
		setDateWidth(120);
		this.setSpacing(true);
		dateSelectionBox = new DateSelectionComboBox();
		dateSelectionBox.setFilteringMode(Filtering.FILTERINGMODE_OFF);
		dateSelectionBox.setImmediate(true);
		
		dateStart.setResolution(PopupDateField.RESOLUTION_DAY);
		dateEnd.setResolution(PopupDateField.RESOLUTION_DAY);
		
        dateSelectionBox.addListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				String filterStr = (String) event.getProperty().getValue();
				filterStr = (filterStr != null) ? filterStr : "";
				
				setComponentByValue(filterStr);
			}
		});
        this.addComponent(dateSelectionBox, 0, 0);
    }
	
	public void setComponentByValue(String filterStr) {
		
		removeAllDatefield();
		
		if (filterStr.equals(DateSelectionComboBox.EQUAL)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.NOTON)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.AFTER)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.BEFORE)) {
			addOneDate();
		} else if (filterStr.equals(DateSelectionComboBox.ISBETWEEN)) {
			addRangeDate();
		}
	}
	
	public SearchField getValue() {
		String filterStr = (String) dateSelectionBox.getValue();
		filterStr = (filterStr != null) ? filterStr : "";
		Date fDate = (Date) dateStart.getValue();
		Date tDate = (Date) dateEnd.getValue();
		
		if (filterStr.equals(DateSelectionComboBox.ISBETWEEN)) {
			if (fDate == null || tDate == null) return null;
			return new RangeDateSearchField(fDate, tDate);
		}
		
		if (filterStr.equals(DateSelectionComboBox.EQUAL)) {
			if (fDate == null) return null;
			return new DateSearchField(SearchField.AND, DateSearchField.EQUAL, fDate);
		}

		if (filterStr.equals(DateSelectionComboBox.NOTON)) {
			if (fDate == null) return null;
			return new DateSearchField(SearchField.AND, DateSearchField.NOTEQUAL, fDate);
		}
		
		if (filterStr.equals(DateSelectionComboBox.AFTER)) {
			if (fDate == null) return null;
			return new DateSearchField(SearchField.AND, DateSearchField.GREATERTHAN, fDate);
		}

		if (filterStr.equals(DateSelectionComboBox.BEFORE)) {
			if (fDate == null) return null;
			return new DateSearchField(SearchField.AND, DateSearchField.LESSTHAN, fDate);
		}

		if (filterStr.equals(DateSelectionComboBox.LAST7DAYS)) {
			return getLastNumberDays(7);
		}

		if (filterStr.equals(DateSelectionComboBox.NEXT7DAYS)) {
			return getNextNumberDays(7);
		}

		if (filterStr.equals(DateSelectionComboBox.LAST30DAYS)) {
			return getLastNumberDays(30);
		}

		if (filterStr.equals(DateSelectionComboBox.NEXT30DAYS)) {
			return getNextNumberDays(30);
		}

		if (filterStr.equals(DateSelectionComboBox.LASTMONTH)) {
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH) - 1);
		}

		if (filterStr.equals(DateSelectionComboBox.THISMONTH)) {
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH));
		}
		
		if (filterStr.equals(DateSelectionComboBox.NEXTMONTH)) {
			return getMonthFilterByDuration(Calendar.getInstance().get(Calendar.MONTH) + 1);
		}
		
		if (filterStr.equals(DateSelectionComboBox.LASTYEAR)) {
			return getYearFilterByDuration(Calendar.getInstance().get(Calendar.YEAR) - 1);
		}

		if (filterStr.equals(DateSelectionComboBox.THISYEAR)) {
			return getYearFilterByDuration(Calendar.getInstance().get(Calendar.YEAR));
		}

		if (filterStr.equals(DateSelectionComboBox.NEXTYEAR)) {
			return getYearFilterByDuration(Calendar.getInstance().get(Calendar.YEAR) + 1);
		}
		
		return null;
	}
	
	
	public void setDefaultSelection() {
		dateSelectionBox.setValue(null);
	}
	
	private RangeDateSearchField getLastNumberDays(int duration) {
		Date fDate = DateTimeUtils.subtractOrAddDayDuration(new Date(), -duration);
		Date tDate = new Date();
		return new RangeDateSearchField(fDate, tDate);
	}
	
	private RangeDateSearchField getNextNumberDays(int duration) {
		Date fDate = new Date();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration(new Date(), duration);
		return new RangeDateSearchField(fDate, tDate);
	}
	
	private RangeDateSearchField getYearFilterByDuration(int yearDuration) {
		
		Calendar c = Calendar.getInstance();
		c.set(yearDuration, 0, 1);
		int yearMaxDays = c.getActualMaximum(Calendar.DAY_OF_YEAR);
		Date fDate = c.getTime();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration((Date) fDate.clone(), yearMaxDays -1);
		return new RangeDateSearchField(fDate, tDate);
	}
	
	private RangeDateSearchField getMonthFilterByDuration(int monthDuration) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.getInstance().get(Calendar.YEAR), monthDuration, 1);
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date fDate = c.getTime();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration(fDate, monthMaxDays -1);
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
	
	public void addRangeDate() {
		this.setRows(2);
		if (ScreenSize.hasSupport1024Pixels()) {
			HorizontalLayout dateStartGroup = new HorizontalLayout();
			dateStartGroup.setSpacing(true);
			Label lbFrom = new Label("From:");
			lbFrom.setWidth("30px");
			dateStartGroup.addComponent(lbFrom);
			dateStartGroup.setComponentAlignment(lbFrom, Alignment.MIDDLE_RIGHT);
			dateStartGroup.addComponent(dateStart);
			dateStartGroup.setComponentAlignment(dateStart, Alignment.MIDDLE_LEFT);
			
			HorizontalLayout dateEndGroup = new HorizontalLayout();
			dateEndGroup.setSpacing(true);
			Label lbTo = new Label("To:");
			lbTo.setWidth("30px");
			dateEndGroup.addComponent(lbTo);
			dateEndGroup.setComponentAlignment(lbTo, Alignment.MIDDLE_RIGHT);
			dateEndGroup.addComponent(dateEnd);
			dateEndGroup.setComponentAlignment(dateEnd, Alignment.MIDDLE_LEFT);
			
			VerticalLayout vLayout = new VerticalLayout();
			vLayout.setSpacing(true);
			vLayout.addComponent(dateStartGroup);
			vLayout.addComponent(dateEndGroup);
			this.addComponent(vLayout, 0, 1);
			
		} else if (ScreenSize.hasSupport1280Pixels()) {
			HorizontalLayout hLayout = new HorizontalLayout();
			hLayout.setSpacing(true);
			hLayout.addComponent(dateStart);
			hLayout.addComponent(dateEnd);
			this.addComponent(hLayout, 0, 1);
		}
	}
	
	public void removeAllDatefield() {
		for (int i = 0; i < this.getColumns(); i++) {
			removeComponent(i, 1);
		}
		
		dateStart.setValue(new Date());
		dateStart.setValue(new Date());
	}
    
    public void setDateFormat(String dateFormat) {
    	dateStart.setDateFormat(dateFormat);
    	dateEnd.setDateFormat(dateFormat);
    }
}
