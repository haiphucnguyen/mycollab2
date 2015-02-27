/*Domain class of table m_crm_call*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_crm_call")
class Call extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.id
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.subject
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=1000, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("subject")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.startDate
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("startDate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.durationInSeconds
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("durationInSeconds")
    private Integer durationinseconds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.calltype
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("calltype")
    private String calltype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.type
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.typeid
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.createdUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.assignUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.contactId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.status
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.purpose
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("purpose")
    private String purpose;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.isClosed
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("isClosed")
    private Boolean isclosed;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.id
     *
     * @return the value of m_crm_call.id
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.id
     *
     * @param id the value for m_crm_call.id
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.subject
     *
     * @return the value of m_crm_call.subject
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.subject
     *
     * @param subject the value for m_crm_call.subject
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.startDate
     *
     * @return the value of m_crm_call.startDate
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.startDate
     *
     * @param startdate the value for m_crm_call.startDate
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.durationInSeconds
     *
     * @return the value of m_crm_call.durationInSeconds
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Integer getDurationinseconds() {
        return durationinseconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.durationInSeconds
     *
     * @param durationinseconds the value for m_crm_call.durationInSeconds
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setDurationinseconds(Integer durationinseconds) {
        this.durationinseconds = durationinseconds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.calltype
     *
     * @return the value of m_crm_call.calltype
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getCalltype() {
        return calltype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.calltype
     *
     * @param calltype the value for m_crm_call.calltype
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.type
     *
     * @return the value of m_crm_call.type
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.type
     *
     * @param type the value for m_crm_call.type
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.typeid
     *
     * @return the value of m_crm_call.typeid
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.typeid
     *
     * @param typeid the value for m_crm_call.typeid
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.lastUpdatedTime
     *
     * @return the value of m_crm_call.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_call.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.createdTime
     *
     * @return the value of m_crm_call.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.createdTime
     *
     * @param createdtime the value for m_crm_call.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.createdUser
     *
     * @return the value of m_crm_call.createdUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.createdUser
     *
     * @param createduser the value for m_crm_call.createdUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.assignUser
     *
     * @return the value of m_crm_call.assignUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.assignUser
     *
     * @param assignuser the value for m_crm_call.assignUser
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.contactId
     *
     * @return the value of m_crm_call.contactId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.contactId
     *
     * @param contactid the value for m_crm_call.contactId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.sAccountId
     *
     * @return the value of m_crm_call.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.sAccountId
     *
     * @param saccountid the value for m_crm_call.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.status
     *
     * @return the value of m_crm_call.status
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.status
     *
     * @param status the value for m_crm_call.status
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.purpose
     *
     * @return the value of m_crm_call.purpose
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.purpose
     *
     * @param purpose the value for m_crm_call.purpose
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.isClosed
     *
     * @return the value of m_crm_call.isClosed
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public Boolean getIsclosed() {
        return isclosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.isClosed
     *
     * @param isclosed the value for m_crm_call.isClosed
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setIsclosed(Boolean isclosed) {
        this.isclosed = isclosed;
    }
}