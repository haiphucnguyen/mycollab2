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
/*Domain class of table m_crm_contacts_cases*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_contacts_cases")
public class ContactCase extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    @Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    @Column("caseId")
    private Integer caseid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ContactCase item = (ContactCase)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(829, 1135).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.id
     *
     * @return the value of m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.id
     *
     * @param id the value for m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.contactId
     *
     * @return the value of m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.contactId
     *
     * @param contactid the value for m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.caseId
     *
     * @return the value of m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public Integer getCaseid() {
        return caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.caseId
     *
     * @param caseid the value for m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.createdTime
     *
     * @return the value of m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.createdTime
     *
     * @param createdtime the value for m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Sep 02 15:05:04 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public enum Field {
        id,
        contactid,
        caseid,
        createdtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}