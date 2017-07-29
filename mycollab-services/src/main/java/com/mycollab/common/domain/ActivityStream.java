/*Domain class of table s_activitystream*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_activitystream")
class ActivityStream extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.id
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.sAccountId
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.type
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.action
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("action")
    private String action;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdUser
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.module
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.extraTypeId
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ActivityStream item = (ActivityStream)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(391, 1875).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.id
     *
     * @return the value of s_activitystream.id
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdTime
     *
     * @return the value of s_activitystream.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdTime
     *
     * @param createdtime the value for s_activitystream.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.action
     *
     * @return the value of s_activitystream.action
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }
}