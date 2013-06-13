/*Domain class of table m_crm_contact*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Contact extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.prefix
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String prefix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.firstname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.lastname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.leadSource
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String leadsource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.campaignId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.isCallable
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Boolean iscallable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.officePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String officephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.mobile
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.homePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String otherphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.fax
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.birthday
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assistant
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String assistant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String primaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String primcity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String primstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String primpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String primcountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.title
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String assistantphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.email
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.department
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assignUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String otheraddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String othercity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String otherstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String otherpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String othercountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.description
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.id
     *
     * @return the value of m_crm_contact.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.id
     *
     * @param id the value for m_crm_contact.id
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.prefix
     *
     * @return the value of m_crm_contact.prefix
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.prefix
     *
     * @param prefix the value for m_crm_contact.prefix
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.firstname
     *
     * @return the value of m_crm_contact.firstname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.firstname
     *
     * @param firstname the value for m_crm_contact.firstname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.lastname
     *
     * @return the value of m_crm_contact.lastname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.lastname
     *
     * @param lastname the value for m_crm_contact.lastname
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.leadSource
     *
     * @return the value of m_crm_contact.leadSource
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getLeadsource() {
        return leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.leadSource
     *
     * @param leadsource the value for m_crm_contact.leadSource
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setLeadsource(String leadsource) {
        this.leadsource = leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.campaignId
     *
     * @return the value of m_crm_contact.campaignId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.campaignId
     *
     * @param campaignid the value for m_crm_contact.campaignId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.isCallable
     *
     * @return the value of m_crm_contact.isCallable
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Boolean getIscallable() {
        return iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.isCallable
     *
     * @param iscallable the value for m_crm_contact.isCallable
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setIscallable(Boolean iscallable) {
        this.iscallable = iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.officePhone
     *
     * @return the value of m_crm_contact.officePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOfficephone() {
        return officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.officePhone
     *
     * @param officephone the value for m_crm_contact.officePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.mobile
     *
     * @return the value of m_crm_contact.mobile
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.mobile
     *
     * @param mobile the value for m_crm_contact.mobile
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.homePhone
     *
     * @return the value of m_crm_contact.homePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.homePhone
     *
     * @param homephone the value for m_crm_contact.homePhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherPhone
     *
     * @return the value of m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherPhone
     *
     * @param otherphone the value for m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.fax
     *
     * @return the value of m_crm_contact.fax
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.fax
     *
     * @param fax the value for m_crm_contact.fax
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.birthday
     *
     * @return the value of m_crm_contact.birthday
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.birthday
     *
     * @param birthday the value for m_crm_contact.birthday
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assistant
     *
     * @return the value of m_crm_contact.assistant
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getAssistant() {
        return assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assistant
     *
     * @param assistant the value for m_crm_contact.assistant
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primAddress
     *
     * @return the value of m_crm_contact.primAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrimaddress() {
        return primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primAddress
     *
     * @param primaddress the value for m_crm_contact.primAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrimaddress(String primaddress) {
        this.primaddress = primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primCity
     *
     * @return the value of m_crm_contact.primCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrimcity() {
        return primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primCity
     *
     * @param primcity the value for m_crm_contact.primCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrimcity(String primcity) {
        this.primcity = primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primState
     *
     * @return the value of m_crm_contact.primState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrimstate() {
        return primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primState
     *
     * @param primstate the value for m_crm_contact.primState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrimstate(String primstate) {
        this.primstate = primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primPostalCode
     *
     * @return the value of m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrimpostalcode() {
        return primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primPostalCode
     *
     * @param primpostalcode the value for m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrimpostalcode(String primpostalcode) {
        this.primpostalcode = primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primCountry
     *
     * @return the value of m_crm_contact.primCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getPrimcountry() {
        return primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primCountry
     *
     * @param primcountry the value for m_crm_contact.primCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setPrimcountry(String primcountry) {
        this.primcountry = primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.title
     *
     * @return the value of m_crm_contact.title
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.title
     *
     * @param title the value for m_crm_contact.title
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assistantPhone
     *
     * @return the value of m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getAssistantphone() {
        return assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assistantPhone
     *
     * @param assistantphone the value for m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setAssistantphone(String assistantphone) {
        this.assistantphone = assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.email
     *
     * @return the value of m_crm_contact.email
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.email
     *
     * @param email the value for m_crm_contact.email
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.department
     *
     * @return the value of m_crm_contact.department
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.department
     *
     * @param department the value for m_crm_contact.department
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.createdTime
     *
     * @return the value of m_crm_contact.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.createdTime
     *
     * @param createdtime the value for m_crm_contact.createdTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.createdUser
     *
     * @return the value of m_crm_contact.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.createdUser
     *
     * @param createduser the value for m_crm_contact.createdUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.sAccountId
     *
     * @return the value of m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.sAccountId
     *
     * @param saccountid the value for m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assignUser
     *
     * @return the value of m_crm_contact.assignUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assignUser
     *
     * @param assignuser the value for m_crm_contact.assignUser
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherAddress
     *
     * @return the value of m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOtheraddress() {
        return otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherAddress
     *
     * @param otheraddress the value for m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOtheraddress(String otheraddress) {
        this.otheraddress = otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherCity
     *
     * @return the value of m_crm_contact.otherCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOthercity() {
        return othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherCity
     *
     * @param othercity the value for m_crm_contact.otherCity
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOthercity(String othercity) {
        this.othercity = othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherState
     *
     * @return the value of m_crm_contact.otherState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOtherstate() {
        return otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherState
     *
     * @param otherstate the value for m_crm_contact.otherState
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOtherstate(String otherstate) {
        this.otherstate = otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherPostalCode
     *
     * @return the value of m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOtherpostalcode() {
        return otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherPostalCode
     *
     * @param otherpostalcode the value for m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOtherpostalcode(String otherpostalcode) {
        this.otherpostalcode = otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherCountry
     *
     * @return the value of m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getOthercountry() {
        return othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherCountry
     *
     * @param othercountry the value for m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setOthercountry(String othercountry) {
        this.othercountry = othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.lastUpdatedTime
     *
     * @return the value of m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.description
     *
     * @return the value of m_crm_contact.description
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.description
     *
     * @param description the value for m_crm_contact.description
     *
     * @mbggenerated Thu Jun 13 13:54:09 GMT+07:00 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }
}