/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.object_class
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("object_class")
    private String objectClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteddate
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("posteddate")
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.posteduser
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("posteduser")
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.sAccountId
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.type
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.typeid
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.module
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.activityLogId
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("activityLogId")
    private Integer activitylogid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_audit_log.changeset
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
        return new HashCodeBuilder(185, 1609).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_audit_log.id
     *
     * @return the value of m_audit_log.id
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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