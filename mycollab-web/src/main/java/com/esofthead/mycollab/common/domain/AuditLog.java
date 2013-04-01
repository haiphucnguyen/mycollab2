package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class AuditLog extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.id
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.object_class
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private String objectClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteddate
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteduser
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.type
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.typeid
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.module
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.changeset
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    private String changeset;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.id
     *
     * @return the value of m_audit_log.id
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.id
     *
     * @param id the value for m_audit_log.id
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.object_class
     *
     * @return the value of m_audit_log.object_class
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public String getObjectClass() {
        return objectClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.object_class
     *
     * @param objectClass the value for m_audit_log.object_class
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.posteddate
     *
     * @return the value of m_audit_log.posteddate
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Date getPosteddate() {
        return posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.posteddate
     *
     * @param posteddate the value for m_audit_log.posteddate
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.posteduser
     *
     * @return the value of m_audit_log.posteduser
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public String getPosteduser() {
        return posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.posteduser
     *
     * @param posteduser the value for m_audit_log.posteduser
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.createdTime
     *
     * @return the value of m_audit_log.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.createdTime
     *
     * @param createdtime the value for m_audit_log.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.lastUpdatedTime
     *
     * @return the value of m_audit_log.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_audit_log.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.sAccountId
     *
     * @return the value of m_audit_log.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.sAccountId
     *
     * @param saccountid the value for m_audit_log.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.type
     *
     * @return the value of m_audit_log.type
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.type
     *
     * @param type the value for m_audit_log.type
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.typeid
     *
     * @return the value of m_audit_log.typeid
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.typeid
     *
     * @param typeid the value for m_audit_log.typeid
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.module
     *
     * @return the value of m_audit_log.module
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.module
     *
     * @param module the value for m_audit_log.module
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.changeset
     *
     * @return the value of m_audit_log.changeset
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public String getChangeset() {
        return changeset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.changeset
     *
     * @param changeset the value for m_audit_log.changeset
     *
     * @mbggenerated Mon Apr 01 22:06:57 GMT+07:00 2013
     */
    public void setChangeset(String changeset) {
        this.changeset = changeset;
    }
}