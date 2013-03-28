package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Task extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.subject
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.startdate
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.duedate
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date duedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.contactId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.typeid
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.createdUser
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.sAccountId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.status
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.assignUser
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.priority
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.type
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.lastUpdatedTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.isClosed
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Boolean isclosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_task.description
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_task.id
     *
     * @return the value of m_crm_task.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
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
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}