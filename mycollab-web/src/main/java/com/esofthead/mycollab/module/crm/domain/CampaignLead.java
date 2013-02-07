package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class CampaignLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.id
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.id
     *
     * @return the value of m_crm_campaigns_leads.id
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.id
     *
     * @param id the value for m_crm_campaigns_leads.id
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.campaignId
     *
     * @return the value of m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.leadId
     *
     * @return the value of m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.leadId
     *
     * @param leadid the value for m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.createdTime
     *
     * @return the value of m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}