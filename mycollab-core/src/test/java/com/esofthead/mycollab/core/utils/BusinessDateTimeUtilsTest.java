/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.utils;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class BusinessDateTimeUtilsTest {
    @Test
    public void testPlusDaysWithPositiveValues() {
        // Friday
        LocalDate date = new LocalDate(2015, 8, 28);

        // Monday
        LocalDate result = BusinessDayTimeUtils.plusDays(date, 1);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 8, 31)));

        //Next Friday
        result = BusinessDayTimeUtils.plusDays(date, 5);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 9, 4)));

        // Tuesday
        date = new LocalDate(2015, 8, 25);
        result = BusinessDayTimeUtils.plusDays(date, 8);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 9, 4)));

        //the same day
        result = BusinessDayTimeUtils.plusDays(date, 0);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 8, 25)));

        date = new LocalDate(2015, 8, 28);
        result = BusinessDayTimeUtils.plusDays(date, 23);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 9, 30)));
    }

    @Test
    public void testPlusDaysWithNegativeValues() {
        // Friday
        LocalDate date = new LocalDate(2015, 8, 28);

        LocalDate result = BusinessDayTimeUtils.plusDays(date, -1);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 8, 27)));

        result = BusinessDayTimeUtils.plusDays(date, -5);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 8, 21)));

        //Monday
        date = new LocalDate(2015, 8, 24);
        result = BusinessDayTimeUtils.plusDays(date, -1);
        Assert.assertEquals(0, result.compareTo(new LocalDate(2015, 8, 21)));
    }

    @Test
    public void testDuration() {
        // Friday
        LocalDate startDate = new LocalDate(2015, 8, 28);
        LocalDate endDate = new LocalDate(2015, 8, 28);
        Assert.assertEquals(0, BusinessDayTimeUtils.duration(startDate, endDate));

        startDate = new LocalDate(2015, 8, 28);
        endDate = new LocalDate(2015, 8, 31);
        Assert.assertEquals(1, BusinessDayTimeUtils.duration(startDate, endDate));

        startDate = new LocalDate(2015, 8, 28);
        endDate = new LocalDate(2015, 9, 30);
        Assert.assertEquals(23, BusinessDayTimeUtils.duration(startDate, endDate));

        startDate = new LocalDate(2015, 8, 24);
        endDate = new LocalDate(2015, 8, 31);
        Assert.assertEquals(5, BusinessDayTimeUtils.duration(startDate, endDate));

        startDate = new LocalDate(2015, 6, 8);
        endDate = new LocalDate(2015, 6, 26);
        Assert.assertEquals(14, BusinessDayTimeUtils.duration(startDate, endDate));

        startDate = new LocalDate(2015, 8, 17);
        endDate = new LocalDate(2016, 12, 26);
        Assert.assertEquals(355, BusinessDayTimeUtils.duration(startDate, endDate));
    }
}
