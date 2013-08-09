/*Domain class of table m_crm_contract*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Contract extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.id
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.contractname
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String contractname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.status
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.code
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.accountid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.opportunityid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer opportunityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.currencyid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.customersigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date customersigneddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.companysigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date companysigneddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.type
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.description
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=4000, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.startdate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.enddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.contractvalue
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Long contractvalue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.createdTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.createdUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.sAccountId
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.assignUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.lastUpdatedTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.id
     *
     * @return the value of m_crm_contract.id
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.id
     *
     * @param id the value for m_crm_contract.id
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.contractname
     *
     * @return the value of m_crm_contract.contractname
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getContractname() {
        return contractname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.contractname
     *
     * @param contractname the value for m_crm_contract.contractname
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.status
     *
     * @return the value of m_crm_contract.status
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.status
     *
     * @param status the value for m_crm_contract.status
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.code
     *
     * @return the value of m_crm_contract.code
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.code
     *
     * @param code the value for m_crm_contract.code
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.accountid
     *
     * @return the value of m_crm_contract.accountid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.accountid
     *
     * @param accountid the value for m_crm_contract.accountid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.opportunityid
     *
     * @return the value of m_crm_contract.opportunityid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.opportunityid
     *
     * @param opportunityid the value for m_crm_contract.opportunityid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.currencyid
     *
     * @return the value of m_crm_contract.currencyid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.currencyid
     *
     * @param currencyid the value for m_crm_contract.currencyid
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.customersigneddate
     *
     * @return the value of m_crm_contract.customersigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getCustomersigneddate() {
        return customersigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.customersigneddate
     *
     * @param customersigneddate the value for m_crm_contract.customersigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCustomersigneddate(Date customersigneddate) {
        this.customersigneddate = customersigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.companysigneddate
     *
     * @return the value of m_crm_contract.companysigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getCompanysigneddate() {
        return companysigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.companysigneddate
     *
     * @param companysigneddate the value for m_crm_contract.companysigneddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCompanysigneddate(Date companysigneddate) {
        this.companysigneddate = companysigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.type
     *
     * @return the value of m_crm_contract.type
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.type
     *
     * @param type the value for m_crm_contract.type
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.description
     *
     * @return the value of m_crm_contract.description
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.description
     *
     * @param description the value for m_crm_contract.description
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.startdate
     *
     * @return the value of m_crm_contract.startdate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.startdate
     *
     * @param startdate the value for m_crm_contract.startdate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.enddate
     *
     * @return the value of m_crm_contract.enddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.enddate
     *
     * @param enddate the value for m_crm_contract.enddate
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.contractvalue
     *
     * @return the value of m_crm_contract.contractvalue
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Long getContractvalue() {
        return contractvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.contractvalue
     *
     * @param contractvalue the value for m_crm_contract.contractvalue
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setContractvalue(Long contractvalue) {
        this.contractvalue = contractvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.createdTime
     *
     * @return the value of m_crm_contract.createdTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.createdTime
     *
     * @param createdtime the value for m_crm_contract.createdTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.createdUser
     *
     * @return the value of m_crm_contract.createdUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.createdUser
     *
     * @param createduser the value for m_crm_contract.createdUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.sAccountId
     *
     * @return the value of m_crm_contract.sAccountId
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.sAccountId
     *
     * @param saccountid the value for m_crm_contract.sAccountId
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.assignUser
     *
     * @return the value of m_crm_contract.assignUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.assignUser
     *
     * @param assignuser the value for m_crm_contract.assignUser
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.lastUpdatedTime
     *
     * @return the value of m_crm_contract.lastUpdatedTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_contract.lastUpdatedTime
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}