package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class CampaignAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.id
     *
     * @return the value of m_crm_campaigns_accounts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.id
     *
     * @param id the value for m_crm_campaigns_accounts.id
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.campaignId
     *
     * @return the value of m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.accountId
     *
     * @return the value of m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.accountId
     *
     * @param accountid the value for m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.createdTime
     *
     * @return the value of m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}