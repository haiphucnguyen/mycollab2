package com.esofthead.mycollab.module.project;

public class ProjectDataTypeFactory {

    public static final String PROJECT_STATUS_OPEN = "Open";
    public static final String PROJECT_STATUS_CLOSED = "Closed";
    public static final String BUG_PRIORITY_BLOCKER = "Blocker";
    public static final String BUG_PRIORITY_CRITICAL = "Critical";
    public static final String BUG_PRIORITY_MAJOR = "Major";
    public static final String BUG_PRIORITY_MINOR = "Minor";
    public static final String BUG_PRIORITY_TRIVIAL = "Trivial";
    private static final String[] PROJECT_STATUSES_LIST = new String[]{"Open", "Closed"};
    private static String[] PROJECT_TYPE_LIST = new String[]{"Unknown",
        "Administrative", "Operative"};
    private static String[] BUG_PRIORITY_LIST = new String[]{"Blocker",
        "Critical", "Major", "Minor", "Trivial"};
    private static String[] BUG_STATUS_LIST = new String[]{"UnConfirmed", "New", "Assigned", "ReOpened", "Ready", "Resolved", "Verify"};

    public static String[] getProjectTypeList() {
        return PROJECT_TYPE_LIST;
    }

    public static String[] getBugPriorityList() {
        return BUG_PRIORITY_LIST;
    }

    public static String[] getBugStatusList() {
        return BUG_STATUS_LIST;
    }

    public static String[] getProjectStatusList() {
        return PROJECT_STATUSES_LIST;
    }
}
