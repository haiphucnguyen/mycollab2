/*Domain class of table m_crm_target_list*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_target_list")
public class TargetGroup extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.id
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.name
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.type
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.description
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Length(max=4000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.createdTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.createdUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.sAccountId
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.assignUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.lastUpdatedTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        TargetGroup item = (TargetGroup)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(723, 207).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.id
     *
     * @return the value of m_crm_target_list.id
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.id
     *
     * @param id the value for m_crm_target_list.id
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.name
     *
     * @return the value of m_crm_target_list.name
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.name
     *
     * @param name the value for m_crm_target_list.name
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.type
     *
     * @return the value of m_crm_target_list.type
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.type
     *
     * @param type the value for m_crm_target_list.type
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.description
     *
     * @return the value of m_crm_target_list.description
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.description
     *
     * @param description the value for m_crm_target_list.description
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.createdTime
     *
     * @return the value of m_crm_target_list.createdTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.createdTime
     *
     * @param createdtime the value for m_crm_target_list.createdTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.createdUser
     *
     * @return the value of m_crm_target_list.createdUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.createdUser
     *
     * @param createduser the value for m_crm_target_list.createdUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.sAccountId
     *
     * @return the value of m_crm_target_list.sAccountId
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.sAccountId
     *
     * @param saccountid the value for m_crm_target_list.sAccountId
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.assignUser
     *
     * @return the value of m_crm_target_list.assignUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.assignUser
     *
     * @param assignuser the value for m_crm_target_list.assignUser
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.lastUpdatedTime
     *
     * @return the value of m_crm_target_list.lastUpdatedTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target_list.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_target_list.lastUpdatedTime
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public enum Field {
        id,
        name,
        type,
        description,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        lastupdatedtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}