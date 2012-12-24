package com.esofthead.mycollab.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
	
	private static String[] getArrayDate(Date date) {
		SimpleDateFormat lv_formatter = new SimpleDateFormat(
		"yyyy:MM:dd:HH:mm:ss");
		String dateFormat = lv_formatter.format(date
				.getTime());
		String[] strPlit = dateFormat.split(":");
		
		return strPlit;
	}
	
	public static String getStringDateFromMilestone(Date date, Date milestoneDate) {
		String[] strPlit = getArrayDate(date);
		
		int year = Integer.parseInt(strPlit[0]);
		int month = Integer.parseInt(strPlit[1]);
		int day = Integer.parseInt(strPlit[2]);
		int hour = Integer.parseInt(strPlit[3]);
		int minute = Integer.parseInt(strPlit[4]);
		int second = Integer.parseInt(strPlit[5]);
		
		String[] strPlitCurrent = getArrayDate(milestoneDate);//getArrayDate(new GregorianCalendar().getTime());
		int yearCurrent = Integer.parseInt(strPlitCurrent[0]);
		int monthCurrent = Integer.parseInt(strPlitCurrent[1]);
		int dayCurrent = Integer.parseInt(strPlitCurrent[2]);
		int hourCurrent = Integer.parseInt(strPlitCurrent[3]);
		int minuteCurrent = Integer.parseInt(strPlitCurrent[4]);
		int secondCurrent = Integer.parseInt(strPlitCurrent[5]);
		
		int numYear = yearCurrent - year;
		int numMonth = monthCurrent - month;
		int numDay = dayCurrent - day;
		int numHour = hourCurrent - hour;
		int numMinute = minuteCurrent - minute;
		int numSecond = secondCurrent - second;
		
		String nextInstruction = "the next ";
		
		if (numYear > 0) {
			return (numYear > 1) ?  (numYear + " years ago") : (numYear + " year ago");
		} else if (numYear < 0) {
			return (numYear < -1) ?  (nextInstruction +  numYear + " year") : (nextInstruction + numYear + " year");
		}
		
		if ( numMonth > 0 && numMonth < 12) {
			return (numMonth > 1) ?  (numMonth + " months ago") : (numMonth + " months ago");
		} else if (numMonth < 0) {
			return (numMonth < -1) ?  (nextInstruction + numMonth + " months") : (nextInstruction + numMonth + " month");
		}
		
		if ( numDay > 0) {
			return (numDay > 1) ?  (numDay + " days ago") : (numDay + " day ago");
		} else if (numDay < 0) {
			return (numDay < -1) ?  (nextInstruction + numDay + " days") : (nextInstruction+ numDay + " day");
		}
		
		if ( numHour > 0 && numHour < 24) {
			return (numHour > 1) ?  (numHour + " hours ago") : (numHour + " hour ago");
		} else if (numHour < 0) {
			return (numHour < -1) ?  (nextInstruction + numHour + " hours") : (nextInstruction + numHour + " hour");
		}
		
		if ( numMinute > 0 && numMinute < 60) {
			return (numMinute > 1) ?  (numMinute + " minutes ago") : (numMinute + " minute ago");
		} else if (numMinute < 0) {
			return (numMinute < -1) ?  (nextInstruction + numMinute + " minutes") : (nextInstruction + numMinute + " minute");
		}
		
		if ( numSecond > 0 && numSecond < 60) {
			return (numSecond > 1) ?  (numSecond + " seconds ago") : (numSecond + " second ago");
		} else if (numSecond < 0) {
			return (numSecond > 1) ?  (nextInstruction + numSecond + " seconds") : (nextInstruction + numSecond + " second");
		}
		
		return "";
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
