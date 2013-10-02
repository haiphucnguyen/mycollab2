/*Domain class of table m_crm_contacts_cases*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class ContactCase extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    private Integer caseid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.id
     *
     * @return the value of m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.id
     *
     * @param id the value for m_crm_contacts_cases.id
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.contactId
     *
     * @return the value of m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.contactId
     *
     * @param contactid the value for m_crm_contacts_cases.contactId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.caseId
     *
     * @return the value of m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public Integer getCaseid() {
        return caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.caseId
     *
     * @param caseid the value for m_crm_contacts_cases.caseId
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contacts_cases.createdTime
     *
     * @return the value of m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contacts_cases.createdTime
     *
     * @param createdtime the value for m_crm_contacts_cases.createdTime
     *
     * @mbggenerated Wed Oct 02 14:27:41 ICT 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}