/*Domain class of table m_crm_meeting*/
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
@Table("m_crm_meeting")
@Alias("Meeting")
class Meeting extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.id
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.subject
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=1000, message="Field value is too long")
    @Column("subject")
    private String subject;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.status
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.type
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.typeid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("typeid")
    private Integer typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.startDate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("startDate")
    private Date startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.endDate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("endDate")
    private Date enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.lastUpdatedTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.sAccountId
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.location
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=100, message="Field value is too long")
    @Column("location")
    private String location;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isRecurrence
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("isRecurrence")
    private Boolean isrecurrence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceType
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("recurrenceType")
    private String recurrencetype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceStartDate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("recurrenceStartDate")
    private Date recurrencestartdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceEndDate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("recurrenceEndDate")
    private Date recurrenceenddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactType
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("contactType")
    private String contacttype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactTypeId
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("contactTypeId")
    private Integer contacttypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isClosed
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("isClosed")
    private Boolean isclosed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isNotified
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("isNotified")
    private Boolean isnotified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isNotifiedPrior
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
        return new HashCodeBuilder(1113, 937).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.id
     *
     * @return the value of m_crm_meeting.id
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
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
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setIsnotifiedprior(Integer isnotifiedprior) {
        this.isnotifiedprior = isnotifiedprior;
    }
}