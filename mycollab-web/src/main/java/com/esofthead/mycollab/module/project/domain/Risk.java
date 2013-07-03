/*Domain class of table m_prj_risk*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Risk extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.id
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.riskname
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=400, message="Field value is too long")
    private String riskname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.projectid
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.raisedbyuser
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.assigntouser
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.consequence
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String consequence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.probalitity
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String probalitity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.status
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.dateraised
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.datedue
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.response
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String response;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.resolution
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=4000, message="Field value is too long")
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.level
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Double level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.source
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.createdTime
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.type
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.typeid
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.description
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.id
     *
     * @return the value of m_prj_risk.id
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setRiskname(String riskname) {
        this.riskname = riskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.projectid
     *
     * @return the value of m_prj_risk.projectid
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.status
     *
     * @param status the value for m_prj_risk.status
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.dateraised
     *
     * @return the value of m_prj_risk.dateraised
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public Double getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.level
     *
     * @param level the value for m_prj_risk.level
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setLevel(Double level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.source
     *
     * @return the value of m_prj_risk.source
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.sAccountId
     *
     * @return the value of m_prj_risk.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.sAccountId
     *
     * @param saccountid the value for m_prj_risk.sAccountId
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.description
     *
     * @return the value of m_prj_risk.description
     *
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
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
     * @mbggenerated Wed Jul 03 17:12:33 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}