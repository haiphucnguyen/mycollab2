package com.esofthead.mycollab.module.crm.domain;

import java.util.Date;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class AccountOpportunity extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_opportunities.id
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_opportunities.accountId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_opportunities.opportunityId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    private Integer opportunityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_opportunities.createdTime
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_opportunities.id
     *
     * @return the value of m_crm_accounts_opportunities.id
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_opportunities.id
     *
     * @param id the value for m_crm_accounts_opportunities.id
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_opportunities.accountId
     *
     * @return the value of m_crm_accounts_opportunities.accountId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_opportunities.accountId
     *
     * @param accountid the value for m_crm_accounts_opportunities.accountId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_opportunities.opportunityId
     *
     * @return the value of m_crm_accounts_opportunities.opportunityId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_opportunities.opportunityId
     *
     * @param opportunityid the value for m_crm_accounts_opportunities.opportunityId
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_opportunities.createdTime
     *
     * @return the value of m_crm_accounts_opportunities.createdTime
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_opportunities.createdTime
     *
     * @param createdtime the value for m_crm_accounts_opportunities.createdTime
     *
     * @mbggenerated Tue Jan 22 11:19:16 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}