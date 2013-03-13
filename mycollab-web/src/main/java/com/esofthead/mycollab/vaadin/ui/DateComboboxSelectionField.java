package com.esofthead.mycollab.vaadin.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DateComboboxSelectionField extends CustomField {

	private ComboBox cboYear;
	private ComboBox cboMonth;
	private ComboBox cboDate;
	private Calendar calendar = Calendar.getInstance();
	private int numOfDays;
	
	public static String JANUARY = "January";
	public static String FEBRUARY = "February";
	public static String MARCH = "March";
	public static String APRIL = "April";
	public static String MAY = "May";
	public static String JUNE = "June";
	public static String JULY = "July";
	public static String AUGUST = "August";
	public static String SEPTEMBER = "September";
	public static String OCTOBER = "October";
	public static String NOVEMBER = "November";
	public static String DECEMBER = "December";
	
	
	
	
	public DateComboboxSelectionField() {
		initUI();
	}
	
	private String formatMonth(String month) {
	    SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	    SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
	    try {
			return monthDisplay.format(monthParse.parse(month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void addMonthItems() {
		for (int i = 1; i <= 12; i++) {
			cboMonth.addItem(formatMonth(i + ""));
		}
	}
	
	public void setDate(Date date) {
		calendar.setTime(new Date());
		numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("Date: " + numOfDays);
	}
	
	private void initUI() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		
		cboMonth = new ComboBox();
		addMonthItems();
		cboMonth.setWidth("100px");
		layout.addComponent(cboMonth);
		layout.setComponentAlignment(cboMonth, Alignment.TOP_CENTER);
		
		cboDate = new ComboBox();
		cboDate.addItem("31");
		cboDate.setWidth("50px");
		layout.addComponent(cboDate);
		layout.setComponentAlignment(cboDate, Alignment.TOP_CENTER);
		
		cboYear = new ComboBox();
		cboYear.addItem("2013");
		cboYear.setWidth("70px");
		layout.addComponent(cboYear);
		layout.setComponentAlignment(cboYear, Alignment.TOP_CENTER);
		
		setCompositionRoot(layout);
	}

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
