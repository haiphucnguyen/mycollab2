package com.esofthead.mycollab.module.project;

public class ProjectDataTypeFactory {
	private static String[] PROJECT_PRIORITY_LIST = new String[] { "1", "2",
			"3", "4", "5", "6", "7", "8", "9", "10" };

	private static String[] PROJECT_TYPE_LIST = new String[] { "Unknown",
			"Administrative", "Operative" };

	private static String[] BUG_PRIORITY_LIST = new String[] { "Blocker",
			"Critical", "Major", "Minor", "Trivial" };

	public static String[] getProjectPriorityList() {
		return PROJECT_PRIORITY_LIST;
	}

	public static String[] getProjectTypeList() {
		return PROJECT_TYPE_LIST;
	}

	public static String[] getBugPriorityList() {
		return BUG_PRIORITY_LIST;
	}
}
