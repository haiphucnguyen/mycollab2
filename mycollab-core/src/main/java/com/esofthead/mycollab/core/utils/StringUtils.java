package com.esofthead.mycollab.core.utils;

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

	public static boolean isNotNullOrEmpty(String str) {
		return (str != null) && (!str.trim().equals(""));
	}

	public static String preStringFormat(String value) {
		if (value == null || "".equals(value)) {
			return "&nbsp;";
		} else {
			return value;
		}
	}

	public static boolean isAsciiString(String text) {
		return text.matches("\\A\\p{ASCII}*\\z");
	}

	public static String formatExtraLink(String value) {
		if (value == null || "".equals(value)) {
			return "&nbsp;";
		}
		return value
				.replaceAll(
						"(?:https?|ftps?)://[\\w/%.-][/\\??\\w=?\\w?/%.-]?[/\\?&\\w=?\\w?/%.-]*",
						"<a href=\"$0\">$0</a>");
	}

	public static void main(String[] args) {
		System.out.println(isAsciiString("123"));
	}
}
