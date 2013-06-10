/*Domain class of table m_crm_target_list*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class TargetGroup extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.id
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.name
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.type
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.description
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=4000, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.createdTime
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.createdUser
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.sAccountId
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.assignUser
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target_list.lastUpdatedTime
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target_list.id
     *
     * @return the value of m_crm_target_list.id
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
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
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}