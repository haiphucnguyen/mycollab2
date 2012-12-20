package com.esofthead.mycollab.module.common;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class DateTimeUtilTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAddDayDuration() {
		Date date = DateTimeUtils.addDayDuration(new Date(), 2);
		Assert.assertEquals(new Date().getDate() + 2, date.getDate());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSubtractDayDuration() {
		Date date = DateTimeUtils.subtractDayDuration(new Date(), 2);
		Assert.assertEquals(new Date().getDate() - 2, date.getDate());
	}
}
