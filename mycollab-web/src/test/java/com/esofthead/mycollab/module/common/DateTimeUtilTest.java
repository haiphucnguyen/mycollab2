package com.esofthead.mycollab.module.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.esofthead.mycollab.test.util.DateTimeUtilsForTest;

public class DateTimeUtilTest {
	
	@Test
	public void testAddDayDuration() {
		Date dateTest = DateTimeUtilsForTest.getDateByString("2012-12-20");
		Date date = DateTimeUtilsForTest.subtractOrAddDayDuration(dateTest, 2);
		Date dateTestResult = DateTimeUtilsForTest.getDateByString("2012-12-22");
		Assert.assertEquals(dateTestResult, date);
	}
	
	@Test
	public void testSubtractDayDuration() {
		Date dateTest = DateTimeUtilsForTest.getDateByString("2012-12-20");
		Date date = DateTimeUtilsForTest.subtractOrAddDayDuration(dateTest, -30);
		Date dateTestResult = DateTimeUtilsForTest.getDateByString("2012-11-20");
		Assert.assertEquals(dateTestResult, date);
	}
	
	@Test
	public void testMonthAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-10-20 16:54:00");
		strDate = getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 months and 5 days ago", strDate);
	}
	
	@Test
	public void testYearAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2010-10-20 16:54:00");
		strDate = getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 years and 2 months ago", strDate);
	}
	
	@Test
	public void testHourAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-24 15:54:00");
		strDate = getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("an hour and 8 minutes ago", strDate);
	}
	
	@Test
	public void testMinuteAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-24 17:00:00");
		strDate = getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 minutes ago", strDate);
	}
	
	@Test
	public void testDayAgoString() {
		
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-20 16:54:00");
		strDate = getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("4 days ago", strDate);
	}
	
	public String getStringDateFromMilestone(Date dateTime, Date milestonedate) {
		StringBuffer sb = new StringBuffer();
		Date current = milestonedate;
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
	
	private Date getDateByStringTest(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
}
