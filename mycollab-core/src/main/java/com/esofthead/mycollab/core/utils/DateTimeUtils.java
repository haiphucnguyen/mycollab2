package com.esofthead.mycollab.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	
	public static Date convertDate(Date value) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(df.format(value.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date addDayDuration(Date date, int duration) {
		return new Date(date.getTime() + duration*24*60*60*1000);
	}
	
	public static Date subtractDayDuration(Date date, int duration) {
		return new Date(date.getTime() - duration*24*60*60*1000);
	}
	
	public static Date getDateByString(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
