package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Lead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.id
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.campaignId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.leadSourceDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String leadsourcedesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.statusDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String statusdesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.referredBy
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String referredby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.prefixName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String prefixname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.firstname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.lastname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.accountName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.title
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.department
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.isCallable
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Boolean iscallable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.officePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String officephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.homePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.mobile
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherPhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String otherphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.fax
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.primAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String primaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.primState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String primstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.primCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String primcity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.primPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String primpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.primCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String primcountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.email
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.createdUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.assignUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.status
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.source
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.website
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String otheraddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String otherstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String othercity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String otherpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.otherCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String othercountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.industry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String industry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.noEmployees
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private Integer noemployees;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_lead.description
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.id
     *
     * @return the value of m_crm_lead.id
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.id
     *
     * @param id the value for m_crm_lead.id
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.campaignId
     *
     * @return the value of m_crm_lead.campaignId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.campaignId
     *
     * @param campaignid the value for m_crm_lead.campaignId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.leadSourceDesc
     *
     * @return the value of m_crm_lead.leadSourceDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getLeadsourcedesc() {
        return leadsourcedesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.leadSourceDesc
     *
     * @param leadsourcedesc the value for m_crm_lead.leadSourceDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setLeadsourcedesc(String leadsourcedesc) {
        this.leadsourcedesc = leadsourcedesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.statusDesc
     *
     * @return the value of m_crm_lead.statusDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getStatusdesc() {
        return statusdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.statusDesc
     *
     * @param statusdesc the value for m_crm_lead.statusDesc
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setStatusdesc(String statusdesc) {
        this.statusdesc = statusdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.referredBy
     *
     * @return the value of m_crm_lead.referredBy
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getReferredby() {
        return referredby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.referredBy
     *
     * @param referredby the value for m_crm_lead.referredBy
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setReferredby(String referredby) {
        this.referredby = referredby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.prefixName
     *
     * @return the value of m_crm_lead.prefixName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrefixname() {
        return prefixname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.prefixName
     *
     * @param prefixname the value for m_crm_lead.prefixName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrefixname(String prefixname) {
        this.prefixname = prefixname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.firstname
     *
     * @return the value of m_crm_lead.firstname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.firstname
     *
     * @param firstname the value for m_crm_lead.firstname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.lastname
     *
     * @return the value of m_crm_lead.lastname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.lastname
     *
     * @param lastname the value for m_crm_lead.lastname
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.accountName
     *
     * @return the value of m_crm_lead.accountName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.accountName
     *
     * @param accountname the value for m_crm_lead.accountName
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.title
     *
     * @return the value of m_crm_lead.title
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.title
     *
     * @param title the value for m_crm_lead.title
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.department
     *
     * @return the value of m_crm_lead.department
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.department
     *
     * @param department the value for m_crm_lead.department
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.isCallable
     *
     * @return the value of m_crm_lead.isCallable
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Boolean getIscallable() {
        return iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.isCallable
     *
     * @param iscallable the value for m_crm_lead.isCallable
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setIscallable(Boolean iscallable) {
        this.iscallable = iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.officePhone
     *
     * @return the value of m_crm_lead.officePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOfficephone() {
        return officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.officePhone
     *
     * @param officephone the value for m_crm_lead.officePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.homePhone
     *
     * @return the value of m_crm_lead.homePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.homePhone
     *
     * @param homephone the value for m_crm_lead.homePhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.mobile
     *
     * @return the value of m_crm_lead.mobile
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.mobile
     *
     * @param mobile the value for m_crm_lead.mobile
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherPhone
     *
     * @return the value of m_crm_lead.otherPhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherPhone
     *
     * @param otherphone the value for m_crm_lead.otherPhone
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.fax
     *
     * @return the value of m_crm_lead.fax
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.fax
     *
     * @param fax the value for m_crm_lead.fax
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.primAddress
     *
     * @return the value of m_crm_lead.primAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrimaddress() {
        return primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.primAddress
     *
     * @param primaddress the value for m_crm_lead.primAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrimaddress(String primaddress) {
        this.primaddress = primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.primState
     *
     * @return the value of m_crm_lead.primState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrimstate() {
        return primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.primState
     *
     * @param primstate the value for m_crm_lead.primState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrimstate(String primstate) {
        this.primstate = primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.primCity
     *
     * @return the value of m_crm_lead.primCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrimcity() {
        return primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.primCity
     *
     * @param primcity the value for m_crm_lead.primCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrimcity(String primcity) {
        this.primcity = primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.primPostalCode
     *
     * @return the value of m_crm_lead.primPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrimpostalcode() {
        return primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.primPostalCode
     *
     * @param primpostalcode the value for m_crm_lead.primPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrimpostalcode(String primpostalcode) {
        this.primpostalcode = primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.primCountry
     *
     * @return the value of m_crm_lead.primCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getPrimcountry() {
        return primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.primCountry
     *
     * @param primcountry the value for m_crm_lead.primCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setPrimcountry(String primcountry) {
        this.primcountry = primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.email
     *
     * @return the value of m_crm_lead.email
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.email
     *
     * @param email the value for m_crm_lead.email
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.createdTime
     *
     * @return the value of m_crm_lead.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.createdTime
     *
     * @param createdtime the value for m_crm_lead.createdTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.createdUser
     *
     * @return the value of m_crm_lead.createdUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.createdUser
     *
     * @param createduser the value for m_crm_lead.createdUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.sAccountId
     *
     * @return the value of m_crm_lead.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.sAccountId
     *
     * @param saccountid the value for m_crm_lead.sAccountId
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.assignUser
     *
     * @return the value of m_crm_lead.assignUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.assignUser
     *
     * @param assignuser the value for m_crm_lead.assignUser
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.status
     *
     * @return the value of m_crm_lead.status
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.status
     *
     * @param status the value for m_crm_lead.status
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.source
     *
     * @return the value of m_crm_lead.source
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.source
     *
     * @param source the value for m_crm_lead.source
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.website
     *
     * @return the value of m_crm_lead.website
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.website
     *
     * @param website the value for m_crm_lead.website
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherAddress
     *
     * @return the value of m_crm_lead.otherAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOtheraddress() {
        return otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherAddress
     *
     * @param otheraddress the value for m_crm_lead.otherAddress
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOtheraddress(String otheraddress) {
        this.otheraddress = otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherState
     *
     * @return the value of m_crm_lead.otherState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOtherstate() {
        return otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherState
     *
     * @param otherstate the value for m_crm_lead.otherState
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOtherstate(String otherstate) {
        this.otherstate = otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherCity
     *
     * @return the value of m_crm_lead.otherCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOthercity() {
        return othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherCity
     *
     * @param othercity the value for m_crm_lead.otherCity
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOthercity(String othercity) {
        this.othercity = othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherPostalCode
     *
     * @return the value of m_crm_lead.otherPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOtherpostalcode() {
        return otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherPostalCode
     *
     * @param otherpostalcode the value for m_crm_lead.otherPostalCode
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOtherpostalcode(String otherpostalcode) {
        this.otherpostalcode = otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.otherCountry
     *
     * @return the value of m_crm_lead.otherCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getOthercountry() {
        return othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.otherCountry
     *
     * @param othercountry the value for m_crm_lead.otherCountry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setOthercountry(String othercountry) {
        this.othercountry = othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.industry
     *
     * @return the value of m_crm_lead.industry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.industry
     *
     * @param industry the value for m_crm_lead.industry
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.lastUpdatedTime
     *
     * @return the value of m_crm_lead.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_lead.lastUpdatedTime
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.noEmployees
     *
     * @return the value of m_crm_lead.noEmployees
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public Integer getNoemployees() {
        return noemployees;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.noEmployees
     *
     * @param noemployees the value for m_crm_lead.noEmployees
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setNoemployees(Integer noemployees) {
        this.noemployees = noemployees;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_lead.description
     *
     * @return the value of m_crm_lead.description
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_lead.description
     *
     * @param description the value for m_crm_lead.description
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}