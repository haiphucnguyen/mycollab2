/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table s_report_bug_issue*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

class ReportBugIssue extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.sAccountId
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.username
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.ipaddress
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=40, message="Field value is too long")
    private String ipaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.country_code
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=5, message="Field value is too long")
    private String countryCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.id
     *
     * @return the value of s_report_bug_issue.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
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
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}