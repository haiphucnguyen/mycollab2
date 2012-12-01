package com.esofthead.mycollab.core.utils;

public class StringUtil {
	public static boolean isNotNullOrEmpty(String str) {
		return (str != null) && (!str.trim().equals(""));
	}
}
