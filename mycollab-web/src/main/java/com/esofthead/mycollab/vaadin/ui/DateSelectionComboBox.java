/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class DateSelectionComboBox extends ValueComboBox{
	
	public static String EQUAL = "Equals";
	public static String NOTON = "Not On";
	public static String AFTER = "After";
	public static String BEFORE = "Before";
	public static String LAST7DAYS = "Last 7 Days";
	public static String NEXT7DAYS = "Next 7 Days";
	public static String LAST30DAYS = "Last 30 Days";
	public static String NEXT30DAYS = "Next 30 Days";
	public static String LASTMONTH = "Last Month";
	public static String THISMONTH = "This Month";
	public static String NEXTMONTH = "Next Month";
	public static String LASTYEAR= "Last Year";
	public static String THISYEAR = "This Year";
	public static String NEXTYEAR = "Next Year";
	public static String ISBETWEEN = "Is Between";
	
    public DateSelectionComboBox() {
        super();
        this.loadData(EQUAL, NOTON, AFTER, BEFORE, LAST7DAYS, NEXT7DAYS,
        				LAST30DAYS, NEXT30DAYS, LASTMONTH, THISMONTH, NEXTMONTH,
        				LASTYEAR, THISYEAR, NEXTYEAR, ISBETWEEN);
    }
}
