package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Risk extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.id
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.riskname
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String riskname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.description
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.projectid
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.raisedbyuser
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.assigntouser
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.consequence
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String consequence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.probalitity
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String probalitity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.status
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Boolean status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.dateraised
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.datedue
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.response
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String response;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.resolution
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.level
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Integer level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.source
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.createdTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.type
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.typeid
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    private Integer typeid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.id
     *
     * @return the value of m_prj_risk.id
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
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
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.createdTime
     *
     * @return the value of m_prj_risk.createdTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.createdTime
     *
     * @param createdtime the value for m_prj_risk.createdTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @return the value of m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.type
     *
     * @return the value of m_prj_risk.type
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.type
     *
     * @param type the value for m_prj_risk.type
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.typeid
     *
     * @return the value of m_prj_risk.typeid
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.typeid
     *
     * @param typeid the value for m_prj_risk.typeid
     *
     * @mbggenerated Sat Dec 15 06:39:52 GMT+07:00 2012
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}