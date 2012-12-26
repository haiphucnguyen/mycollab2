package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Message extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.title
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.message
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String message;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.messagehtml
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String messagehtml;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteddate
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteduser
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.projectid
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.category
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String category;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.createdTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.id
     *
     * @return the value of m_prj_message.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.id
     *
     * @param id the value for m_prj_message.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.title
     *
     * @return the value of m_prj_message.title
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.title
     *
     * @param title the value for m_prj_message.title
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.message
     *
     * @return the value of m_prj_message.message
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.message
     *
     * @param message the value for m_prj_message.message
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.messagehtml
     *
     * @return the value of m_prj_message.messagehtml
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getMessagehtml() {
        return messagehtml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.messagehtml
     *
     * @param messagehtml the value for m_prj_message.messagehtml
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setMessagehtml(String messagehtml) {
        this.messagehtml = messagehtml;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.posteddate
     *
     * @return the value of m_prj_message.posteddate
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Date getPosteddate() {
        return posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.posteddate
     *
     * @param posteddate the value for m_prj_message.posteddate
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.posteduser
     *
     * @return the value of m_prj_message.posteduser
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getPosteduser() {
        return posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.posteduser
     *
     * @param posteduser the value for m_prj_message.posteduser
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.projectid
     *
     * @return the value of m_prj_message.projectid
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.projectid
     *
     * @param projectid the value for m_prj_message.projectid
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.category
     *
     * @return the value of m_prj_message.category
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.category
     *
     * @param category the value for m_prj_message.category
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.createdTime
     *
     * @return the value of m_prj_message.createdTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.createdTime
     *
     * @param createdtime the value for m_prj_message.createdTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.lastUpdatedTime
     *
     * @return the value of m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.sAccountId
     *
     * @return the value of m_prj_message.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.sAccountId
     *
     * @param saccountid the value for m_prj_message.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}