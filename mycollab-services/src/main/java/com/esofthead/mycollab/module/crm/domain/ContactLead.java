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
/*Domain class of table m_crm_contacts_leads*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_contacts_leads")
public class ContactLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.id
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.contactId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    @Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.leadId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    @Column("leadId")
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.isConvertRel
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    @Column("isConvertRel")
    private Boolean isconvertrel;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ContactLead item = (ContactLead)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1395, 1123).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_leads.id
     *
     * @return the value of m_crm_contacts_leads.id
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_leads.id
     *
     * @param id the value for m_crm_contacts_leads.id
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_leads.contactId
     *
     * @return the value of m_crm_contacts_leads.contactId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_leads.contactId
     *
     * @param contactid the value for m_crm_contacts_leads.contactId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_leads.leadId
     *
     * @return the value of m_crm_contacts_leads.leadId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_leads.leadId
     *
     * @param leadid the value for m_crm_contacts_leads.leadId
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_leads.isConvertRel
     *
     * @return the value of m_crm_contacts_leads.isConvertRel
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public Boolean getIsconvertrel() {
        return isconvertrel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_leads.isConvertRel
     *
     * @param isconvertrel the value for m_crm_contacts_leads.isConvertRel
     *
     * @mbggenerated Mon Mar 21 16:11:33 ICT 2016
     */
    public void setIsconvertrel(Boolean isconvertrel) {
        this.isconvertrel = isconvertrel;
    }

    public enum Field {
        id,
        contactid,
        leadid,
        isconvertrel;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}