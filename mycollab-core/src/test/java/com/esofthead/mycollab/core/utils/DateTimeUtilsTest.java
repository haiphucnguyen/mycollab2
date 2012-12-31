/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.core.utils;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

/**
 *
 * @author haiphucnguyen
 */
public class DateTimeUtilsTest {
    @Test
    public void testConvertDate() {
        Date d = new GregorianCalendar().getTime();
        DateTime time = new DateTime(d);
        System.out.println("Aaa: " + d + "---" + time);
        Set<String> timezones = DateTimeZone.getAvailableIDs();
        for (String tid : timezones) {
           DateTimeZone tz = DateTimeZone.forID(tid);
           System.out.println("D: " + tz);
        }
    }
}
