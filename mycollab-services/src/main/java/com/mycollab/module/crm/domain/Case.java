/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_crm_case*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_case")
@Alias("Case")
class Case extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.priority
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.status
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.type
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.subject
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=255, message="Field value is too long")
    @Column("subject")
    private String subject;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.accountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("accountId")
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.reason
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("reason")
    private String reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.origin
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("origin")
    private String origin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.email
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.phonenumber
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("phonenumber")
    private String phonenumber;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Case item = (Case)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(761, 1201).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.id
     *
     * @return the value of m_crm_case.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.id
     *
     * @param id the value for m_crm_case.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.priority
     *
     * @return the value of m_crm_case.priority
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.priority
     *
     * @param priority the value for m_crm_case.priority
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.status
     *
     * @return the value of m_crm_case.status
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.status
     *
     * @param status the value for m_crm_case.status
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.type
     *
     * @return the value of m_crm_case.type
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.type
     *
     * @param type the value for m_crm_case.type
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.subject
     *
     * @return the value of m_crm_case.subject
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.subject
     *
     * @param subject the value for m_crm_case.subject
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.accountId
     *
     * @return the value of m_crm_case.accountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.accountId
     *
     * @param accountid the value for m_crm_case.accountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.createdTime
     *
     * @return the value of m_crm_case.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.createdTime
     *
     * @param createdtime the value for m_crm_case.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.createdUser
     *
     * @return the value of m_crm_case.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.createdUser
     *
     * @param createduser the value for m_crm_case.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.sAccountId
     *
     * @return the value of m_crm_case.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.sAccountId
     *
     * @param saccountid the value for m_crm_case.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.assignUser
     *
     * @return the value of m_crm_case.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.assignUser
     *
     * @param assignuser the value for m_crm_case.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.lastUpdatedTime
     *
     * @return the value of m_crm_case.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_case.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.reason
     *
     * @return the value of m_crm_case.reason
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.reason
     *
     * @param reason the value for m_crm_case.reason
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.origin
     *
     * @return the value of m_crm_case.origin
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.origin
     *
     * @param origin the value for m_crm_case.origin
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.email
     *
     * @return the value of m_crm_case.email
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.email
     *
     * @param email the value for m_crm_case.email
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.phonenumber
     *
     * @return the value of m_crm_case.phonenumber
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.phonenumber
     *
     * @param phonenumber the value for m_crm_case.phonenumber
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}