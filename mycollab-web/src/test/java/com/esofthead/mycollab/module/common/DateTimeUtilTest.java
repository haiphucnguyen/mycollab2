package com.esofthead.mycollab.module.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class DateTimeUtilTest {
	
	@Test
	public void testAddDayDuration() {
		Date dateTest = DateTimeUtils.getDateByString("2012-12-20");
		Date date = DateTimeUtils.addDayDuration(dateTest, 2);
		Date dateTestResult = DateTimeUtils.getDateByString("2012-12-22");
		Assert.assertEquals(dateTestResult, date);
	}
	
	@Test
	public void testSubtractDayDuration() {
		Date dateTest = DateTimeUtils.getDateByString("2012-12-20");
		Date date = DateTimeUtils.subtractDayDuration(dateTest, 2);
		Date dateTestResult = DateTimeUtils.getDateByString("2012-12-18");
		Assert.assertEquals(dateTestResult, date);
	}
	
	@Test
	public void testMonthAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-10-20 16:54:00");
		strDate = DateTimeUtils.getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 months ago", strDate);
	}
	
	@Test
	public void testYearAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2010-10-20 16:54:00");
		strDate = DateTimeUtils.getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 years ago", strDate);
	}
	
	@Test
	public void testHourAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-24 15:54:00");
		strDate = DateTimeUtils.getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 hours ago", strDate);
	}
	
	@Test
	public void testMinuteAgoString() {
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-24 17:00:00");
		strDate = DateTimeUtils.getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("2 minutes ago", strDate);
	}
	
	@Test
	public void testDayAgoString() {
		
		Date milestoneDate = getDateByStringTest("2012-12-24 17:02:00");
		Date dayTest;
		String strDate = "";
		dayTest = getDateByStringTest("2012-12-20 16:54:00");
		strDate = DateTimeUtils.getStringDateFromMilestone(dayTest, milestoneDate);
		Assert.assertEquals("4 days ago", strDate);
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
