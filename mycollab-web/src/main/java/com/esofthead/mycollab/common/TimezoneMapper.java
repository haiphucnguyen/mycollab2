package com.esofthead.mycollab.common;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimezoneMapper {
	private static Map<String, TimezoneExt> timeMap;

	static {
		timeMap = new HashMap<String, TimezoneMapper.TimezoneExt>();
		timeMap.put("1", new TimezoneExt("(GMT+00:00) Abidjan",
				"Africa/Abidjan"));
		timeMap.put("2", new TimezoneExt("(GMT+00:00) Accra",
				"Africa/Accra"));
	}

	public static TimezoneExt getTimezone(String id) {
		return timeMap.get(id);
	}

	public static class TimezoneExt {
		private String displayName;
		private TimeZone timezone;

		public TimezoneExt(String displayName, String javaTimeZoneId) {
			this.displayName = displayName;
			this.timezone = TimeZone.getTimeZone(javaTimeZoneId);
		}

		public String getDisplayName() {
			return displayName;
		}

		public TimeZone getTimezone() {
			return timezone;
		}
	}

	public static void main(String[] args) {
		String[] availableIDs = TimeZone.getAvailableIDs();
		for (String timezoneId : availableIDs) {
			TimeZone timeZone = TimeZone.getTimeZone(timezoneId);
			System.out.println("Timezone: " + timeZone.getID() + "---"
					+ timeZone.getDisplayName() + "   "
					+ timeZone.getRawOffset() / (1000 * 60 * 60));
		}
	}
}
