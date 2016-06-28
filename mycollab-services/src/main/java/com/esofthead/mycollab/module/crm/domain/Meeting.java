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
/*Domain class of table m_crm_meeting*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_meeting")
class Meeting extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.id
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.subject
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=1000, message="Field value is too long")
    @Column("subject")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.status
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.type
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.typeid
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.startDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("startDate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.endDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("endDate")
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdUser
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.sAccountId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.location
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("location")
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isRecurrence
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("isRecurrence")
    private Boolean isrecurrence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("recurrenceType")
    private String recurrencetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceStartDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("recurrenceStartDate")
    private Date recurrencestartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceEndDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("recurrenceEndDate")
    private Date recurrenceenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("contactType")
    private String contacttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactTypeId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("contactTypeId")
    private Integer contacttypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isClosed
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("isClosed")
    private Boolean isclosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isNotified
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("isNotified")
    private Boolean isnotified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isNotifiedPrior
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    @Column("isNotifiedPrior")
    private Integer isnotifiedprior;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Meeting item = (Meeting)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1777, 1343).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.id
     *
     * @return the value of m_crm_meeting.id
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.id
     *
     * @param id the value for m_crm_meeting.id
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.subject
     *
     * @return the value of m_crm_meeting.subject
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.subject
     *
     * @param subject the value for m_crm_meeting.subject
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.status
     *
     * @return the value of m_crm_meeting.status
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.status
     *
     * @param status the value for m_crm_meeting.status
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.type
     *
     * @return the value of m_crm_meeting.type
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.type
     *
     * @param type the value for m_crm_meeting.type
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.typeid
     *
     * @return the value of m_crm_meeting.typeid
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.typeid
     *
     * @param typeid the value for m_crm_meeting.typeid
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.startDate
     *
     * @return the value of m_crm_meeting.startDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.startDate
     *
     * @param startdate the value for m_crm_meeting.startDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.endDate
     *
     * @return the value of m_crm_meeting.endDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.endDate
     *
     * @param enddate the value for m_crm_meeting.endDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.lastUpdatedTime
     *
     * @return the value of m_crm_meeting.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_meeting.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.createdTime
     *
     * @return the value of m_crm_meeting.createdTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.createdTime
     *
     * @param createdtime the value for m_crm_meeting.createdTime
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.createdUser
     *
     * @return the value of m_crm_meeting.createdUser
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.createdUser
     *
     * @param createduser the value for m_crm_meeting.createdUser
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.sAccountId
     *
     * @return the value of m_crm_meeting.sAccountId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.sAccountId
     *
     * @param saccountid the value for m_crm_meeting.sAccountId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.location
     *
     * @return the value of m_crm_meeting.location
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.location
     *
     * @param location the value for m_crm_meeting.location
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.isRecurrence
     *
     * @return the value of m_crm_meeting.isRecurrence
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Boolean getIsrecurrence() {
        return isrecurrence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.isRecurrence
     *
     * @param isrecurrence the value for m_crm_meeting.isRecurrence
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setIsrecurrence(Boolean isrecurrence) {
        this.isrecurrence = isrecurrence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.recurrenceType
     *
     * @return the value of m_crm_meeting.recurrenceType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getRecurrencetype() {
        return recurrencetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.recurrenceType
     *
     * @param recurrencetype the value for m_crm_meeting.recurrenceType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setRecurrencetype(String recurrencetype) {
        this.recurrencetype = recurrencetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.recurrenceStartDate
     *
     * @return the value of m_crm_meeting.recurrenceStartDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getRecurrencestartdate() {
        return recurrencestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.recurrenceStartDate
     *
     * @param recurrencestartdate the value for m_crm_meeting.recurrenceStartDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setRecurrencestartdate(Date recurrencestartdate) {
        this.recurrencestartdate = recurrencestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.recurrenceEndDate
     *
     * @return the value of m_crm_meeting.recurrenceEndDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Date getRecurrenceenddate() {
        return recurrenceenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.recurrenceEndDate
     *
     * @param recurrenceenddate the value for m_crm_meeting.recurrenceEndDate
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setRecurrenceenddate(Date recurrenceenddate) {
        this.recurrenceenddate = recurrenceenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.contactType
     *
     * @return the value of m_crm_meeting.contactType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public String getContacttype() {
        return contacttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.contactType
     *
     * @param contacttype the value for m_crm_meeting.contactType
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setContacttype(String contacttype) {
        this.contacttype = contacttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.contactTypeId
     *
     * @return the value of m_crm_meeting.contactTypeId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Integer getContacttypeid() {
        return contacttypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.contactTypeId
     *
     * @param contacttypeid the value for m_crm_meeting.contactTypeId
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setContacttypeid(Integer contacttypeid) {
        this.contacttypeid = contacttypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.isClosed
     *
     * @return the value of m_crm_meeting.isClosed
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Boolean getIsclosed() {
        return isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.isClosed
     *
     * @param isclosed the value for m_crm_meeting.isClosed
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.isNotified
     *
     * @return the value of m_crm_meeting.isNotified
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Boolean getIsnotified() {
        return isnotified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.isNotified
     *
     * @param isnotified the value for m_crm_meeting.isNotified
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setIsnotified(Boolean isnotified) {
        this.isnotified = isnotified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.isNotifiedPrior
     *
     * @return the value of m_crm_meeting.isNotifiedPrior
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public Integer getIsnotifiedprior() {
        return isnotifiedprior;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.isNotifiedPrior
     *
     * @param isnotifiedprior the value for m_crm_meeting.isNotifiedPrior
     *
     * @mbggenerated Tue Jun 28 10:34:30 ICT 2016
     */
    public void setIsnotifiedprior(Integer isnotifiedprior) {
        this.isnotifiedprior = isnotifiedprior;
    }
}