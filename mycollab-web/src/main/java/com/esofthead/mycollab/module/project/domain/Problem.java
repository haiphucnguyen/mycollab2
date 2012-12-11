package com.esofthead.mycollab.module.project.domain;

import java.util.Date;

public class Problem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.id
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.issuename
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String issuename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.description
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.projectid
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.raisedbyuser
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.assigntouser
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.impact
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String impact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.priority
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.status
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.dateraised
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.datedue
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.actualstartdate
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Date actualstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.actualenddate
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Date actualenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.level
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private Integer level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.resolution
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.state
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_problem.problemsource
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    private String problemsource;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.id
     *
     * @return the value of m_prj_problem.id
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.status
     *
     * @param status the value for m_prj_problem.status
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.dateraised
     *
     * @return the value of m_prj_problem.dateraised
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_problem.level
     *
     * @param level the value for m_prj_problem.level
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_problem.resolution
     *
     * @return the value of m_prj_problem.resolution
     *
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
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
     * @mbggenerated Tue Dec 11 10:16:46 GMT+07:00 2012
     */
    public void setProblemsource(String problemsource) {
        this.problemsource = problemsource;
    }
}