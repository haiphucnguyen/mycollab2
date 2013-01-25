package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Query extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.queryname
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private String queryname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.sharetype
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Integer sharetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.createddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Date createddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.updateddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Date updateddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.owner
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.querytext
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.description
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.id
     *
     * @return the value of m_tracker_query.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.id
     *
     * @param id the value for m_tracker_query.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.queryname
     *
     * @return the value of m_tracker_query.queryname
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public String getQueryname() {
        return queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.queryname
     *
     * @param queryname the value for m_tracker_query.queryname
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.sharetype
     *
     * @return the value of m_tracker_query.sharetype
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Integer getSharetype() {
        return sharetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.sharetype
     *
     * @param sharetype the value for m_tracker_query.sharetype
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setSharetype(Integer sharetype) {
        this.sharetype = sharetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.createddate
     *
     * @return the value of m_tracker_query.createddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Date getCreateddate() {
        return createddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.createddate
     *
     * @param createddate the value for m_tracker_query.createddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.updateddate
     *
     * @return the value of m_tracker_query.updateddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Date getUpdateddate() {
        return updateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.updateddate
     *
     * @param updateddate the value for m_tracker_query.updateddate
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.owner
     *
     * @return the value of m_tracker_query.owner
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.owner
     *
     * @param owner the value for m_tracker_query.owner
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.querytext
     *
     * @return the value of m_tracker_query.querytext
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public String getQuerytext() {
        return querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.querytext
     *
     * @param querytext the value for m_tracker_query.querytext
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setQuerytext(String querytext) {
        this.querytext = querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.description
     *
     * @return the value of m_tracker_query.description
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.description
     *
     * @param description the value for m_tracker_query.description
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.projectid
     *
     * @return the value of m_tracker_query.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.projectid
     *
     * @param projectid the value for m_tracker_query.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }
}