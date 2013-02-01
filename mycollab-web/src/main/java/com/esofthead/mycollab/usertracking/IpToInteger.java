package com.esofthead.mycollab.usertracking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpToInteger {
	private static final String IP_PATTERN = "[0-9]+.[0-9]+.[0-9]+.[0-9]+";

	public static final long toDecimalNumber(String ipaddress) throws Exception {
		Pattern pattern = Pattern.compile(IP_PATTERN);
		Matcher matcher = pattern.matcher(ipaddress);
		if (matcher.find()) {
			String match = ipaddress.substring(matcher.start(), matcher.end());
			if (!match.equals(ipaddress))
				throw new Exception("Invalid ip address format");
		}

		String[] subIp = ipaddress.split("\\.");
		int w = Integer.parseInt(subIp[0]);
		int x = Integer.parseInt(subIp[1]);
		int y = Integer.parseInt(subIp[2]);
		int z = Integer.parseInt(subIp[3]);

		if (w < 0 || w > 255 
				|| x < 0 || x > 255 
				|| y < 0 || y > 255
				|| z < 0 || z > 255)
			throw new Exception("Invalid ip address format");

		return (16777216 * w) + (65536 * x) + (256 * y) + z;
	}
}
