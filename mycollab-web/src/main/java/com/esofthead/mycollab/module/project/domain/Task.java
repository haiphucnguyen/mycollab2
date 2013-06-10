/*Domain class of table m_prj_task*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Task extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.id
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.taskname
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String taskname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.percentagecomplete
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Double percentagecomplete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.startdate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.enddate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.priority
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.duration
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Double duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.isestimated
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Boolean isestimated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.projectid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.deadline
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date deadline;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.taskindex
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer taskindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.actualStartDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date actualstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.actualEndDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date actualenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.tasklistid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer tasklistid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.lastUpdatedTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.assignUser
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.sAccountId
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.status
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.logBy
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String logby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.taskkey
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Integer taskkey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.originalEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Double originalestimate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.remainEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    private Double remainestimate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.notes
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String notes;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.id
     *
     * @return the value of m_prj_task.id
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.id
     *
     * @param id the value for m_prj_task.id
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.taskname
     *
     * @return the value of m_prj_task.taskname
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getTaskname() {
        return taskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.taskname
     *
     * @param taskname the value for m_prj_task.taskname
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.percentagecomplete
     *
     * @return the value of m_prj_task.percentagecomplete
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Double getPercentagecomplete() {
        return percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.percentagecomplete
     *
     * @param percentagecomplete the value for m_prj_task.percentagecomplete
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setPercentagecomplete(Double percentagecomplete) {
        this.percentagecomplete = percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.startdate
     *
     * @return the value of m_prj_task.startdate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.startdate
     *
     * @param startdate the value for m_prj_task.startdate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.enddate
     *
     * @return the value of m_prj_task.enddate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.enddate
     *
     * @param enddate the value for m_prj_task.enddate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.priority
     *
     * @return the value of m_prj_task.priority
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.priority
     *
     * @param priority the value for m_prj_task.priority
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.duration
     *
     * @return the value of m_prj_task.duration
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Double getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.duration
     *
     * @param duration the value for m_prj_task.duration
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setDuration(Double duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.isestimated
     *
     * @return the value of m_prj_task.isestimated
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Boolean getIsestimated() {
        return isestimated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.isestimated
     *
     * @param isestimated the value for m_prj_task.isestimated
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setIsestimated(Boolean isestimated) {
        this.isestimated = isestimated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.projectid
     *
     * @return the value of m_prj_task.projectid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.projectid
     *
     * @param projectid the value for m_prj_task.projectid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.deadline
     *
     * @return the value of m_prj_task.deadline
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.deadline
     *
     * @param deadline the value for m_prj_task.deadline
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.taskindex
     *
     * @return the value of m_prj_task.taskindex
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getTaskindex() {
        return taskindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.taskindex
     *
     * @param taskindex the value for m_prj_task.taskindex
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setTaskindex(Integer taskindex) {
        this.taskindex = taskindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.actualStartDate
     *
     * @return the value of m_prj_task.actualStartDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getActualstartdate() {
        return actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.actualStartDate
     *
     * @param actualstartdate the value for m_prj_task.actualStartDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setActualstartdate(Date actualstartdate) {
        this.actualstartdate = actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.actualEndDate
     *
     * @return the value of m_prj_task.actualEndDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getActualenddate() {
        return actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.actualEndDate
     *
     * @param actualenddate the value for m_prj_task.actualEndDate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setActualenddate(Date actualenddate) {
        this.actualenddate = actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.tasklistid
     *
     * @return the value of m_prj_task.tasklistid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getTasklistid() {
        return tasklistid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.tasklistid
     *
     * @param tasklistid the value for m_prj_task.tasklistid
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setTasklistid(Integer tasklistid) {
        this.tasklistid = tasklistid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.createdTime
     *
     * @return the value of m_prj_task.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.createdTime
     *
     * @param createdtime the value for m_prj_task.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.lastUpdatedTime
     *
     * @return the value of m_prj_task.lastUpdatedTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_task.lastUpdatedTime
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.assignUser
     *
     * @return the value of m_prj_task.assignUser
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.assignUser
     *
     * @param assignuser the value for m_prj_task.assignUser
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.sAccountId
     *
     * @return the value of m_prj_task.sAccountId
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.sAccountId
     *
     * @param saccountid the value for m_prj_task.sAccountId
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.status
     *
     * @return the value of m_prj_task.status
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.status
     *
     * @param status the value for m_prj_task.status
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.logBy
     *
     * @return the value of m_prj_task.logBy
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getLogby() {
        return logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.logBy
     *
     * @param logby the value for m_prj_task.logBy
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setLogby(String logby) {
        this.logby = logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.taskkey
     *
     * @return the value of m_prj_task.taskkey
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Integer getTaskkey() {
        return taskkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.taskkey
     *
     * @param taskkey the value for m_prj_task.taskkey
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setTaskkey(Integer taskkey) {
        this.taskkey = taskkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.originalEstimate
     *
     * @return the value of m_prj_task.originalEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Double getOriginalestimate() {
        return originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.originalEstimate
     *
     * @param originalestimate the value for m_prj_task.originalEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setOriginalestimate(Double originalestimate) {
        this.originalestimate = originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.remainEstimate
     *
     * @return the value of m_prj_task.remainEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public Double getRemainestimate() {
        return remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.remainEstimate
     *
     * @param remainestimate the value for m_prj_task.remainEstimate
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setRemainestimate(Double remainestimate) {
        this.remainestimate = remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.notes
     *
     * @return the value of m_prj_task.notes
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.notes
     *
     * @param notes the value for m_prj_task.notes
     *
     * @mbggenerated Mon Jun 10 15:53:34 GMT+07:00 2013
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}