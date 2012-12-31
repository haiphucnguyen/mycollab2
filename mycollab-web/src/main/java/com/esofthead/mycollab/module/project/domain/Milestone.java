package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Milestone extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.flag
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.iscompleted
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Boolean iscompleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.id
     *
     * @return the value of m_prj_milestone.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.id
     *
     * @param id the value for m_prj_milestone.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.name
     *
     * @return the value of m_prj_milestone.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.name
     *
     * @param name the value for m_prj_milestone.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.description
     *
     * @return the value of m_prj_milestone.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.description
     *
     * @param description the value for m_prj_milestone.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.startdate
     *
     * @return the value of m_prj_milestone.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.startdate
     *
     * @param startdate the value for m_prj_milestone.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.enddate
     *
     * @return the value of m_prj_milestone.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.enddate
     *
     * @param enddate the value for m_prj_milestone.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.owner
     *
     * @return the value of m_prj_milestone.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.owner
     *
     * @param owner the value for m_prj_milestone.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.flag
     *
     * @return the value of m_prj_milestone.flag
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.flag
     *
     * @param flag the value for m_prj_milestone.flag
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.projectid
     *
     * @return the value of m_prj_milestone.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.projectid
     *
     * @param projectid the value for m_prj_milestone.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.iscompleted
     *
     * @return the value of m_prj_milestone.iscompleted
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Boolean getIscompleted() {
        return iscompleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.iscompleted
     *
     * @param iscompleted the value for m_prj_milestone.iscompleted
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createdTime
     *
     * @return the value of m_prj_milestone.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createdTime
     *
     * @param createdtime the value for m_prj_milestone.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @return the value of m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.sAccountId
     *
     * @return the value of m_prj_milestone.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.sAccountId
     *
     * @param saccountid the value for m_prj_milestone.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}