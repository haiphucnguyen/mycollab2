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

	public static String formatExtraLink(String value) {
		return value.replaceAll(
				"(?:https?|ftps?)://[\\w/%.-][/\\??\\w=?\\w?/%.-]?[/\\?&\\w=?\\w?/%.-]*",
				"<a href=\"$0\">$0</a>");
	}

	public static void main(String[] args) {
		System.out
				.println(formatExtraLink("Go here http://www.vnexpress.net<span style=\"color: rgb(68, 68, 68); font-family: Consolas, Menlo, Monaco, 'Lucida Console', 'Liberation Mono', 'DejaVu Sans Mono', 'Bitstream Vera Sans Mono', 'Courier New', monospace, serif; font-size: 13px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 17px; orphans: auto; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(238, 238, 238); display: inline !important; float: none;\"></span>"));
	}
}
