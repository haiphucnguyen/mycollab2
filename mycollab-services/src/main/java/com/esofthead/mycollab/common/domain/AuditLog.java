/*Domain class of table m_audit_log*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_audit_log")
public class AuditLog extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.object_class
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("object_class")
    private String objectClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteddate
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("posteddate")
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteduser
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("posteduser")
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.sAccountId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.type
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.typeid
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.module
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.activityLogId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("activityLogId")
    private Integer activitylogid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.changeset
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("changeset")
    private String changeset;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        AuditLog item = (AuditLog)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1653, 1429).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.id
     *
     * @return the value of m_audit_log.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.sAccountId
     *
     * @return the value of m_audit_log.sAccountId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.activityLogId
     *
     * @return the value of m_audit_log.activityLogId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Integer getActivitylogid() {
        return activitylogid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_audit_log.activityLogId
     *
     * @param activitylogid the value for m_audit_log.activityLogId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setActivitylogid(Integer activitylogid) {
        this.activitylogid = activitylogid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.changeset
     *
     * @return the value of m_audit_log.changeset
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setChangeset(String changeset) {
        this.changeset = changeset;
    }

    public enum Field {
        id,
        objectClass,
        posteddate,
        posteduser,
        saccountid,
        type,
        typeid,
        module,
        activitylogid,
        changeset;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}