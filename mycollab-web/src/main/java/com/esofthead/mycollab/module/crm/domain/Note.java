/*Domain class of table m_crm_note*/
package com.esofthead.mycollab.module.crm.domain;

import java.util.Date;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Note extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.subject
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.type
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.note
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.id
     *
     * @return the value of m_crm_note.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.id
     *
     * @param id the value for m_crm_note.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.subject
     *
     * @return the value of m_crm_note.subject
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.subject
     *
     * @param subject the value for m_crm_note.subject
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.type
     *
     * @return the value of m_crm_note.type
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.type
     *
     * @param type the value for m_crm_note.type
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.typeid
     *
     * @return the value of m_crm_note.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.typeid
     *
     * @param typeid the value for m_crm_note.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.createdTime
     *
     * @return the value of m_crm_note.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.createdTime
     *
     * @param createdtime the value for m_crm_note.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.createdUser
     *
     * @return the value of m_crm_note.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.createdUser
     *
     * @param createduser the value for m_crm_note.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.sAccountId
     *
     * @return the value of m_crm_note.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.sAccountId
     *
     * @param saccountid the value for m_crm_note.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.lastUpdatedTime
     *
     * @return the value of m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.note
     *
     * @return the value of m_crm_note.note
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.note
     *
     * @param note the value for m_crm_note.note
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setNote(String note) {
        this.note = note;
    }
}