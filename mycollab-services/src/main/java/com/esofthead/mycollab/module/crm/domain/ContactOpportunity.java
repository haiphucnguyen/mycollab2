/*Domain class of table m_crm_contacts_opportunities*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class ContactOpportunity extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.id
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.contactId
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.opportunityId
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer opportunityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_opportunities.createdTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_opportunities.id
     *
     * @return the value of m_crm_contacts_opportunities.id
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}