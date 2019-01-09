/*Domain class of table m_prj_milestone*/
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
@Table("m_prj_milestone")
@Alias("Milestone")
public class Milestone extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.name
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotEmpty
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.startDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("startDate")
    private LocalDate startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.endDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("endDate")
    private LocalDate enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.assignUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.flag
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("flag")
    private String flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.projectId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotNull
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.status
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.prjKey
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.dueDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Column("dueDate")
    private LocalDate duedate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.color
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=6, message="Field value is too long")
    @Column("color")
    private String color;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.priority
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("priority")
    private String priority;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.description
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Milestone item = (Milestone)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1059, 735).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.id
     *
     * @return the value of m_prj_milestone.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.id
     *
     * @param id the value for m_prj_milestone.id
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.name
     *
     * @return the value of m_prj_milestone.name
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.name
     *
     * @param name the value for m_prj_milestone.name
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.startDate
     *
     * @return the value of m_prj_milestone.startDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDate getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.startDate
     *
     * @param startdate the value for m_prj_milestone.startDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.endDate
     *
     * @return the value of m_prj_milestone.endDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDate getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.endDate
     *
     * @param enddate the value for m_prj_milestone.endDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.assignUser
     *
     * @return the value of m_prj_milestone.assignUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.assignUser
     *
     * @param assignuser the value for m_prj_milestone.assignUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.flag
     *
     * @return the value of m_prj_milestone.flag
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.flag
     *
     * @param flag the value for m_prj_milestone.flag
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.projectId
     *
     * @return the value of m_prj_milestone.projectId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.projectId
     *
     * @param projectid the value for m_prj_milestone.projectId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createdTime
     *
     * @return the value of m_prj_milestone.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createdTime
     *
     * @param createdtime the value for m_prj_milestone.createdTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @return the value of m_prj_milestone.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_milestone.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.sAccountId
     *
     * @return the value of m_prj_milestone.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.sAccountId
     *
     * @param saccountid the value for m_prj_milestone.sAccountId
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.status
     *
     * @return the value of m_prj_milestone.status
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.status
     *
     * @param status the value for m_prj_milestone.status
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createdUser
     *
     * @return the value of m_prj_milestone.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createdUser
     *
     * @param createduser the value for m_prj_milestone.createdUser
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.prjKey
     *
     * @return the value of m_prj_milestone.prjKey
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.prjKey
     *
     * @param prjkey the value for m_prj_milestone.prjKey
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.dueDate
     *
     * @return the value of m_prj_milestone.dueDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public LocalDate getDuedate() {
        return duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.dueDate
     *
     * @param duedate the value for m_prj_milestone.dueDate
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.color
     *
     * @return the value of m_prj_milestone.color
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.color
     *
     * @param color the value for m_prj_milestone.color
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.priority
     *
     * @return the value of m_prj_milestone.priority
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.priority
     *
     * @param priority the value for m_prj_milestone.priority
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.description
     *
     * @return the value of m_prj_milestone.description
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.description
     *
     * @param description the value for m_prj_milestone.description
     *
     * @mbg.generated Wed Jan 09 14:21:41 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        startdate,
        enddate,
        assignuser,
        flag,
        projectid,
        createdtime,
        lastupdatedtime,
        saccountid,
        status,
        createduser,
        prjkey,
        duedate,
        color,
        priority,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}