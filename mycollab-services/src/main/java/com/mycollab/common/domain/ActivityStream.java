/*Domain class of table s_activitystream*/
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
@Table("s_activitystream")
@Alias("ActivityStream")
public class ActivityStream extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.id
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.sAccountId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.type
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.typeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=100, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdTime
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.action
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("action")
    private String action;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdUser
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.module
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.extraTypeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.nameField
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("nameField")
    private String namefield;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ActivityStream item = (ActivityStream)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1505, 909).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.id
     *
     * @return the value of s_activitystream.id
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.id
     *
     * @param id the value for s_activitystream.id
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.sAccountId
     *
     * @return the value of s_activitystream.sAccountId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.sAccountId
     *
     * @param saccountid the value for s_activitystream.sAccountId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.type
     *
     * @return the value of s_activitystream.type
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.type
     *
     * @param type the value for s_activitystream.type
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.typeId
     *
     * @return the value of s_activitystream.typeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.typeId
     *
     * @param typeid the value for s_activitystream.typeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdTime
     *
     * @return the value of s_activitystream.createdTime
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdTime
     *
     * @param createdtime the value for s_activitystream.createdTime
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.action
     *
     * @return the value of s_activitystream.action
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.action
     *
     * @param action the value for s_activitystream.action
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdUser
     *
     * @return the value of s_activitystream.createdUser
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdUser
     *
     * @param createduser the value for s_activitystream.createdUser
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.module
     *
     * @return the value of s_activitystream.module
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.module
     *
     * @param module the value for s_activitystream.module
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.extraTypeId
     *
     * @return the value of s_activitystream.extraTypeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.extraTypeId
     *
     * @param extratypeid the value for s_activitystream.extraTypeId
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.nameField
     *
     * @return the value of s_activitystream.nameField
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getNamefield() {
        return namefield;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.nameField
     *
     * @param namefield the value for s_activitystream.nameField
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setNamefield(String namefield) {
        this.namefield = namefield;
    }

    public enum Field {
        id,
        saccountid,
        type,
        typeid,
        createdtime,
        action,
        createduser,
        module,
        extratypeid,
        namefield;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}