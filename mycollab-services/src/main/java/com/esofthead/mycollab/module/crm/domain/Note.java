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
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_crm_note")
public class Note extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.id
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.subject
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("subject")
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.type
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.typeid
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdTime
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.createdUser
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.sAccountId
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_note.note
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("note")
    private String note;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_note.id
     *
     * @return the value of m_crm_note.id
     *
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
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
     * @mbggenerated Mon Dec 22 11:25:10 ICT 2014
     */
    public void setNote(String note) {
        this.note = note;
    }

    public static enum Field {
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