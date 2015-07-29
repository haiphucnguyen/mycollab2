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

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_report_bug_issue")
class ReportBugIssue extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.id
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.sAccountId
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.username
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.ipaddress
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=40, message="Field value is too long")
    @Column("ipaddress")
    private String ipaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_report_bug_issue.country_code
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=5, message="Field value is too long")
    @Column("country_code")
    private String countryCode;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ReportBugIssue item = (ReportBugIssue)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(185, 373).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_report_bug_issue.id
     *
     * @return the value of s_report_bug_issue.id
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
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
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}