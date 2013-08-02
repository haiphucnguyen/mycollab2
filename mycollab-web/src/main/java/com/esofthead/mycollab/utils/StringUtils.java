package com.esofthead.mycollab.utils;

public class StringUtils {
	public static String subString(String input, int length) {
		return trimString(input, length, false);
	}

	public static String trimString(String input, int length,
			boolean withEllipsis) {
		if (input == null) {
			return "";
		}

		if (input.length() <= length)
			return input;
		else if (withEllipsis)
			return input.substring(0, length) + "...";
		else
			return input.substring(0, length);
	}
}
