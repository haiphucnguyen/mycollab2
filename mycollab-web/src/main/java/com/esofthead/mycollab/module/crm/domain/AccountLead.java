/*Domain class of table m_crm_accounts_leads*/
package com.esofthead.mycollab.module.crm.domain;

import java.util.Date;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class AccountLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.id
     *
     * @return the value of m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.id
     *
     * @param id the value for m_crm_accounts_leads.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.accountId
     *
     * @return the value of m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.accountId
     *
     * @param accountid the value for m_crm_accounts_leads.accountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.leadId
     *
     * @return the value of m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.leadId
     *
     * @param leadid the value for m_crm_accounts_leads.leadId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_leads.createTime
     *
     * @return the value of m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_leads.createTime
     *
     * @param createtime the value for m_crm_accounts_leads.createTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}