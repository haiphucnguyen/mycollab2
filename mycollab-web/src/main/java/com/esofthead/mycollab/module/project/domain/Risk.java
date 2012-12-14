package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Risk extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.id
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.riskname
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String riskname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.description
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.projectid
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.consequence
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String consequence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.probalitity
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String probalitity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.status
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.dateraised
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.datedue
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.response
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String response;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.resolution
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.level
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private Integer level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.source
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    private String source;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.id
     *
     * @return the value of m_prj_risk.id
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.id
     *
     * @param id the value for m_prj_risk.id
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.riskname
     *
     * @return the value of m_prj_risk.riskname
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getRiskname() {
        return riskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.riskname
     *
     * @param riskname the value for m_prj_risk.riskname
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setRiskname(String riskname) {
        this.riskname = riskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.description
     *
     * @return the value of m_prj_risk.description
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.description
     *
     * @param description the value for m_prj_risk.description
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.projectid
     *
     * @return the value of m_prj_risk.projectid
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.projectid
     *
     * @param projectid the value for m_prj_risk.projectid
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.raisedbyuser
     *
     * @return the value of m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getRaisedbyuser() {
        return raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.raisedbyuser
     *
     * @param raisedbyuser the value for m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setRaisedbyuser(String raisedbyuser) {
        this.raisedbyuser = raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.assigntouser
     *
     * @return the value of m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getAssigntouser() {
        return assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.assigntouser
     *
     * @param assigntouser the value for m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setAssigntouser(String assigntouser) {
        this.assigntouser = assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.consequence
     *
     * @return the value of m_prj_risk.consequence
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getConsequence() {
        return consequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.consequence
     *
     * @param consequence the value for m_prj_risk.consequence
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.probalitity
     *
     * @return the value of m_prj_risk.probalitity
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getProbalitity() {
        return probalitity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.probalitity
     *
     * @param probalitity the value for m_prj_risk.probalitity
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setProbalitity(String probalitity) {
        this.probalitity = probalitity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.status
     *
     * @return the value of m_prj_risk.status
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.status
     *
     * @param status the value for m_prj_risk.status
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.dateraised
     *
     * @return the value of m_prj_risk.dateraised
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Date getDateraised() {
        return dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.dateraised
     *
     * @param dateraised the value for m_prj_risk.dateraised
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setDateraised(Date dateraised) {
        this.dateraised = dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.datedue
     *
     * @return the value of m_prj_risk.datedue
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Date getDatedue() {
        return datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.datedue
     *
     * @param datedue the value for m_prj_risk.datedue
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.response
     *
     * @return the value of m_prj_risk.response
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getResponse() {
        return response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.response
     *
     * @param response the value for m_prj_risk.response
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.resolution
     *
     * @return the value of m_prj_risk.resolution
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.resolution
     *
     * @param resolution the value for m_prj_risk.resolution
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.level
     *
     * @return the value of m_prj_risk.level
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.level
     *
     * @param level the value for m_prj_risk.level
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.source
     *
     * @return the value of m_prj_risk.source
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.source
     *
     * @param source the value for m_prj_risk.source
     *
     * @mbggenerated Fri Dec 14 14:31:21 GMT+07:00 2012
     */
    public void setSource(String source) {
        this.source = source;
    }
}