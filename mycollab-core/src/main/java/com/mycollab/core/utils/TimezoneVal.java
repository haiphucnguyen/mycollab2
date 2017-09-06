package com.mycollab.core.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class TimezoneVal implements Comparable<TimezoneVal> {
    private static Map<String, List<TimezoneVal>> cacheTimezones = new ConcurrentHashMap<>();

    static {
        String[] zoneIds = TimeZone.getAvailableIDs();
        for (int i = 0; i < zoneIds.length; i++) {
            TimeZone timeZone = TimeZone.getTimeZone(zoneIds[i]);
            String timeZoneId = timeZone.getID();
            try {
                DateTimeZone.forTimeZone(timeZone); //check compatible between joda timezone and java timezone
                TimezoneVal timezoneVal = new TimezoneVal(zoneIds[i]);
                List<TimezoneVal> timeZones = cacheTimezones.get(timezoneVal.getArea());
                if (timeZones == null) {
                    timeZones = new ArrayList<>();
                    cacheTimezones.put(timezoneVal.getArea(), timeZones);
                }
                timeZones.add(timezoneVal);
            } catch (Exception e) {
                // ignore exception
            }
        }

        Set<String> keys = cacheTimezones.keySet();
        for (String key : keys) {
            List<TimezoneVal> timezones = cacheTimezones.get(key);
            Collections.sort(timezones);
        }
    }

    private String id;
    private TimeZone timezone;
    private String area;
    private String location;

    public TimezoneVal(String id) {
        this.id = id;
        this.timezone = (id != null) ? TimeZone.getTimeZone(id) : TimeZone.getDefault();
        String timeZoneId = timezone.getID();
        int index = timeZoneId.indexOf('/');
        location = (index > -1) ? timeZoneId.substring(index + 1, timeZoneId.length()) : timeZoneId;
        area = (index > -1) ? timeZoneId.substring(0, index) : "Others";
    }

    public String getDisplayName() {
        return getOffsetString(timezone) + " " + location;
    }

    public DateTimeZone getTimezone() {
        return DateTimeZone.forTimeZone(timezone);
    }

    @Override
    public int compareTo(TimezoneVal val) {
        int offsetInMillis1 = this.getTimezone().getOffset(new DateTime().getMillis());
        int offsetInMillis2 = val.getTimezone().getOffset(new DateTime().getMillis());
        return offsetInMillis1 - offsetInMillis2;
    }

    public String getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getLocation() {
        return location;
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

    public static String getDisplayName(Locale locale, String timeZoneId) {
        TimeZone timeZone = valueOf(timeZoneId);
        return getOffsetString(timeZone) + " " + timeZone.getDisplayName(locale);
    }

    public static String[] getAreas() {
        List<String> keys = new ArrayList<>(cacheTimezones.keySet());
        Collections.sort(keys);
        return keys.toArray(new String[keys.size()]);
    }

    public static Collection<TimezoneVal> getTimezoneInAreas(String area) {
        return cacheTimezones.get(area);
    }
}
