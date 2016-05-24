package com.esofthead.mycollab.core.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class TimezoneVal {
    private String id;
    private DateTimeZone timezone;

    public TimezoneVal(String id, String javaTimeZoneId) {
        this.id = id;
        try {
            this.timezone = DateTimeZone.forTimeZone(TimeZone.getTimeZone(javaTimeZoneId));
        } catch (Exception e) {
            timezone = DateTimeZone.UTC;
        }
    }

    public String getDisplayName(Locale locale) {
        return getOffsetString(timezone) + " " + timezone.toTimeZone().getDisplayName(locale);
    }

    public DateTimeZone getTimezone() {
        return timezone;
    }

    public String getId() {
        return id;
    }


    private static String getOffsetString(DateTimeZone timeZone) {
        int offsetInMillis = timeZone.getOffset(new DateTime().getMillis());
        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000),
                Math.abs((offsetInMillis / 60000) % 60));
        offset = "(GMT" + (offsetInMillis >= 0 ? "+" : "-") + offset + ")";
        return offset;
    }

    public static TimeZone valueOf(String timeZoneId) {
        if (StringUtils.isBlank(timeZoneId)) {
            return TimeZone.getDefault();
        }
        return TimeZone.getTimeZone(timeZoneId);
    }

    public static void main(String[] args) {
        
    }
}
