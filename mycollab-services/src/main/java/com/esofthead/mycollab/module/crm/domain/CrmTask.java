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
/*Domain class of table m_crm_task*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_task")
public class CrmTask extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.id
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.subject
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=1000, message="Field value is too long")
    @Column("subject")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.startdate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("startdate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.duedate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("duedate")
    private Date duedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.contactId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.typeid
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.createdTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.createdUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.sAccountId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.status
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.assignUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.priority
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.type
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.lastUpdatedTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.isClosed
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Column("isClosed")
    private Boolean isclosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.description
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        CrmTask item = (CrmTask)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(45, 109).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.id
     *
     * @return the value of m_crm_task.id
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.id
     *
     * @param id the value for m_crm_task.id
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.subject
     *
     * @return the value of m_crm_task.subject
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.subject
     *
     * @param subject the value for m_crm_task.subject
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.startdate
     *
     * @return the value of m_crm_task.startdate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.startdate
     *
     * @param startdate the value for m_crm_task.startdate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.duedate
     *
     * @return the value of m_crm_task.duedate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.duedate
     *
     * @param duedate the value for m_crm_task.duedate
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.contactId
     *
     * @return the value of m_crm_task.contactId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.contactId
     *
     * @param contactid the value for m_crm_task.contactId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.typeid
     *
     * @return the value of m_crm_task.typeid
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.typeid
     *
     * @param typeid the value for m_crm_task.typeid
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.createdTime
     *
     * @return the value of m_crm_task.createdTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.createdTime
     *
     * @param createdtime the value for m_crm_task.createdTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.createdUser
     *
     * @return the value of m_crm_task.createdUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.createdUser
     *
     * @param createduser the value for m_crm_task.createdUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.sAccountId
     *
     * @return the value of m_crm_task.sAccountId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.sAccountId
     *
     * @param saccountid the value for m_crm_task.sAccountId
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.status
     *
     * @return the value of m_crm_task.status
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.status
     *
     * @param status the value for m_crm_task.status
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.assignUser
     *
     * @return the value of m_crm_task.assignUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.assignUser
     *
     * @param assignuser the value for m_crm_task.assignUser
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.priority
     *
     * @return the value of m_crm_task.priority
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.priority
     *
     * @param priority the value for m_crm_task.priority
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.type
     *
     * @return the value of m_crm_task.type
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.type
     *
     * @param type the value for m_crm_task.type
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.lastUpdatedTime
     *
     * @return the value of m_crm_task.lastUpdatedTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_task.lastUpdatedTime
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.isClosed
     *
     * @return the value of m_crm_task.isClosed
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public Boolean getIsclosed() {
        return isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.isClosed
     *
     * @param isclosed the value for m_crm_task.isClosed
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.description
     *
     * @return the value of m_crm_task.description
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_task.description
     *
     * @param description the value for m_crm_task.description
     *
     * @mbggenerated Wed Apr 13 18:59:27 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        subject,
        startdate,
        duedate,
        contactid,
        typeid,
        createdtime,
        createduser,
        saccountid,
        status,
        assignuser,
        priority,
        type,
        lastupdatedtime,
        isclosed,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}