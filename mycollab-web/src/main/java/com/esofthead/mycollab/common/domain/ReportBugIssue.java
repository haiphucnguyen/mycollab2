package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class ReportBugIssue extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.id
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.sAccountId
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.username
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.ipaddress
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private String ipaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.country_code
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private String countryCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.id
     *
     * @return the value of s_report_bug_issue.id
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
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
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}