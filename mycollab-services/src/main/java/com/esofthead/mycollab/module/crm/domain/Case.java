/*Domain class of table m_crm_case*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

class Case extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.id
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.priority
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.status
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.type
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.subject
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.accountId
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.createdTime
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.createdUser
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.sAccountId
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.assignUser
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.lastUpdatedTime
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.reason
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String reason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.origin
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String origin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.email
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.phonenumber
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String phonenumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.id
     *
     * @return the value of m_crm_case.id
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}