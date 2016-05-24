package com.esofthead.mycollab.core.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class TimezoneVal {
    private static Map<String, Collection<TimezoneVal>> cacheTimezones = new ConcurrentHashMap<>();

    static {
        String[] zoneIds = TimeZone.getAvailableIDs();
        for (int i = 0; i < zoneIds.length; i++) {
            TimeZone timeZone = TimeZone.getTimeZone(zoneIds[i]);
            String timeZoneId = timeZone.getID();
            int index = timeZoneId.indexOf('/');
            String area = (index > -1) ? timeZoneId.substring(0, index) : "Others";
            Collection<TimezoneVal> timeZones = cacheTimezones.get(area);
            if (timeZones == null) {
                timeZones = new ArrayList<>();
                cacheTimezones.put(area, timeZones);
            }
            TimezoneVal timezoneVal = new TimezoneVal(zoneIds[i], timeZone);
            timeZones.add(timezoneVal);
        }
    }

    private String id;
    private TimeZone timezone;

    public TimezoneVal(String id, TimeZone timeZone) {
        this.id = id;
        this.timezone = timeZone;
    }

    public String getDisplayName(Locale locale) {
        return getOffsetString(timezone) + " " + timezone.getDisplayName(locale);
    }

    public DateTimeZone getTimezone() {
        return DateTimeZone.forTimeZone(timezone);
    }

    public String getId() {
        return id;
    }


    private static String getOffsetString(TimeZone timeZone) {
        int offsetInMillis = DateTimeZone.forTimeZone(timeZone).getOffset(new DateTime().getMillis());
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

    public static String[] getAreas() {
        Set<String> keys = cacheTimezones.keySet();
        return keys.toArray(new String[keys.size()]);
    }

    public static synchronized Collection<TimezoneVal> getTimezoneInAreas(String area) {
        return cacheTimezones.get(area);
    }
}
