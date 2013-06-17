package com.esofthead.mycollab.core.utils;

public class StringUtil {
	public static boolean isNotNullOrEmpty(String str) {
		return (str != null) && (!str.trim().equals(""));
	}

	public static String preStringFormat(String value) {
		if (value == null || "".equals(value)) {
			return " ";
		} else {
			return value;
		}
	}
}
