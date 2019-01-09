/*Domain class of table s_relay_email_notification*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_relay_email_notification")
@Alias("RelayEmailNotification")
public class RelayEmailNotification extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.id
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.sAccountId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.type
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.typeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Length(max=100, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.action
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("action")
    private String action;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.changeBy
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("changeBy")
    private String changeby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.extraTypeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.createdTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.changeComment
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("changeComment")
    private String changecomment;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        RelayEmailNotification item = (RelayEmailNotification)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1975, 1489).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.id
     *
     * @return the value of s_relay_email_notification.id
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.id
     *
     * @param id the value for s_relay_email_notification.id
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.sAccountId
     *
     * @return the value of s_relay_email_notification.sAccountId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.sAccountId
     *
     * @param saccountid the value for s_relay_email_notification.sAccountId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.type
     *
     * @return the value of s_relay_email_notification.type
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.type
     *
     * @param type the value for s_relay_email_notification.type
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.typeId
     *
     * @return the value of s_relay_email_notification.typeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.typeId
     *
     * @param typeid the value for s_relay_email_notification.typeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.action
     *
     * @return the value of s_relay_email_notification.action
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.action
     *
     * @param action the value for s_relay_email_notification.action
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.changeBy
     *
     * @return the value of s_relay_email_notification.changeBy
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public String getChangeby() {
        return changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.changeBy
     *
     * @param changeby the value for s_relay_email_notification.changeBy
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setChangeby(String changeby) {
        this.changeby = changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.extraTypeId
     *
     * @return the value of s_relay_email_notification.extraTypeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.extraTypeId
     *
     * @param extratypeid the value for s_relay_email_notification.extraTypeId
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.createdTime
     *
     * @return the value of s_relay_email_notification.createdTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.createdTime
     *
     * @param createdtime the value for s_relay_email_notification.createdTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.lastUpdatedTime
     *
     * @return the value of s_relay_email_notification.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.lastUpdatedTime
     *
     * @param lastupdatedtime the value for s_relay_email_notification.lastUpdatedTime
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.changeComment
     *
     * @return the value of s_relay_email_notification.changeComment
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public String getChangecomment() {
        return changecomment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.changeComment
     *
     * @param changecomment the value for s_relay_email_notification.changeComment
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    public void setChangecomment(String changecomment) {
        this.changecomment = changecomment;
    }

    public enum Field {
        id,
        saccountid,
        type,
        typeid,
        action,
        changeby,
        extratypeid,
        createdtime,
        lastupdatedtime,
        changecomment;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}