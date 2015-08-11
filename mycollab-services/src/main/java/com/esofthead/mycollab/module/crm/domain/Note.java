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
/*Domain class of table m_crm_note*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_note")
public class Note extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.id
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.subject
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("subject")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.type
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.typeid
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdUser
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.sAccountId
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.note
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("note")
    private String note;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Note item = (Note)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1265, 647).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.id
     *
     * @return the value of m_crm_note.id
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.id
     *
     * @param id the value for m_crm_note.id
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.subject
     *
     * @return the value of m_crm_note.subject
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.subject
     *
     * @param subject the value for m_crm_note.subject
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.type
     *
     * @return the value of m_crm_note.type
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.type
     *
     * @param type the value for m_crm_note.type
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.typeid
     *
     * @return the value of m_crm_note.typeid
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.typeid
     *
     * @param typeid the value for m_crm_note.typeid
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.createdTime
     *
     * @return the value of m_crm_note.createdTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.createdTime
     *
     * @param createdtime the value for m_crm_note.createdTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.createdUser
     *
     * @return the value of m_crm_note.createdUser
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.createdUser
     *
     * @param createduser the value for m_crm_note.createdUser
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.sAccountId
     *
     * @return the value of m_crm_note.sAccountId
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.sAccountId
     *
     * @param saccountid the value for m_crm_note.sAccountId
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.lastUpdatedTime
     *
     * @return the value of m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.note
     *
     * @return the value of m_crm_note.note
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_note.note
     *
     * @param note the value for m_crm_note.note
     *
     * @mbggenerated Tue Aug 11 12:50:52 ICT 2015
     */
    public void setNote(String note) {
        this.note = note;
    }

    public enum Field {
        id,
        subject,
        type,
        typeid,
        createdtime,
        createduser,
        saccountid,
        lastupdatedtime,
        note;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}