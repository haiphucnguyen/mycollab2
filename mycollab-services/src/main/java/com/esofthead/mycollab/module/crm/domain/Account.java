/*Domain class of table m_crm_account*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

public class Account extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.accountName
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.website
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.phoneOffice
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String phoneoffice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.fax
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.alternatePhone
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String alternatephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.annualRevenue
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String annualrevenue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.billingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String billingaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.city
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.postalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String postalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.state
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.email
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.ownership
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String ownership;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String shippingaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingCity
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String shippingcity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingPostalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String shippingpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingState
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String shippingstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.numemployees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer numemployees;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.createdTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.createdUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.sAccountId
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.assignUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.type
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.industry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String industry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.lastUpdatedTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.billingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String billingcountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String shippingcountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.description
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.id
     *
     * @return the value of m_crm_account.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.id
     *
     * @param id the value for m_crm_account.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.accountName
     *
     * @return the value of m_crm_account.accountName
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.accountName
     *
     * @param accountname the value for m_crm_account.accountName
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.website
     *
     * @return the value of m_crm_account.website
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.website
     *
     * @param website the value for m_crm_account.website
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.phoneOffice
     *
     * @return the value of m_crm_account.phoneOffice
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getPhoneoffice() {
        return phoneoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.phoneOffice
     *
     * @param phoneoffice the value for m_crm_account.phoneOffice
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setPhoneoffice(String phoneoffice) {
        this.phoneoffice = phoneoffice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.fax
     *
     * @return the value of m_crm_account.fax
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.fax
     *
     * @param fax the value for m_crm_account.fax
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.alternatePhone
     *
     * @return the value of m_crm_account.alternatePhone
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getAlternatephone() {
        return alternatephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.alternatePhone
     *
     * @param alternatephone the value for m_crm_account.alternatePhone
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setAlternatephone(String alternatephone) {
        this.alternatephone = alternatephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.annualRevenue
     *
     * @return the value of m_crm_account.annualRevenue
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getAnnualrevenue() {
        return annualrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.annualRevenue
     *
     * @param annualrevenue the value for m_crm_account.annualRevenue
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setAnnualrevenue(String annualrevenue) {
        this.annualrevenue = annualrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.billingAddress
     *
     * @return the value of m_crm_account.billingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getBillingaddress() {
        return billingaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.billingAddress
     *
     * @param billingaddress the value for m_crm_account.billingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setBillingaddress(String billingaddress) {
        this.billingaddress = billingaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.city
     *
     * @return the value of m_crm_account.city
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.city
     *
     * @param city the value for m_crm_account.city
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.postalCode
     *
     * @return the value of m_crm_account.postalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.postalCode
     *
     * @param postalcode the value for m_crm_account.postalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.state
     *
     * @return the value of m_crm_account.state
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.state
     *
     * @param state the value for m_crm_account.state
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.email
     *
     * @return the value of m_crm_account.email
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.email
     *
     * @param email the value for m_crm_account.email
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.ownership
     *
     * @return the value of m_crm_account.ownership
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.ownership
     *
     * @param ownership the value for m_crm_account.ownership
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.shippingAddress
     *
     * @return the value of m_crm_account.shippingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getShippingaddress() {
        return shippingaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.shippingAddress
     *
     * @param shippingaddress the value for m_crm_account.shippingAddress
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.shippingCity
     *
     * @return the value of m_crm_account.shippingCity
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getShippingcity() {
        return shippingcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.shippingCity
     *
     * @param shippingcity the value for m_crm_account.shippingCity
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setShippingcity(String shippingcity) {
        this.shippingcity = shippingcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.shippingPostalCode
     *
     * @return the value of m_crm_account.shippingPostalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getShippingpostalcode() {
        return shippingpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.shippingPostalCode
     *
     * @param shippingpostalcode the value for m_crm_account.shippingPostalCode
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setShippingpostalcode(String shippingpostalcode) {
        this.shippingpostalcode = shippingpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.shippingState
     *
     * @return the value of m_crm_account.shippingState
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getShippingstate() {
        return shippingstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.shippingState
     *
     * @param shippingstate the value for m_crm_account.shippingState
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setShippingstate(String shippingstate) {
        this.shippingstate = shippingstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.numemployees
     *
     * @return the value of m_crm_account.numemployees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public Integer getNumemployees() {
        return numemployees;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.numemployees
     *
     * @param numemployees the value for m_crm_account.numemployees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setNumemployees(Integer numemployees) {
        this.numemployees = numemployees;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.createdTime
     *
     * @return the value of m_crm_account.createdTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.createdTime
     *
     * @param createdtime the value for m_crm_account.createdTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.createdUser
     *
     * @return the value of m_crm_account.createdUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.createdUser
     *
     * @param createduser the value for m_crm_account.createdUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.sAccountId
     *
     * @return the value of m_crm_account.sAccountId
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.sAccountId
     *
     * @param saccountid the value for m_crm_account.sAccountId
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.assignUser
     *
     * @return the value of m_crm_account.assignUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.assignUser
     *
     * @param assignuser the value for m_crm_account.assignUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.type
     *
     * @return the value of m_crm_account.type
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.type
     *
     * @param type the value for m_crm_account.type
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.industry
     *
     * @return the value of m_crm_account.industry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.industry
     *
     * @param industry the value for m_crm_account.industry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.lastUpdatedTime
     *
     * @return the value of m_crm_account.lastUpdatedTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_account.lastUpdatedTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.billingCountry
     *
     * @return the value of m_crm_account.billingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getBillingcountry() {
        return billingcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.billingCountry
     *
     * @param billingcountry the value for m_crm_account.billingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setBillingcountry(String billingcountry) {
        this.billingcountry = billingcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.shippingCountry
     *
     * @return the value of m_crm_account.shippingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getShippingcountry() {
        return shippingcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.shippingCountry
     *
     * @param shippingcountry the value for m_crm_account.shippingCountry
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setShippingcountry(String shippingcountry) {
        this.shippingcountry = shippingcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.description
     *
     * @return the value of m_crm_account.description
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.description
     *
     * @param description the value for m_crm_account.description
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setDescription(String description) {
        this.description = description;
    }
}