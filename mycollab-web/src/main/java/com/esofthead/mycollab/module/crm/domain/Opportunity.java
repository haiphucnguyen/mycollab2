package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Opportunity extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.id
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.opportunityName
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String opportunityname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.currencyid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.accountid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.amount
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.type
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.source
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.expectedClosedDate
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Date expectedcloseddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.campaignid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.nextStep
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String nextstep;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.probability
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer probability;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.description
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.createdTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.createdUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.sAccountId
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.assignUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.opportunityType
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String opportunitytype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.salesStage
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private String salesstage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunity.lastUpdatedTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.id
     *
     * @return the value of m_crm_opportunity.id
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.id
     *
     * @param id the value for m_crm_opportunity.id
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.opportunityName
     *
     * @return the value of m_crm_opportunity.opportunityName
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getOpportunityname() {
        return opportunityname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.opportunityName
     *
     * @param opportunityname the value for m_crm_opportunity.opportunityName
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setOpportunityname(String opportunityname) {
        this.opportunityname = opportunityname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.currencyid
     *
     * @return the value of m_crm_opportunity.currencyid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.currencyid
     *
     * @param currencyid the value for m_crm_opportunity.currencyid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.accountid
     *
     * @return the value of m_crm_opportunity.accountid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.accountid
     *
     * @param accountid the value for m_crm_opportunity.accountid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.amount
     *
     * @return the value of m_crm_opportunity.amount
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.amount
     *
     * @param amount the value for m_crm_opportunity.amount
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.type
     *
     * @return the value of m_crm_opportunity.type
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.type
     *
     * @param type the value for m_crm_opportunity.type
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.source
     *
     * @return the value of m_crm_opportunity.source
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.source
     *
     * @param source the value for m_crm_opportunity.source
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.expectedClosedDate
     *
     * @return the value of m_crm_opportunity.expectedClosedDate
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Date getExpectedcloseddate() {
        return expectedcloseddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.expectedClosedDate
     *
     * @param expectedcloseddate the value for m_crm_opportunity.expectedClosedDate
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setExpectedcloseddate(Date expectedcloseddate) {
        this.expectedcloseddate = expectedcloseddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.campaignid
     *
     * @return the value of m_crm_opportunity.campaignid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.campaignid
     *
     * @param campaignid the value for m_crm_opportunity.campaignid
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.nextStep
     *
     * @return the value of m_crm_opportunity.nextStep
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getNextstep() {
        return nextstep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.nextStep
     *
     * @param nextstep the value for m_crm_opportunity.nextStep
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setNextstep(String nextstep) {
        this.nextstep = nextstep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.probability
     *
     * @return the value of m_crm_opportunity.probability
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getProbability() {
        return probability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.probability
     *
     * @param probability the value for m_crm_opportunity.probability
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.description
     *
     * @return the value of m_crm_opportunity.description
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.description
     *
     * @param description the value for m_crm_opportunity.description
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.createdTime
     *
     * @return the value of m_crm_opportunity.createdTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.createdTime
     *
     * @param createdtime the value for m_crm_opportunity.createdTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.createdUser
     *
     * @return the value of m_crm_opportunity.createdUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.createdUser
     *
     * @param createduser the value for m_crm_opportunity.createdUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.sAccountId
     *
     * @return the value of m_crm_opportunity.sAccountId
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.sAccountId
     *
     * @param saccountid the value for m_crm_opportunity.sAccountId
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.assignUser
     *
     * @return the value of m_crm_opportunity.assignUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.assignUser
     *
     * @param assignuser the value for m_crm_opportunity.assignUser
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.opportunityType
     *
     * @return the value of m_crm_opportunity.opportunityType
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getOpportunitytype() {
        return opportunitytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.opportunityType
     *
     * @param opportunitytype the value for m_crm_opportunity.opportunityType
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setOpportunitytype(String opportunitytype) {
        this.opportunitytype = opportunitytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.salesStage
     *
     * @return the value of m_crm_opportunity.salesStage
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public String getSalesstage() {
        return salesstage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.salesStage
     *
     * @param salesstage the value for m_crm_opportunity.salesStage
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setSalesstage(String salesstage) {
        this.salesstage = salesstage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunity.lastUpdatedTime
     *
     * @return the value of m_crm_opportunity.lastUpdatedTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunity.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_opportunity.lastUpdatedTime
     *
     * @mbggenerated Mon Jan 14 15:29:25 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}