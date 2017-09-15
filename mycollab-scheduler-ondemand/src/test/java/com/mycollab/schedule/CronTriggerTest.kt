package com.mycollab.schedule

import org.junit.Assert
import org.junit.Test
import org.quartz.CronExpression

import java.text.ParseException
import java.util.Date
import java.util.GregorianCalendar

class CronTriggerTest {

    @Test
    @Throws(ParseException::class)
    fun testTriggerEveryBeginningDay() {
        val expr = CronExpression("0 0 0 * * ?")
        val calendar = GregorianCalendar(2014, 7, 3, 9, 30)
        val validDate = expr.getNextValidTimeAfter(calendar.time)
        Assert.assertEquals(GregorianCalendar(2014, 7, 4, 0, 0, 0).time, validDate)
    }

    @Test
    @Throws(ParseException::class)
    fun testTriggerEveryMinutes() {
        val expr = CronExpression("0 * * * * ?")
        val calendar = GregorianCalendar(2014, 7, 3, 9, 30)
        val validDate = expr.getNextValidTimeAfter(calendar.time)
        Assert.assertEquals(GregorianCalendar(2014, 7, 3, 9, 31, 0).time, validDate)
    }
}
