package com.esofthead.mycollab.common.domain;

public class ReportBugIssueWithBLOBs extends ReportBugIssue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.userAgent
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    private String useragent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.errorTrace
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    private String errortrace;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.userAgent
     *
     * @return the value of s_report_bug_issue.userAgent
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.userAgent
     *
     * @param useragent the value for s_report_bug_issue.userAgent
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.errorTrace
     *
     * @return the value of s_report_bug_issue.errorTrace
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    public String getErrortrace() {
        return errortrace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.errorTrace
     *
     * @param errortrace the value for s_report_bug_issue.errorTrace
     *
     * @mbggenerated Tue Feb 05 10:30:39 GMT+07:00 2013
     */
    public void setErrortrace(String errortrace) {
        this.errortrace = errortrace;
    }
}