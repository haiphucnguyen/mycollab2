package com.esofthead.mycollab.utils;

public class StringUtils {
	public static String subString(String input, int length) {
		if (input == null) {
			return "";
		}

		if (input.length() <= length)
			return input;
		else
			return input.substring(0, length);
	}
}
