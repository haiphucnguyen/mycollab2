/*Domain class of table m_crm_contacts_leads*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_crm_contacts_leads")
public class ContactLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.id
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.contactId
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("contactId")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.leadId
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("leadId")
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_leads.isConvertRel
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("isConvertRel")
    private Boolean isconvertrel;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_leads.id
     *
     * @return the value of m_crm_contacts_leads.id
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
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
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    public void setIsconvertrel(Boolean isconvertrel) {
        this.isconvertrel = isconvertrel;
    }

    public static enum Field {
        id,
        contactid,
        leadid,
        isconvertrel;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}