package com.esofthead.mycollab.common.domain;

@SuppressWarnings("ucd")
public class ReportBugIssueWithBLOBs extends ReportBugIssue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.userAgent
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("userAgent")
    private String useragent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.errorTrace
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("errorTrace")
    private String errortrace;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.userAgent
     *
     * @return the value of s_report_bug_issue.userAgent
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
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
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
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
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
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
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    public void setErrortrace(String errortrace) {
        this.errortrace = errortrace;
    }

    public static enum Field {
        id,
        saccountid,
        username,
        ipaddress,
        countryCode,
        useragent,
        errortrace;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}