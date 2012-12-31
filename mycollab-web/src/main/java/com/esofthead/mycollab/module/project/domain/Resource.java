package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Resource extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.resourcename
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String resourcename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.bookingtype
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String bookingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.notes
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String notes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.username
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.allocation
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer allocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.workingtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double workingtimerate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.overtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double overtimerate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_resource.roleid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer roleid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.id
     *
     * @return the value of m_prj_resource.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.id
     *
     * @param id the value for m_prj_resource.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.resourcename
     *
     * @return the value of m_prj_resource.resourcename
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getResourcename() {
        return resourcename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.resourcename
     *
     * @param resourcename the value for m_prj_resource.resourcename
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.bookingtype
     *
     * @return the value of m_prj_resource.bookingtype
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getBookingtype() {
        return bookingtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.bookingtype
     *
     * @param bookingtype the value for m_prj_resource.bookingtype
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setBookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.notes
     *
     * @return the value of m_prj_resource.notes
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.notes
     *
     * @param notes the value for m_prj_resource.notes
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.username
     *
     * @return the value of m_prj_resource.username
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.username
     *
     * @param username the value for m_prj_resource.username
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.projectid
     *
     * @return the value of m_prj_resource.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.projectid
     *
     * @param projectid the value for m_prj_resource.projectid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.allocation
     *
     * @return the value of m_prj_resource.allocation
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getAllocation() {
        return allocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.allocation
     *
     * @param allocation the value for m_prj_resource.allocation
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setAllocation(Integer allocation) {
        this.allocation = allocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.startdate
     *
     * @return the value of m_prj_resource.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.startdate
     *
     * @param startdate the value for m_prj_resource.startdate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.enddate
     *
     * @return the value of m_prj_resource.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.enddate
     *
     * @param enddate the value for m_prj_resource.enddate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.workingtimerate
     *
     * @return the value of m_prj_resource.workingtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getWorkingtimerate() {
        return workingtimerate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.workingtimerate
     *
     * @param workingtimerate the value for m_prj_resource.workingtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setWorkingtimerate(Double workingtimerate) {
        this.workingtimerate = workingtimerate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.overtimerate
     *
     * @return the value of m_prj_resource.overtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getOvertimerate() {
        return overtimerate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.overtimerate
     *
     * @param overtimerate the value for m_prj_resource.overtimerate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setOvertimerate(Double overtimerate) {
        this.overtimerate = overtimerate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_resource.roleid
     *
     * @return the value of m_prj_resource.roleid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_resource.roleid
     *
     * @param roleid the value for m_prj_resource.roleid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}