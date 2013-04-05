package com.esofthead.mycollab.common;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimezoneMapper {
	private static Map<String, TimezoneExt> timeMap;

	static {
		timeMap = new HashMap<String, TimezoneMapper.TimezoneExt>();
		timeMap.put("1", new TimezoneExt("(GMT+00:00) Abidjan", "Africa",
				"Africa/Abidjan"));
		timeMap.put("2", new TimezoneExt("(GMT+00:00) Accra", "Africa",
				"Africa/Accra"));
		
	}

	public static TimezoneExt getTimezone(String id) {
		return timeMap.get(id);
	}

	public static class TimezoneExt {
		private String displayName;
		private TimeZone timezone;
		private String area;

		public TimezoneExt(String displayName, String area,
				String javaTimeZoneId) {
			this.displayName = displayName;
			this.area = area;
			this.timezone = TimeZone.getTimeZone(javaTimeZoneId);
		}

		public String getDisplayName() {
			return displayName;
		}

		public TimeZone getTimezone() {
			return timezone;
		}

		public String getArea() {
			return area;
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

		TimeZone t = TimeZone.getTimeZone("NZ-CHAT");
		System.out.println("aa: " + t);

	}
}
