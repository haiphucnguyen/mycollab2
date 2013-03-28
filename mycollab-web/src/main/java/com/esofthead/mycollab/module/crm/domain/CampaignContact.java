package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class CampaignContact extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.contactId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.id
     *
     * @return the value of m_crm_campaigns_contacts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.id
     *
     * @param id the value for m_crm_campaigns_contacts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.campaignId
     *
     * @return the value of m_crm_campaigns_contacts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_contacts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.contactId
     *
     * @return the value of m_crm_campaigns_contacts.contactId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.contactId
     *
     * @param contactid the value for m_crm_campaigns_contacts.contactId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.createdTime
     *
     * @return the value of m_crm_campaigns_contacts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_contacts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}