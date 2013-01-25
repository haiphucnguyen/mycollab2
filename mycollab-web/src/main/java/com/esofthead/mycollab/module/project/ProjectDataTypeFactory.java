package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;

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
    
    private static String[] BUG_SEVERITY_LIST = new String[]{"Critical", "Major", "Minor", "Trivial"};
    
    private static String[] BUG_STATUS_LIST = new String[]{BugStatusConstants.CLOSE, BugStatusConstants.INPROGRESS, BugStatusConstants.OPEN, BugStatusConstants.TESTPENDING, BugStatusConstants.WONFIX};
    
    private static String[] BUG_RESOLUTION_LIST = new String[] {BugResolutionConstants.CAN_NOT_REPRODUCE, BugResolutionConstants.DUPLICATE, BugResolutionConstants.FIXED, BugResolutionConstants.INCOMPLETE, BugResolutionConstants.NEWISSUE, BugResolutionConstants.WAITFORVERIFICATION, BugResolutionConstants.WON_FIX};

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

    public static String[] getBugSeverityList() {
        return BUG_SEVERITY_LIST;
    }
    
    public static String[] getBugResolutionList() {
        return BUG_RESOLUTION_LIST;
    }
}
