package com.esofthead.mycollab.module.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.esofthead.mycollab.core.MyCollabException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public class ProjectLinkParams {
	private static Pattern PATTERN = Pattern.compile("^\\w{1,3}-\\d*$");

	public static boolean isValidParam(String param) {
		Matcher matcher = PATTERN.matcher(param);
		return matcher.find();
	}

	public static String getProjectShortName(String param) {
		int index = param.indexOf("-");
		if (index > 0) {
			return param.substring(0, index);
		} else {
			throw new MyCollabException("Invalid param " + param);
		}
	}

	public static int getItemKey(String param) {
		int index = param.indexOf("-");
		if (index > 0) {
			return Integer.parseInt(param.substring(index + 1));
		} else {
			throw new MyCollabException("Invalid param " + param);
		}
	}

	public static void main(String[] args) {
		Pattern compile = Pattern.compile("^\\w{1,3}-\\d*$");
		Matcher matcher = compile.matcher("AB-11");
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
