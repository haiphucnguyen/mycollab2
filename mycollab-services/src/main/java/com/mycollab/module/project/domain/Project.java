/*Domain class of table m_prj_project*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_project")
@Alias("Project")
public class Project extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.id
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.name
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @NotEmpty
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createUser
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createUser")
    private String createuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.clientId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("clientId")
    private Integer clientid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.priority
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.shortName
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("shortName")
    private String shortname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planStartDate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("planStartDate")
    private LocalDate planstartdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planEndDate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("planEndDate")
    private LocalDate planenddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.targetBudget
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("targetBudget")
    private Double targetbudget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.homePage
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("homePage")
    private String homepage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualBudget
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("actualBudget")
    private Double actualbudget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.type
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.status
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultBillingRate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("defaultBillingRate")
    private Double defaultbillingrate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("defaultOvertimeBillingRate")
    private Double defaultovertimebillingrate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.currencyId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=4, message="Field value is too long")
    @Column("currencyId")
    private String currencyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.progress
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("progress")
    private Double progress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.sAccountId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createdTime
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.lastUpdatedTime
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.avatarId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.contextAsk
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("contextAsk")
    private Boolean contextask;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.deadline
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("deadline")
    private LocalDate deadline;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.isPublic
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("isPublic")
    private Boolean ispublic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.isTemplate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Column("isTemplate")
    private Boolean istemplate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.memLead
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("memLead")
    private String memlead;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.color
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    @Length(max=6, message="Field value is too long")
    @Column("color")
    private String color;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.description
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
        return new HashCodeBuilder(599, 1881).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.id
     *
     * @return the value of m_prj_project.id
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.id
     *
     * @param id the value for m_prj_project.id
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.name
     *
     * @param name the value for m_prj_project.name
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withCreateuser(String createuser) {
        this.setCreateuser(createuser);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.createUser
     *
     * @param createuser the value for m_prj_project.createUser
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.clientId
     *
     * @return the value of m_prj_project.clientId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Integer getClientid() {
        return clientid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withClientid(Integer clientid) {
        this.setClientid(clientid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.clientId
     *
     * @param clientid the value for m_prj_project.clientId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.priority
     *
     * @return the value of m_prj_project.priority
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withPriority(String priority) {
        this.setPriority(priority);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.priority
     *
     * @param priority the value for m_prj_project.priority
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.shortName
     *
     * @return the value of m_prj_project.shortName
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withShortname(String shortname) {
        this.setShortname(shortname);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.shortName
     *
     * @param shortname the value for m_prj_project.shortName
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public LocalDate getPlanstartdate() {
        return planstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withPlanstartdate(LocalDate planstartdate) {
        this.setPlanstartdate(planstartdate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.planStartDate
     *
     * @param planstartdate the value for m_prj_project.planStartDate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setPlanstartdate(LocalDate planstartdate) {
        this.planstartdate = planstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.planEndDate
     *
     * @return the value of m_prj_project.planEndDate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public LocalDate getPlanenddate() {
        return planenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withPlanenddate(LocalDate planenddate) {
        this.setPlanenddate(planenddate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.planEndDate
     *
     * @param planenddate the value for m_prj_project.planEndDate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setPlanenddate(LocalDate planenddate) {
        this.planenddate = planenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.targetBudget
     *
     * @return the value of m_prj_project.targetBudget
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Double getTargetbudget() {
        return targetbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withTargetbudget(Double targetbudget) {
        this.setTargetbudget(targetbudget);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.targetBudget
     *
     * @param targetbudget the value for m_prj_project.targetBudget
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withHomepage(String homepage) {
        this.setHomepage(homepage);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.homePage
     *
     * @param homepage the value for m_prj_project.homePage
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Double getActualbudget() {
        return actualbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withActualbudget(Double actualbudget) {
        this.setActualbudget(actualbudget);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.actualBudget
     *
     * @param actualbudget the value for m_prj_project.actualBudget
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setActualbudget(Double actualbudget) {
        this.actualbudget = actualbudget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.type
     *
     * @return the value of m_prj_project.type
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.type
     *
     * @param type the value for m_prj_project.type
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.status
     *
     * @return the value of m_prj_project.status
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.status
     *
     * @param status the value for m_prj_project.status
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.defaultBillingRate
     *
     * @return the value of m_prj_project.defaultBillingRate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Double getDefaultbillingrate() {
        return defaultbillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withDefaultbillingrate(Double defaultbillingrate) {
        this.setDefaultbillingrate(defaultbillingrate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.defaultBillingRate
     *
     * @param defaultbillingrate the value for m_prj_project.defaultBillingRate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Double getDefaultovertimebillingrate() {
        return defaultovertimebillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withDefaultovertimebillingrate(Double defaultovertimebillingrate) {
        this.setDefaultovertimebillingrate(defaultovertimebillingrate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @param defaultovertimebillingrate the value for m_prj_project.defaultOvertimeBillingRate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setDefaultovertimebillingrate(Double defaultovertimebillingrate) {
        this.defaultovertimebillingrate = defaultovertimebillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.currencyId
     *
     * @return the value of m_prj_project.currencyId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withCurrencyid(String currencyid) {
        this.setCurrencyid(currencyid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.currencyId
     *
     * @param currencyid the value for m_prj_project.currencyId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Double getProgress() {
        return progress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withProgress(Double progress) {
        this.setProgress(progress);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.progress
     *
     * @param progress the value for m_prj_project.progress
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withSaccountid(Integer saccountid) {
        this.setSaccountid(saccountid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.sAccountId
     *
     * @param saccountid the value for m_prj_project.sAccountId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withCreatedtime(LocalDateTime createdtime) {
        this.setCreatedtime(createdtime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.createdTime
     *
     * @param createdtime the value for m_prj_project.createdTime
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.lastUpdatedTime
     *
     * @return the value of m_prj_project.lastUpdatedTime
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.setLastupdatedtime(lastupdatedtime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_project.lastUpdatedTime
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.avatarId
     *
     * @return the value of m_prj_project.avatarId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withAvatarid(String avatarid) {
        this.setAvatarid(avatarid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.avatarId
     *
     * @param avatarid the value for m_prj_project.avatarId
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Boolean getContextask() {
        return contextask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withContextask(Boolean contextask) {
        this.setContextask(contextask);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.contextAsk
     *
     * @param contextask the value for m_prj_project.contextAsk
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withDeadline(LocalDate deadline) {
        this.setDeadline(deadline);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.deadline
     *
     * @param deadline the value for m_prj_project.deadline
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.isPublic
     *
     * @return the value of m_prj_project.isPublic
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Boolean getIspublic() {
        return ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withIspublic(Boolean ispublic) {
        this.setIspublic(ispublic);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.isPublic
     *
     * @param ispublic the value for m_prj_project.isPublic
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.isTemplate
     *
     * @return the value of m_prj_project.isTemplate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Boolean getIstemplate() {
        return istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withIstemplate(Boolean istemplate) {
        this.setIstemplate(istemplate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.isTemplate
     *
     * @param istemplate the value for m_prj_project.isTemplate
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setIstemplate(Boolean istemplate) {
        this.istemplate = istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.memLead
     *
     * @return the value of m_prj_project.memLead
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getMemlead() {
        return memlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withMemlead(String memlead) {
        this.setMemlead(memlead);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.memLead
     *
     * @param memlead the value for m_prj_project.memLead
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setMemlead(String memlead) {
        this.memlead = memlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.color
     *
     * @return the value of m_prj_project.color
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withColor(String color) {
        this.setColor(color);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.color
     *
     * @param color the value for m_prj_project.color
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
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
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public Project withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_project.description
     *
     * @param description the value for m_prj_project.description
     *
     * @mbg.generated Sat Feb 09 11:42:24 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        createuser,
        clientid,
        priority,
        shortname,
        planstartdate,
        planenddate,
        targetbudget,
        homepage,
        actualbudget,
        type,
        status,
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
        memlead,
        color,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}