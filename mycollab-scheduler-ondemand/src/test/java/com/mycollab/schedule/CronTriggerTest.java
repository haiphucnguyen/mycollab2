package com.mycollab.schedule;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

public class CronTriggerTest {

    @Test
    public void testTriggerEveryBeginningDay() throws ParseException {
        CronExpression expr = new CronExpression("0 0 0 * * ?");
        GregorianCalendar calendar = new GregorianCalendar(2014, 7, 3, 9, 30);
        Date validDate = expr.getNextValidTimeAfter(calendar.getTime());
        Assert.assertEquals(new GregorianCalendar(2014, 7, 4, 0, 0, 0).getTime(), validDate);
    }

    @Test
    public void testTriggerEveryMinutes() throws ParseException {
        CronExpression expr = new CronExpression("0 * * * * ?");
        GregorianCalendar calendar = new GregorianCalendar(2014, 7, 3, 9, 30);
        Date validDate = expr.getNextValidTimeAfter(calendar.getTime());
        Assert.assertEquals(new GregorianCalendar(2014, 7, 3, 9, 31, 0).getTime(), validDate);
    }
}
