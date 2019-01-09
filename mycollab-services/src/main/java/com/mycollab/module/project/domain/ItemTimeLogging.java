/*Domain class of table m_prj_time_logging*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_time_logging")
@Alias("ItemTimeLogging")
public class ItemTimeLogging extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.id
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.projectId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @NotNull
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.type
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.typeId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("typeId")
    private Integer typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logValue
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @NotNull
    @Column("logValue")
    private Double logvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("logUser")
    private String loguser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.createdTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logForDay
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @NotNull
    @Column("logForDay")
    private LocalDate logforday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.isBillable
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @NotNull
    @Column("isBillable")
    private Boolean isbillable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.createdUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.isOvertime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("isOvertime")
    private Boolean isovertime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.isApproved
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("isApproved")
    private Boolean isapproved;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.approveUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("approveUser")
    private String approveuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.approveTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Column("approveTime")
    private LocalDateTime approvetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.note
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("note")
    private String note;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ItemTimeLogging item = (ItemTimeLogging)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1593, 945).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.id
     *
     * @return the value of m_prj_time_logging.id
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.id
     *
     * @param id the value for m_prj_time_logging.id
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.projectId
     *
     * @return the value of m_prj_time_logging.projectId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.projectId
     *
     * @param projectid the value for m_prj_time_logging.projectId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.type
     *
     * @return the value of m_prj_time_logging.type
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.type
     *
     * @param type the value for m_prj_time_logging.type
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.typeId
     *
     * @return the value of m_prj_time_logging.typeId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.typeId
     *
     * @param typeid the value for m_prj_time_logging.typeId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logValue
     *
     * @return the value of m_prj_time_logging.logValue
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Double getLogvalue() {
        return logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logValue
     *
     * @param logvalue the value for m_prj_time_logging.logValue
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setLogvalue(Double logvalue) {
        this.logvalue = logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logUser
     *
     * @return the value of m_prj_time_logging.logUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public String getLoguser() {
        return loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logUser
     *
     * @param loguser the value for m_prj_time_logging.logUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setLoguser(String loguser) {
        this.loguser = loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.createdTime
     *
     * @return the value of m_prj_time_logging.createdTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.createdTime
     *
     * @param createdtime the value for m_prj_time_logging.createdTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @return the value of m_prj_time_logging.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_time_logging.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.sAccountId
     *
     * @return the value of m_prj_time_logging.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.sAccountId
     *
     * @param saccountid the value for m_prj_time_logging.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logForDay
     *
     * @return the value of m_prj_time_logging.logForDay
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public LocalDate getLogforday() {
        return logforday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logForDay
     *
     * @param logforday the value for m_prj_time_logging.logForDay
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setLogforday(LocalDate logforday) {
        this.logforday = logforday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.isBillable
     *
     * @return the value of m_prj_time_logging.isBillable
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Boolean getIsbillable() {
        return isbillable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.isBillable
     *
     * @param isbillable the value for m_prj_time_logging.isBillable
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setIsbillable(Boolean isbillable) {
        this.isbillable = isbillable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.createdUser
     *
     * @return the value of m_prj_time_logging.createdUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.createdUser
     *
     * @param createduser the value for m_prj_time_logging.createdUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.isOvertime
     *
     * @return the value of m_prj_time_logging.isOvertime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Boolean getIsovertime() {
        return isovertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.isOvertime
     *
     * @param isovertime the value for m_prj_time_logging.isOvertime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setIsovertime(Boolean isovertime) {
        this.isovertime = isovertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.isApproved
     *
     * @return the value of m_prj_time_logging.isApproved
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public Boolean getIsapproved() {
        return isapproved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.isApproved
     *
     * @param isapproved the value for m_prj_time_logging.isApproved
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setIsapproved(Boolean isapproved) {
        this.isapproved = isapproved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.approveUser
     *
     * @return the value of m_prj_time_logging.approveUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public String getApproveuser() {
        return approveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.approveUser
     *
     * @param approveuser the value for m_prj_time_logging.approveUser
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setApproveuser(String approveuser) {
        this.approveuser = approveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.approveTime
     *
     * @return the value of m_prj_time_logging.approveTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public LocalDateTime getApprovetime() {
        return approvetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.approveTime
     *
     * @param approvetime the value for m_prj_time_logging.approveTime
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setApprovetime(LocalDateTime approvetime) {
        this.approvetime = approvetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.note
     *
     * @return the value of m_prj_time_logging.note
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.note
     *
     * @param note the value for m_prj_time_logging.note
     *
     * @mbg.generated Wed Jan 09 17:12:50 CST 2019
     */
    public void setNote(String note) {
        this.note = note;
    }

    public enum Field {
        id,
        projectid,
        type,
        typeid,
        logvalue,
        loguser,
        createdtime,
        lastupdatedtime,
        saccountid,
        logforday,
        isbillable,
        createduser,
        isovertime,
        isapproved,
        approveuser,
        approvetime,
        note;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}