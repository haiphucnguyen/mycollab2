package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Problem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.id
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.issuename
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String issuename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.description
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.projectid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.raisedbyuser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.assigntouser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.impact
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String impact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.priority
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.status
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.dateraised
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.datedue
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.actualstartdate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date actualstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.actualenddate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date actualenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.level
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Double level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.resolution
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.state
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.problemsource
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String problemsource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.createdTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.type
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.typeid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.sAccountId
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.id
     *
     * @return the value of m_prj_problem.id
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.id
     *
     * @param id the value for m_prj_problem.id
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.issuename
     *
     * @return the value of m_prj_problem.issuename
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getIssuename() {
        return issuename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.issuename
     *
     * @param issuename the value for m_prj_problem.issuename
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setIssuename(String issuename) {
        this.issuename = issuename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.description
     *
     * @return the value of m_prj_problem.description
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.description
     *
     * @param description the value for m_prj_problem.description
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.projectid
     *
     * @return the value of m_prj_problem.projectid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.projectid
     *
     * @param projectid the value for m_prj_problem.projectid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.raisedbyuser
     *
     * @return the value of m_prj_problem.raisedbyuser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getRaisedbyuser() {
        return raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.raisedbyuser
     *
     * @param raisedbyuser the value for m_prj_problem.raisedbyuser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setRaisedbyuser(String raisedbyuser) {
        this.raisedbyuser = raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.assigntouser
     *
     * @return the value of m_prj_problem.assigntouser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getAssigntouser() {
        return assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.assigntouser
     *
     * @param assigntouser the value for m_prj_problem.assigntouser
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setAssigntouser(String assigntouser) {
        this.assigntouser = assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.impact
     *
     * @return the value of m_prj_problem.impact
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getImpact() {
        return impact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.impact
     *
     * @param impact the value for m_prj_problem.impact
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setImpact(String impact) {
        this.impact = impact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.priority
     *
     * @return the value of m_prj_problem.priority
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.priority
     *
     * @param priority the value for m_prj_problem.priority
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.status
     *
     * @return the value of m_prj_problem.status
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.status
     *
     * @param status the value for m_prj_problem.status
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.dateraised
     *
     * @return the value of m_prj_problem.dateraised
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getDateraised() {
        return dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.dateraised
     *
     * @param dateraised the value for m_prj_problem.dateraised
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setDateraised(Date dateraised) {
        this.dateraised = dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.datedue
     *
     * @return the value of m_prj_problem.datedue
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getDatedue() {
        return datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.datedue
     *
     * @param datedue the value for m_prj_problem.datedue
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.actualstartdate
     *
     * @return the value of m_prj_problem.actualstartdate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getActualstartdate() {
        return actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.actualstartdate
     *
     * @param actualstartdate the value for m_prj_problem.actualstartdate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setActualstartdate(Date actualstartdate) {
        this.actualstartdate = actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.actualenddate
     *
     * @return the value of m_prj_problem.actualenddate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getActualenddate() {
        return actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.actualenddate
     *
     * @param actualenddate the value for m_prj_problem.actualenddate
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setActualenddate(Date actualenddate) {
        this.actualenddate = actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.level
     *
     * @return the value of m_prj_problem.level
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Double getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.level
     *
     * @param level the value for m_prj_problem.level
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setLevel(Double level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.resolution
     *
     * @return the value of m_prj_problem.resolution
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.resolution
     *
     * @param resolution the value for m_prj_problem.resolution
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.state
     *
     * @return the value of m_prj_problem.state
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.state
     *
     * @param state the value for m_prj_problem.state
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.problemsource
     *
     * @return the value of m_prj_problem.problemsource
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getProblemsource() {
        return problemsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.problemsource
     *
     * @param problemsource the value for m_prj_problem.problemsource
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setProblemsource(String problemsource) {
        this.problemsource = problemsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.createdTime
     *
     * @return the value of m_prj_problem.createdTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.createdTime
     *
     * @param createdtime the value for m_prj_problem.createdTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.lastUpdatedTime
     *
     * @return the value of m_prj_problem.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_problem.lastUpdatedTime
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.type
     *
     * @return the value of m_prj_problem.type
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.type
     *
     * @param type the value for m_prj_problem.type
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.typeid
     *
     * @return the value of m_prj_problem.typeid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.typeid
     *
     * @param typeid the value for m_prj_problem.typeid
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.sAccountId
     *
     * @return the value of m_prj_problem.sAccountId
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.sAccountId
     *
     * @param saccountid the value for m_prj_problem.sAccountId
     *
     * @mbggenerated Sat Feb 16 22:50:41 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}