/*Domain class of table m_prj_risk*/
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
@Table("m_prj_risk")
@Alias("Risk")
public class Risk extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.id
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.name
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=400, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.projectId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.createdUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.assignUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.consequence
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("consequence")
    private String consequence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.probability
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("probability")
    private String probability;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.status
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.raisedDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("raisedDate")
    private LocalDate raiseddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.dueDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("dueDate")
    private LocalDate duedate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.response
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("response")
    private String response;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.resolution
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=4000, message="Field value is too long")
    @Column("resolution")
    private String resolution;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.source
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("source")
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.createdTime
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.lastUpdatedTime
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.sAccountId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.startDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("startDate")
    private LocalDate startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.endDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("endDate")
    private LocalDate enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.milestoneId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("milestoneId")
    private Integer milestoneid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.percentageComplete
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("percentageComplete")
    private Double percentagecomplete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.priority
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.remainEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("remainEstimate")
    private Double remainestimate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.originalEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("originalEstimate")
    private Double originalestimate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.description
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Risk item = (Risk)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(355, 1027).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.id
     *
     * @return the value of m_prj_risk.id
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.name
     *
     * @return the value of m_prj_risk.name
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.name
     *
     * @param name the value for m_prj_risk.name
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.projectId
     *
     * @return the value of m_prj_risk.projectId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.projectId
     *
     * @param projectid the value for m_prj_risk.projectId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.createdUser
     *
     * @return the value of m_prj_risk.createdUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.createdUser
     *
     * @param createduser the value for m_prj_risk.createdUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.assignUser
     *
     * @return the value of m_prj_risk.assignUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.assignUser
     *
     * @param assignuser the value for m_prj_risk.assignUser
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.consequence
     *
     * @return the value of m_prj_risk.consequence
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.probability
     *
     * @return the value of m_prj_risk.probability
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public String getProbability() {
        return probability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.probability
     *
     * @param probability the value for m_prj_risk.probability
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setProbability(String probability) {
        this.probability = probability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.status
     *
     * @return the value of m_prj_risk.status
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.raisedDate
     *
     * @return the value of m_prj_risk.raisedDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDate getRaiseddate() {
        return raiseddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.raisedDate
     *
     * @param raiseddate the value for m_prj_risk.raisedDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setRaiseddate(LocalDate raiseddate) {
        this.raiseddate = raiseddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.dueDate
     *
     * @return the value of m_prj_risk.dueDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDate getDuedate() {
        return duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.dueDate
     *
     * @param duedate the value for m_prj_risk.dueDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.response
     *
     * @return the value of m_prj_risk.response
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.source
     *
     * @return the value of m_prj_risk.source
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.createdTime
     *
     * @param createdtime the value for m_prj_risk.createdTime
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @return the value of m_prj_risk.lastUpdatedTime
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_risk.lastUpdatedTime
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.sAccountId
     *
     * @return the value of m_prj_risk.sAccountId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.startDate
     *
     * @return the value of m_prj_risk.startDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDate getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.startDate
     *
     * @param startdate the value for m_prj_risk.startDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.endDate
     *
     * @return the value of m_prj_risk.endDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public LocalDate getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.endDate
     *
     * @param enddate the value for m_prj_risk.endDate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.milestoneId
     *
     * @return the value of m_prj_risk.milestoneId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public Integer getMilestoneid() {
        return milestoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.milestoneId
     *
     * @param milestoneid the value for m_prj_risk.milestoneId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setMilestoneid(Integer milestoneid) {
        this.milestoneid = milestoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.percentageComplete
     *
     * @return the value of m_prj_risk.percentageComplete
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public Double getPercentagecomplete() {
        return percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.percentageComplete
     *
     * @param percentagecomplete the value for m_prj_risk.percentageComplete
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setPercentagecomplete(Double percentagecomplete) {
        this.percentagecomplete = percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.priority
     *
     * @return the value of m_prj_risk.priority
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.priority
     *
     * @param priority the value for m_prj_risk.priority
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.remainEstimate
     *
     * @return the value of m_prj_risk.remainEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public Double getRemainestimate() {
        return remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.remainEstimate
     *
     * @param remainestimate the value for m_prj_risk.remainEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setRemainestimate(Double remainestimate) {
        this.remainestimate = remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.originalEstimate
     *
     * @return the value of m_prj_risk.originalEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public Double getOriginalestimate() {
        return originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.originalEstimate
     *
     * @param originalestimate the value for m_prj_risk.originalEstimate
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setOriginalestimate(Double originalestimate) {
        this.originalestimate = originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.description
     *
     * @return the value of m_prj_risk.description
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        projectid,
        createduser,
        assignuser,
        consequence,
        probability,
        status,
        raiseddate,
        duedate,
        response,
        resolution,
        source,
        createdtime,
        lastupdatedtime,
        saccountid,
        startdate,
        enddate,
        milestoneid,
        percentagecomplete,
        priority,
        remainestimate,
        originalestimate,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}