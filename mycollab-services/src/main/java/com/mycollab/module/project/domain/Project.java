/*Domain class of table m_prj_project*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_project")
public class Project extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.id
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.name
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createUser
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createUser")
    private String createuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.accountId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("accountId")
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.priority
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.shortname
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("shortname")
    private String shortname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planStartDate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("planStartDate")
    private Date planstartdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planEndDate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("planEndDate")
    private Date planenddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.targetBudget
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("targetBudget")
    private Double targetbudget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.homePage
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("homePage")
    private String homepage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualBudget
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("actualBudget")
    private Double actualbudget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectType
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("projectType")
    private String projecttype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectStatus
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("projectStatus")
    private String projectstatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultBillingRate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("defaultBillingRate")
    private Double defaultbillingrate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("defaultOvertimeBillingRate")
    private Double defaultovertimebillingrate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.currencyid
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=4, message="Field value is too long")
    @Column("currencyid")
    private String currencyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.progress
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("progress")
    private Double progress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.sAccountId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createdTime
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.lastUpdatedTime
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.avatarId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.contextAsk
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("contextAsk")
    private Boolean contextask;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.deadline
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("deadline")
    private Date deadline;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.ispublic
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("ispublic")
    private Boolean ispublic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.istemplate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Column("istemplate")
    private Boolean istemplate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.lead
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("lead")
    private String lead;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.color
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=6, message="Field value is too long")
    @Column("color")
    private String color;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.description
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Project item = (Project)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1829, 239).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.id
     *
     * @return the value of m_prj_project.id
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.createUser
     *
     * @return the value of m_prj_project.createUser
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.createUser
     *
     * @param createuser the value for m_prj_project.createUser
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.accountId
     *
     * @return the value of m_prj_project.accountId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.accountId
     *
     * @param accountid the value for m_prj_project.accountId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.priority
     *
     * @return the value of m_prj_project.priority
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.defaultBillingRate
     *
     * @return the value of m_prj_project.defaultBillingRate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setDefaultbillingrate(Double defaultbillingrate) {
        this.defaultbillingrate = defaultbillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @return the value of m_prj_project.defaultOvertimeBillingRate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public String getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.currencyid
     *
     * @param currencyid the value for m_prj_project.currencyid
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.progress
     *
     * @return the value of m_prj_project.progress
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.avatarId
     *
     * @return the value of m_prj_project.avatarId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.avatarId
     *
     * @param avatarid the value for m_prj_project.avatarId
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.contextAsk
     *
     * @return the value of m_prj_project.contextAsk
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public Boolean getContextask() {
        return contextask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.contextAsk
     *
     * @param contextask the value for m_prj_project.contextAsk
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setContextask(Boolean contextask) {
        this.contextask = contextask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.deadline
     *
     * @return the value of m_prj_project.deadline
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.deadline
     *
     * @param deadline the value for m_prj_project.deadline
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.ispublic
     *
     * @return the value of m_prj_project.ispublic
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public Boolean getIspublic() {
        return ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.ispublic
     *
     * @param ispublic the value for m_prj_project.ispublic
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.istemplate
     *
     * @return the value of m_prj_project.istemplate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public Boolean getIstemplate() {
        return istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.istemplate
     *
     * @param istemplate the value for m_prj_project.istemplate
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setIstemplate(Boolean istemplate) {
        this.istemplate = istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.lead
     *
     * @return the value of m_prj_project.lead
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public String getLead() {
        return lead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.lead
     *
     * @param lead the value for m_prj_project.lead
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setLead(String lead) {
        this.lead = lead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.color
     *
     * @return the value of m_prj_project.color
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.color
     *
     * @param color the value for m_prj_project.color
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.description
     *
     * @return the value of m_prj_project.description
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        createuser,
        accountid,
        priority,
        shortname,
        planstartdate,
        planenddate,
        targetbudget,
        homepage,
        actualbudget,
        projecttype,
        projectstatus,
        defaultbillingrate,
        defaultovertimebillingrate,
        currencyid,
        progress,
        saccountid,
        createdtime,
        lastupdatedtime,
        avatarid,
        contextask,
        deadline,
        ispublic,
        istemplate,
        lead,
        color,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}