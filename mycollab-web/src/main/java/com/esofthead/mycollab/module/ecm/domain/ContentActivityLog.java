/*Domain class of table m_ecm_activity_log*/
package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class ContentActivityLog extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.id
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdUser
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdTime
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String createduserfullname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String actiondesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.id
     *
     * @return the value of m_ecm_activity_log.id
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.id
     *
     * @param id the value for m_ecm_activity_log.id
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdUser
     *
     * @return the value of m_ecm_activity_log.createdUser
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdUser
     *
     * @param createduser the value for m_ecm_activity_log.createdUser
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdTime
     *
     * @return the value of m_ecm_activity_log.createdTime
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdTime
     *
     * @param createdtime the value for m_ecm_activity_log.createdTime
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdUserFullName
     *
     * @return the value of m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public String getCreateduserfullname() {
        return createduserfullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdUserFullName
     *
     * @param createduserfullname the value for m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public void setCreateduserfullname(String createduserfullname) {
        this.createduserfullname = createduserfullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.actionDesc
     *
     * @return the value of m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public String getActiondesc() {
        return actiondesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.actionDesc
     *
     * @param actiondesc the value for m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Mon Aug 05 11:12:13 GMT+07:00 2013
     */
    public void setActiondesc(String actiondesc) {
        this.actiondesc = actiondesc;
    }
}