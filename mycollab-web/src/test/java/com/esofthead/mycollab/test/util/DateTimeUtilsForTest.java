/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.test.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author haiphucnguyen
 */
public class DateTimeUtilsForTest {
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
    
    /**
     *
     * @param date
     * @param duration Example: Date date = subtractOrAddDayDuration(new Date(),
     * -2); // Result: the last 2 days
     *
     * Date date = subtractOrAddDayDuration(new Date(), 2); // Result: the next
     * 2 days
     * @return
     */
    public static Date subtractOrAddDayDuration(Date date, int duration) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, duration);
        Date dateExpect = cal.getTime();
        return dateExpect;
    }
}
