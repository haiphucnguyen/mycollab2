/*Domain class of table m_tracker_component*/
package com.esofthead.mycollab.module.tracker.domain;

import java.util.Date;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Component extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.projectid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.componentname
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String componentname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.userlead
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String userlead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.description
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.id
     *
     * @return the value of m_tracker_component.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.id
     *
     * @param id the value for m_tracker_component.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.projectid
     *
     * @return the value of m_tracker_component.projectid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.projectid
     *
     * @param projectid the value for m_tracker_component.projectid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.componentname
     *
     * @return the value of m_tracker_component.componentname
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getComponentname() {
        return componentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.componentname
     *
     * @param componentname the value for m_tracker_component.componentname
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.userlead
     *
     * @return the value of m_tracker_component.userlead
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getUserlead() {
        return userlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.userlead
     *
     * @param userlead the value for m_tracker_component.userlead
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setUserlead(String userlead) {
        this.userlead = userlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.createdUser
     *
     * @return the value of m_tracker_component.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.createdUser
     *
     * @param createduser the value for m_tracker_component.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.sAccountId
     *
     * @return the value of m_tracker_component.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.sAccountId
     *
     * @param saccountid the value for m_tracker_component.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.lastUpdatedTime
     *
     * @return the value of m_tracker_component.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_tracker_component.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.createdTime
     *
     * @return the value of m_tracker_component.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.createdTime
     *
     * @param createdtime the value for m_tracker_component.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.description
     *
     * @return the value of m_tracker_component.description
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.description
     *
     * @param description the value for m_tracker_component.description
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}