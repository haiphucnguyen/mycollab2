package com.esofthead.mycollab.module.project.domain;

import java.util.Date;

public class ChangeLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.id
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.projectid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.posteddate
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.posteduser
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.source
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.sourceid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private Integer sourceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.logAction
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private String logaction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_change_log.sourceDesc
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    private String sourcedesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.id
     *
     * @return the value of m_prj_change_log.id
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.id
     *
     * @param id the value for m_prj_change_log.id
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.projectid
     *
     * @return the value of m_prj_change_log.projectid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.projectid
     *
     * @param projectid the value for m_prj_change_log.projectid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.posteddate
     *
     * @return the value of m_prj_change_log.posteddate
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public Date getPosteddate() {
        return posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.posteddate
     *
     * @param posteddate the value for m_prj_change_log.posteddate
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.posteduser
     *
     * @return the value of m_prj_change_log.posteduser
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public String getPosteduser() {
        return posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.posteduser
     *
     * @param posteduser the value for m_prj_change_log.posteduser
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.source
     *
     * @return the value of m_prj_change_log.source
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.source
     *
     * @param source the value for m_prj_change_log.source
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.sourceid
     *
     * @return the value of m_prj_change_log.sourceid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public Integer getSourceid() {
        return sourceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.sourceid
     *
     * @param sourceid the value for m_prj_change_log.sourceid
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.logAction
     *
     * @return the value of m_prj_change_log.logAction
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public String getLogaction() {
        return logaction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.logAction
     *
     * @param logaction the value for m_prj_change_log.logAction
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setLogaction(String logaction) {
        this.logaction = logaction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_change_log.sourceDesc
     *
     * @return the value of m_prj_change_log.sourceDesc
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public String getSourcedesc() {
        return sourcedesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_change_log.sourceDesc
     *
     * @param sourcedesc the value for m_prj_change_log.sourceDesc
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    public void setSourcedesc(String sourcedesc) {
        this.sourcedesc = sourcedesc;
    }
}