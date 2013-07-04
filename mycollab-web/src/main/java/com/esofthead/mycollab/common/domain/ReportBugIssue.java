/*Domain class of table s_report_bug_issue*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

class ReportBugIssue extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.id
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.username
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.ipaddress
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=15, message="Field value is too long")
    private String ipaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.country_code
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=5, message="Field value is too long")
    private String countryCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.id
     *
     * @return the value of s_report_bug_issue.id
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.id
     *
     * @param id the value for s_report_bug_issue.id
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.sAccountId
     *
     * @return the value of s_report_bug_issue.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.sAccountId
     *
     * @param saccountid the value for s_report_bug_issue.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.username
     *
     * @return the value of s_report_bug_issue.username
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.username
     *
     * @param username the value for s_report_bug_issue.username
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.ipaddress
     *
     * @return the value of s_report_bug_issue.ipaddress
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.ipaddress
     *
     * @param ipaddress the value for s_report_bug_issue.ipaddress
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.country_code
     *
     * @return the value of s_report_bug_issue.country_code
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_report_bug_issue.country_code
     *
     * @param countryCode the value for s_report_bug_issue.country_code
     *
     * @mbggenerated Wed Jul 03 17:12:32 GMT+07:00 2013
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}