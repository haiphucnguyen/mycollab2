/*Domain class of table m_tracker_version*/
package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Version extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.id
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.projectid
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.duedate
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Date duedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.versionname
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String versionname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.createdUser
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_version.description
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.id
     *
     * @return the value of m_tracker_version.id
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.id
     *
     * @param id the value for m_tracker_version.id
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.projectid
     *
     * @return the value of m_tracker_version.projectid
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.projectid
     *
     * @param projectid the value for m_tracker_version.projectid
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.duedate
     *
     * @return the value of m_tracker_version.duedate
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.duedate
     *
     * @param duedate the value for m_tracker_version.duedate
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.versionname
     *
     * @return the value of m_tracker_version.versionname
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public String getVersionname() {
        return versionname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.versionname
     *
     * @param versionname the value for m_tracker_version.versionname
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.createdUser
     *
     * @return the value of m_tracker_version.createdUser
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.createdUser
     *
     * @param createduser the value for m_tracker_version.createdUser
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.sAccountId
     *
     * @return the value of m_tracker_version.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.sAccountId
     *
     * @param saccountid the value for m_tracker_version.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.lastUpdatedTime
     *
     * @return the value of m_tracker_version.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_tracker_version.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.createdTime
     *
     * @return the value of m_tracker_version.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.createdTime
     *
     * @param createdtime the value for m_tracker_version.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_version.description
     *
     * @return the value of m_tracker_version.description
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_version.description
     *
     * @param description the value for m_tracker_version.description
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}