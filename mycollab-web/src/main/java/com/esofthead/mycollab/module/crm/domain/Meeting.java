/*Domain class of table m_crm_meeting*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Meeting extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.id
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.subject
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=1000, message="Field value is too long")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.status
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.type
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.typeid
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.startDate
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.endDate
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.createdUser
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.location
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isRecurrence
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Byte isrecurrence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceType
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String recurrencetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceStartDate
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date recurrencestartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.recurrenceEndDate
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Date recurrenceenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactType
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String contacttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.contactTypeId
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Integer contacttypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.isClosed
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    private Boolean isclosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting.description
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.id
     *
     * @return the value of m_crm_meeting.id
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    public Byte getIsrecurrence() {
        return isrecurrence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.isRecurrence
     *
     * @param isrecurrence the value for m_crm_meeting.isRecurrence
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    public void setIsrecurrence(Byte isrecurrence) {
        this.isrecurrence = isrecurrence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.recurrenceType
     *
     * @return the value of m_crm_meeting.recurrenceType
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting.description
     *
     * @return the value of m_crm_meeting.description
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting.description
     *
     * @param description the value for m_crm_meeting.description
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}