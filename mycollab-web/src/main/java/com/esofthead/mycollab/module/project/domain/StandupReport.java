/*Domain class of table m_prj_standup*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

class StandupReport extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.id
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.sAccountId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.projectId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.forday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Date forday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.logBy
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String logby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.createdTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.id
     *
     * @return the value of m_prj_standup.id
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.id
     *
     * @param id the value for m_prj_standup.id
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.sAccountId
     *
     * @return the value of m_prj_standup.sAccountId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.sAccountId
     *
     * @param saccountid the value for m_prj_standup.sAccountId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.projectId
     *
     * @return the value of m_prj_standup.projectId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.projectId
     *
     * @param projectid the value for m_prj_standup.projectId
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.forday
     *
     * @return the value of m_prj_standup.forday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Date getForday() {
        return forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.forday
     *
     * @param forday the value for m_prj_standup.forday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setForday(Date forday) {
        this.forday = forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.logBy
     *
     * @return the value of m_prj_standup.logBy
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public String getLogby() {
        return logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.logBy
     *
     * @param logby the value for m_prj_standup.logBy
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setLogby(String logby) {
        this.logby = logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.createdTime
     *
     * @return the value of m_prj_standup.createdTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.createdTime
     *
     * @param createdtime the value for m_prj_standup.createdTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.lastUpdatedTime
     *
     * @return the value of m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}