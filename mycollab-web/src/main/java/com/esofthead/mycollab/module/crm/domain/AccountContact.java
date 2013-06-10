/*Domain class of table m_crm_accounts_contacts*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class AccountContact extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_contacts.id
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_contacts.accountId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_contacts.contactId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_accounts_contacts.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_contacts.id
     *
     * @return the value of m_crm_accounts_contacts.id
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_contacts.id
     *
     * @param id the value for m_crm_accounts_contacts.id
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_contacts.accountId
     *
     * @return the value of m_crm_accounts_contacts.accountId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_contacts.accountId
     *
     * @param accountid the value for m_crm_accounts_contacts.accountId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_contacts.contactId
     *
     * @return the value of m_crm_accounts_contacts.contactId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_contacts.contactId
     *
     * @param contactid the value for m_crm_accounts_contacts.contactId
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_accounts_contacts.createdTime
     *
     * @return the value of m_crm_accounts_contacts.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_accounts_contacts.createdTime
     *
     * @param createdtime the value for m_crm_accounts_contacts.createdTime
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}