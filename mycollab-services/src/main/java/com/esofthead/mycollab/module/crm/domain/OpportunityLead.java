/*Domain class of table m_crm_opportunities_leads*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

public class OpportunityLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.id
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.opportunityId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    private Integer opportunityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.leadId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.createdTime
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.id
     *
     * @return the value of m_crm_opportunities_leads.id
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.id
     *
     * @param id the value for m_crm_opportunities_leads.id
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.opportunityId
     *
     * @return the value of m_crm_opportunities_leads.opportunityId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.opportunityId
     *
     * @param opportunityid the value for m_crm_opportunities_leads.opportunityId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.leadId
     *
     * @return the value of m_crm_opportunities_leads.leadId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.leadId
     *
     * @param leadid the value for m_crm_opportunities_leads.leadId
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.createdTime
     *
     * @return the value of m_crm_opportunities_leads.createdTime
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.createdTime
     *
     * @param createdtime the value for m_crm_opportunities_leads.createdTime
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}