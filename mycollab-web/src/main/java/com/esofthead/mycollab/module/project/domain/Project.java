package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Project extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.account
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.priority
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.shortname
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String shortname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date planstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date planenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.targetBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double targetbudget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.homePage
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String homepage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double actualbudget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectType
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String projecttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectStatus
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String projectstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double defaultbillingrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date actualstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date actualenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double defaultovertimebillingrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.currencyid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.progress
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Double progress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.id
     *
     * @return the value of m_prj_project.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.id
     *
     * @param id the value for m_prj_project.id
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.name
     *
     * @return the value of m_prj_project.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.name
     *
     * @param name the value for m_prj_project.name
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.owner
     *
     * @return the value of m_prj_project.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.owner
     *
     * @param owner the value for m_prj_project.owner
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.account
     *
     * @return the value of m_prj_project.account
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.account
     *
     * @param account the value for m_prj_project.account
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setAccount(Integer account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.priority
     *
     * @return the value of m_prj_project.priority
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.priority
     *
     * @param priority the value for m_prj_project.priority
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.shortname
     *
     * @return the value of m_prj_project.shortname
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.shortname
     *
     * @param shortname the value for m_prj_project.shortname
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.planStartDate
     *
     * @return the value of m_prj_project.planStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getPlanstartdate() {
        return planstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.planStartDate
     *
     * @param planstartdate the value for m_prj_project.planStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setPlanstartdate(Date planstartdate) {
        this.planstartdate = planstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.planEndDate
     *
     * @return the value of m_prj_project.planEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getPlanenddate() {
        return planenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.planEndDate
     *
     * @param planenddate the value for m_prj_project.planEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setPlanenddate(Date planenddate) {
        this.planenddate = planenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.targetBudget
     *
     * @return the value of m_prj_project.targetBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getTargetbudget() {
        return targetbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.targetBudget
     *
     * @param targetbudget the value for m_prj_project.targetBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setTargetbudget(Double targetbudget) {
        this.targetbudget = targetbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.homePage
     *
     * @return the value of m_prj_project.homePage
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.homePage
     *
     * @param homepage the value for m_prj_project.homePage
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.actualBudget
     *
     * @return the value of m_prj_project.actualBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getActualbudget() {
        return actualbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.actualBudget
     *
     * @param actualbudget the value for m_prj_project.actualBudget
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setActualbudget(Double actualbudget) {
        this.actualbudget = actualbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.projectType
     *
     * @return the value of m_prj_project.projectType
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getProjecttype() {
        return projecttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.projectType
     *
     * @param projecttype the value for m_prj_project.projectType
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.projectStatus
     *
     * @return the value of m_prj_project.projectStatus
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getProjectstatus() {
        return projectstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.projectStatus
     *
     * @param projectstatus the value for m_prj_project.projectStatus
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.description
     *
     * @return the value of m_prj_project.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.description
     *
     * @param description the value for m_prj_project.description
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.defaultBillingRate
     *
     * @return the value of m_prj_project.defaultBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getDefaultbillingrate() {
        return defaultbillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.defaultBillingRate
     *
     * @param defaultbillingrate the value for m_prj_project.defaultBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setDefaultbillingrate(Double defaultbillingrate) {
        this.defaultbillingrate = defaultbillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.actualStartDate
     *
     * @return the value of m_prj_project.actualStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getActualstartdate() {
        return actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.actualStartDate
     *
     * @param actualstartdate the value for m_prj_project.actualStartDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setActualstartdate(Date actualstartdate) {
        this.actualstartdate = actualstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.actualEndDate
     *
     * @return the value of m_prj_project.actualEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getActualenddate() {
        return actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.actualEndDate
     *
     * @param actualenddate the value for m_prj_project.actualEndDate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setActualenddate(Date actualenddate) {
        this.actualenddate = actualenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @return the value of m_prj_project.defaultOvertimeBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getDefaultovertimebillingrate() {
        return defaultovertimebillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @param defaultovertimebillingrate the value for m_prj_project.defaultOvertimeBillingRate
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setDefaultovertimebillingrate(Double defaultovertimebillingrate) {
        this.defaultovertimebillingrate = defaultovertimebillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.currencyid
     *
     * @return the value of m_prj_project.currencyid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.currencyid
     *
     * @param currencyid the value for m_prj_project.currencyid
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.progress
     *
     * @return the value of m_prj_project.progress
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Double getProgress() {
        return progress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.progress
     *
     * @param progress the value for m_prj_project.progress
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setProgress(Double progress) {
        this.progress = progress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.sAccountId
     *
     * @return the value of m_prj_project.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.sAccountId
     *
     * @param saccountid the value for m_prj_project.sAccountId
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.createdTime
     *
     * @return the value of m_prj_project.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.createdTime
     *
     * @param createdtime the value for m_prj_project.createdTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.lastUpdatedTime
     *
     * @return the value of m_prj_project.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_project.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 31 14:53:05 GMT+07:00 2012
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}