/*Domain class of table m_prj_time_logging*/
package com.esofthead.mycollab.module.project.domain;

import java.util.Date;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class ItemTimeLogging extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.projectId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.type
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logValue
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Double logvalue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.loguser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String loguser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.note
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.id
     *
     * @return the value of m_prj_time_logging.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.id
     *
     * @param id the value for m_prj_time_logging.id
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.projectId
     *
     * @return the value of m_prj_time_logging.projectId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.projectId
     *
     * @param projectid the value for m_prj_time_logging.projectId
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.type
     *
     * @return the value of m_prj_time_logging.type
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.type
     *
     * @param type the value for m_prj_time_logging.type
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.typeid
     *
     * @return the value of m_prj_time_logging.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.typeid
     *
     * @param typeid the value for m_prj_time_logging.typeid
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logValue
     *
     * @return the value of m_prj_time_logging.logValue
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Double getLogvalue() {
        return logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logValue
     *
     * @param logvalue the value for m_prj_time_logging.logValue
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setLogvalue(Double logvalue) {
        this.logvalue = logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.loguser
     *
     * @return the value of m_prj_time_logging.loguser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getLoguser() {
        return loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.loguser
     *
     * @param loguser the value for m_prj_time_logging.loguser
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setLoguser(String loguser) {
        this.loguser = loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.createdTime
     *
     * @return the value of m_prj_time_logging.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.createdTime
     *
     * @param createdtime the value for m_prj_time_logging.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @return the value of m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.note
     *
     * @return the value of m_prj_time_logging.note
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.note
     *
     * @param note the value for m_prj_time_logging.note
     *
     * @mbggenerated Thu Jun 13 13:54:11 GMT+07:00 2013
     */
    public void setNote(String note) {
        this.note = note;
    }
}