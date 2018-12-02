/*Domain class of table m_crm_contacts_opportunities*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_contacts_opportunities")
@Alias("ContactOpportunity")
public class ContactOpportunity extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.contactId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("contactId")
    private Integer contactid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.opportunityId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("opportunityId")
    private Integer opportunityid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.decisionRole
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("decisionRole")
    private String decisionrole;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ContactOpportunity item = (ContactOpportunity)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(287, 1195).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.id
     *
     * @return the value of m_crm_contacts_opportunities.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_opportunities.createdTime
     *
     * @param createdtime the value for m_crm_contacts_opportunities.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.decisionRole
     *
     * @return the value of m_crm_contacts_opportunities.decisionRole
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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