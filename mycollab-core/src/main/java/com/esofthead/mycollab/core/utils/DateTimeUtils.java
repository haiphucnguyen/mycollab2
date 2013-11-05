package com.esofthead.mycollab.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.esofthead.mycollab.core.MyCollabException;

public class DateTimeUtils {
	private static DateTimeZone utcZone = DateTimeZone.UTC;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MM/dd/yyyy");

	private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss Z");

	public static Date convertDate(Date value) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = df.parse(df.format(value.getTime()));
		} catch (ParseException e) {
			throw new MyCollabException(e);
		}
		return date;
	}

	public static Date getDateByStringWithFormat(String strDate, String format) {
		if (strDate != null && !strDate.equals("") && format != null
				&& !format.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			try {
				return formatter.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return new Date();
	}

	public static String getStringDateFromNow(Date dateTime) {
		if (dateTime == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Date current = Calendar.getInstance().getTime();
		long diffInSeconds = (current.getTime() - dateTime.getTime()) / 1000;

		long sec = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		long min = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60
				: diffInSeconds;
		long hrs = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24
				: diffInSeconds;
		long days = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30
				: diffInSeconds;
		long months = (diffInSeconds = (diffInSeconds / 30)) >= 12 ? diffInSeconds % 12
				: diffInSeconds;
		long years = (diffInSeconds = (diffInSeconds / 12));

		if (years > 0) {
			sb.append((years == 1) ? "a year" : (years + " years"));
			if (years <= 6 && months > 0) {
				sb.append(" and ");
				sb.append((months == 1) ? "a month" : (months + " months"));
			}
		} else if (months > 0) {
			sb.append((months == 1) ? "a month" : (months + " months"));
			if (months <= 6 && days > 0) {
				sb.append(" and ");
				sb.append((days == 1) ? "a day" : (days + " days"));
			}
		} else if (days > 0) {
			sb.append((days == 1) ? "a day" : (days + " days"));
			if (days <= 3 && hrs > 0) {
				sb.append(" and ");
				sb.append((hrs == 1) ? "an hour" : (hrs + " hours"));
			}
		} else if (hrs > 0) {
			sb.append((hrs == 1) ? "an hour" : (hrs + " hours"));
			if (min > 1) {
				sb.append(" and " + min + " minutes");
			}
		} else if (min > 0) {
			sb.append((min == 1) ? "a minute" : (min + " minutes"));
			if (sec > 1) {
				sb.append(" and " + sec + " seconds");
			}
		} else {
			sb.append((sec == 1) ? "about a second"
					: ("about " + sec + " seconds"));
		}

		sb.append(" ago");

		return sb.toString();
	}

	/**
	 * 
	 * @param date
	 * @param duration
	 *            Example: Date date = subtractOrAddDayDuration(new Date(), -2);
	 *            // Result: the last 2 days
	 * 
	 *            Date date = subtractOrAddDayDuration(new Date(), 2); //
	 *            Result: the next 2 days
	 * @return
	 */
	public static Date subtractOrAddDayDuration(Date date, int duration) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, duration);
		Date dateExpect = cal.getTime();
		return dateExpect;
	}

	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	public static String formatDate(Date date, TimeZone timezone) {
		if (date == null) {
			return "";
		}

		if (timezone != null) {
			simpleDateFormat.setTimeZone(timezone);
		}

		return simpleDateFormat.format(date);
	}

	public static String formatDateTime(Date date, TimeZone timezone) {
		if (date == null) {
			return "";
		}

		if (timezone != null) {
			simpleDateTimeFormat.setTimeZone(timezone);
		}
		return simpleDateTimeFormat.format(date);
	}

	public static Date convertTimeFromSystemTimezoneToUTC(long timeInMillis) {
		DateTime dt = new DateTime();
		dt = dt.withMillis(-DateTimeZone.getDefault().getOffset(timeInMillis)
				+ timeInMillis);
		dt = dt.withZone(utcZone);
		Date date = dt.toDate();
		return date;
	}

	/**
	 * Convert from UTC time to default time zone of system
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static Date convertTimeFromUTCToSystemTimezone(long timeInMillis) {
		DateTime dt = new DateTime();
		dt = dt.withMillis(DateTimeZone.getDefault().getOffset(timeInMillis)
				+ timeInMillis);
		dt = dt.withZone(utcZone);
		Date date = dt.toDate();
		return date;
	}

}
