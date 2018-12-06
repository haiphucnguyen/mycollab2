/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class to process date instance
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class DateTimeUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DateTimeUtils.class);

    private static ZoneId utcZone = ZoneId.of("UTC");

    public static LocalDateTime getCurrentDateWithoutMS() {
        return LocalDate.now().atStartOfDay();
    }

    public static Date convertDateByString(String strDate, String format) {
        if (!StringUtils.isBlank(strDate)) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                return formatter.parse(strDate);
            } catch (ParseException e) {
                LOG.error("Error while parse date", e);
            }
        }
        return new Date();
    }

    public static String convertToStringWithUserTimeZone(String dateVal, String dateFormat, Locale locale, ZoneId userTimeZone) {
        LocalDateTime date = parseDateByW3C(dateVal);
        return convertToStringWithUserTimeZone(date, dateFormat, locale, userTimeZone);
    }

    /**
     * @param strDate
     * @return
     */
    public static LocalDateTime parseDateByW3C(String strDate) {
        if (strDate != null && !strDate.equals("")) {
            try {
                return LocalDateTime.parse(strDate);
            } catch (DateTimeParseException e) {
                LOG.error("Error while parse date", e);
            }
        }
        return null;
    }

    public static String formatDateToW3C(Date date) {
        if (date != null) {
            String formatW3C = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat formatter = new SimpleDateFormat(formatW3C);
            return formatter.format(date);
        }
        return "";
    }

    public static String convertToStringWithUserTimeZone(LocalDateTime date, String dateFormat, Locale locale, ZoneId userTimeZone) {
        if (date == null)
            return "";
        return formatDate(date, dateFormat, locale, userTimeZone);
    }

    public static String getPrettyDateValue(LocalDateTime dateTime, Locale locale) {
        if (dateTime == null) {
            return "";
        }
//        PrettyTime p = new PrettyTime(locale);
//        return p.format(dateTime);
        return "Implemented";
    }

    public static String getPrettyDurationValue(LocalDateTime date, Locale locale) {
//        Period period = Period.between(date, LocalDate.now());
        // TODO
//        PeriodFormatter formatter = PeriodFormat.wordBased(locale);
//        return formatter.print(period);
        return "Implemented";
    }


    public static String formatDate(LocalDateTime date, String dateFormat, Locale locale) {
        return formatDate(date, dateFormat, locale, null);
    }

    public static String formatDate(LocalDateTime date, String dateFormat, Locale locale, ZoneId timezone) {
        if (date == null) {
            return "";
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat).withLocale(locale);
        if (timezone != null) {
            formatter = formatter.withZone(timezone);
        }

        return formatter.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static LocalDateTime convertDateTimeToUTC(LocalDateTime date) {
        return convertDateTimeByTimezone(date, utcZone);
    }

    public static LocalDateTime convertDateTimeByTimezone(LocalDateTime date, ZoneId timeZone) {
        return date.atZone(timeZone).toLocalDateTime();
    }

    /**
     * Convert from UTC time to default time zone of system
     *
     * @param timeInMillis
     * @return
     */
    public static LocalDateTime convertTimeFromUTCToSystemTimezone(long timeInMillis) {
        LocalDateTime dt = LocalDateTime.now();
        ZoneId systemTimeZone = ZoneId.systemDefault();

        // TODO
//        dt = dt.withMillis(DateTimeZone.getDefault().getOffset(timeInMillis) + timeInMillis);
//        dt = dt.withZone(utcZone);
//        return dt.toDate();
        return LocalDateTime.now();
    }

    /**
     * @param date
     * @return array of two date elements, first is the first day of week, and
     * the second is the end week date
     */
    public static LocalDate[] getBounceDatesOfWeek(LocalDate date) {
        LocalDate begin = date.with(WeekFields.ISO.dayOfWeek(), 1);
        LocalDate end = date.with(WeekFields.ISO.dayOfWeek(), 7);
        return new LocalDate[]{begin, end};
    }

    public static LocalDate min(LocalDate... values) {
        LocalDate minVal = values[0];
        for (int i = 1; i < values.length; i++) {
            if (minVal.isAfter(values[i])) {
                minVal = values[i];
            }
        }
        return minVal;
    }

    public static LocalDate max(LocalDate... values) {
        LocalDate max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (max.isBefore(values[i])) {
                max = values[i];
            }
        }
        return max;
    }

    public static String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zid);
    }
}
