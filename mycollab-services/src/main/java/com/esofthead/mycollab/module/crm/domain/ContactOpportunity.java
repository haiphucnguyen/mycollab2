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
/*Domain class of table m_crm_contacts_opportunities*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_contacts_opportunities")
public class ContactOpportunity extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.id
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.contactId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    @Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.opportunityId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    @Column("opportunityId")
    private Integer opportunityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.decisionRole
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("decisionRole")
    private String decisionrole;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ContactOpportunity item = (ContactOpportunity)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(829, 1471).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.id
     *
     * @return the value of m_crm_contacts_opportunities.id
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.id
     *
     * @param id the value for m_crm_contacts_opportunities.id
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.contactId
     *
     * @return the value of m_crm_contacts_opportunities.contactId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.contactId
     *
     * @param contactid the value for m_crm_contacts_opportunities.contactId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.opportunityId
     *
     * @return the value of m_crm_contacts_opportunities.opportunityId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.opportunityId
     *
     * @param opportunityid the value for m_crm_contacts_opportunities.opportunityId
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.createdTime
     *
     * @return the value of m_crm_contacts_opportunities.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.createdTime
     *
     * @param createdtime the value for m_crm_contacts_opportunities.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.decisionRole
     *
     * @return the value of m_crm_contacts_opportunities.decisionRole
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public String getDecisionrole() {
        return decisionrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.decisionRole
     *
     * @param decisionrole the value for m_crm_contacts_opportunities.decisionRole
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    public void setDecisionrole(String decisionrole) {
        this.decisionrole = decisionrole;
    }

    public enum Field {
        id,
        contactid,
        opportunityid,
        createdtime,
        decisionrole;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}