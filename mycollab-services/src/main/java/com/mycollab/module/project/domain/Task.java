/*Domain class of table m_prj_task*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_task")
public class Task extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.id
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.name
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=400, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.percentagecomplete
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("percentagecomplete")
    private Double percentagecomplete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.startDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("startDate")
    private Date startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.endDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("endDate")
    private Date enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.priority
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.duration
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("duration")
    private Long duration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.isestimated
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("isestimated")
    private Boolean isestimated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.projectId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.dueDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("dueDate")
    private Date duedate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.taskindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("taskindex")
    private Integer taskindex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.createdTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.lastUpdatedTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.assignUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.sAccountId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.status
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.createdUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.taskkey
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("taskkey")
    private Integer taskkey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.originalEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("originalEstimate")
    private Double originalestimate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.remainEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("remainEstimate")
    private Double remainestimate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.parentTaskId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("parentTaskId")
    private Integer parenttaskid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.milestoneId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("milestoneId")
    private Integer milestoneid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.ganttindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Column("ganttindex")
    private Integer ganttindex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_task.description
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Task item = (Task)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(277, 841).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.id
     *
     * @return the value of m_prj_task.id
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.id
     *
     * @param id the value for m_prj_task.id
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.name
     *
     * @return the value of m_prj_task.name
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.name
     *
     * @param name the value for m_prj_task.name
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.percentagecomplete
     *
     * @return the value of m_prj_task.percentagecomplete
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Double getPercentagecomplete() {
        return percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.percentagecomplete
     *
     * @param percentagecomplete the value for m_prj_task.percentagecomplete
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setPercentagecomplete(Double percentagecomplete) {
        this.percentagecomplete = percentagecomplete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.startDate
     *
     * @return the value of m_prj_task.startDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.startDate
     *
     * @param startdate the value for m_prj_task.startDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.endDate
     *
     * @return the value of m_prj_task.endDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.endDate
     *
     * @param enddate the value for m_prj_task.endDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.priority
     *
     * @return the value of m_prj_task.priority
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.priority
     *
     * @param priority the value for m_prj_task.priority
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.duration
     *
     * @return the value of m_prj_task.duration
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.duration
     *
     * @param duration the value for m_prj_task.duration
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.isestimated
     *
     * @return the value of m_prj_task.isestimated
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Boolean getIsestimated() {
        return isestimated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.isestimated
     *
     * @param isestimated the value for m_prj_task.isestimated
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setIsestimated(Boolean isestimated) {
        this.isestimated = isestimated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.projectId
     *
     * @return the value of m_prj_task.projectId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.projectId
     *
     * @param projectid the value for m_prj_task.projectId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.dueDate
     *
     * @return the value of m_prj_task.dueDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.dueDate
     *
     * @param duedate the value for m_prj_task.dueDate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.taskindex
     *
     * @return the value of m_prj_task.taskindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getTaskindex() {
        return taskindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.taskindex
     *
     * @param taskindex the value for m_prj_task.taskindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setTaskindex(Integer taskindex) {
        this.taskindex = taskindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.createdTime
     *
     * @return the value of m_prj_task.createdTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.createdTime
     *
     * @param createdtime the value for m_prj_task.createdTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.lastUpdatedTime
     *
     * @return the value of m_prj_task.lastUpdatedTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_task.lastUpdatedTime
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.assignUser
     *
     * @return the value of m_prj_task.assignUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.assignUser
     *
     * @param assignuser the value for m_prj_task.assignUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.sAccountId
     *
     * @return the value of m_prj_task.sAccountId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.sAccountId
     *
     * @param saccountid the value for m_prj_task.sAccountId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.status
     *
     * @return the value of m_prj_task.status
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.status
     *
     * @param status the value for m_prj_task.status
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.createdUser
     *
     * @return the value of m_prj_task.createdUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.createdUser
     *
     * @param createduser the value for m_prj_task.createdUser
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.taskkey
     *
     * @return the value of m_prj_task.taskkey
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getTaskkey() {
        return taskkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.taskkey
     *
     * @param taskkey the value for m_prj_task.taskkey
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setTaskkey(Integer taskkey) {
        this.taskkey = taskkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.originalEstimate
     *
     * @return the value of m_prj_task.originalEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Double getOriginalestimate() {
        return originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.originalEstimate
     *
     * @param originalestimate the value for m_prj_task.originalEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setOriginalestimate(Double originalestimate) {
        this.originalestimate = originalestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.remainEstimate
     *
     * @return the value of m_prj_task.remainEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Double getRemainestimate() {
        return remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.remainEstimate
     *
     * @param remainestimate the value for m_prj_task.remainEstimate
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setRemainestimate(Double remainestimate) {
        this.remainestimate = remainestimate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.parentTaskId
     *
     * @return the value of m_prj_task.parentTaskId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getParenttaskid() {
        return parenttaskid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.parentTaskId
     *
     * @param parenttaskid the value for m_prj_task.parentTaskId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setParenttaskid(Integer parenttaskid) {
        this.parenttaskid = parenttaskid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.milestoneId
     *
     * @return the value of m_prj_task.milestoneId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getMilestoneid() {
        return milestoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.milestoneId
     *
     * @param milestoneid the value for m_prj_task.milestoneId
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setMilestoneid(Integer milestoneid) {
        this.milestoneid = milestoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.ganttindex
     *
     * @return the value of m_prj_task.ganttindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public Integer getGanttindex() {
        return ganttindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.ganttindex
     *
     * @param ganttindex the value for m_prj_task.ganttindex
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setGanttindex(Integer ganttindex) {
        this.ganttindex = ganttindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_task.description
     *
     * @return the value of m_prj_task.description
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_task.description
     *
     * @param description the value for m_prj_task.description
     *
     * @mbg.generated Sat Oct 01 11:44:32 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        percentagecomplete,
        startdate,
        enddate,
        priority,
        duration,
        isestimated,
        projectid,
        duedate,
        taskindex,
        createdtime,
        lastupdatedtime,
        assignuser,
        saccountid,
        status,
        createduser,
        taskkey,
        originalestimate,
        remainestimate,
        parenttaskid,
        milestoneid,
        ganttindex,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}