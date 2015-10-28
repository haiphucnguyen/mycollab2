/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_prj_project*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_project")
public class Project extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.id
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.name
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.owner
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("owner")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.account
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("account")
    private Integer account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.priority
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.shortname
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("shortname")
    private String shortname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planStartDate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("planStartDate")
    private Date planstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.planEndDate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("planEndDate")
    private Date planenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.targetBudget
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("targetBudget")
    private Double targetbudget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.homePage
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("homePage")
    private String homepage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualBudget
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("actualBudget")
    private Double actualbudget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectType
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("projectType")
    private String projecttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.projectStatus
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("projectStatus")
    private String projectstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultBillingRate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("defaultBillingRate")
    private Double defaultbillingrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualStartDate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("actualStartDate")
    private Date actualstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.actualEndDate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("actualEndDate")
    private Date actualenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.defaultOvertimeBillingRate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("defaultOvertimeBillingRate")
    private Double defaultovertimebillingrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.currencyid
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("currencyid")
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.progress
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("progress")
    private Double progress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.sAccountId
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.createdTime
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.lastUpdatedTime
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.avatarId
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.contextAsk
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("contextAsk")
    private Boolean contextask;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.deadline
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("deadline")
    private Date deadline;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.ispublic
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("ispublic")
    private Boolean ispublic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.istemplate
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Column("istemplate")
    private Boolean istemplate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_project.description
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Project item = (Project)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1931, 1341).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.id
     *
     * @return the value of m_prj_project.id
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    public void setIstemplate(Boolean istemplate) {
        this.istemplate = istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_project.description
     *
     * @return the value of m_prj_project.description
     *
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
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
     * @mbggenerated Wed Oct 28 16:06:53 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        owner,
        account,
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
        actualstartdate,
        actualenddate,
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
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}