/*Domain class of table m_crm_account*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_account")
public class Account extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.id
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.accountName
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("accountName")
    private String accountname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.website
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("website")
    private String website;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.phoneOffice
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("phoneOffice")
    private String phoneoffice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.fax
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("fax")
    private String fax;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.alternatePhone
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("alternatePhone")
    private String alternatephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.annualRevenue
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("annualRevenue")
    private String annualrevenue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.billingAddress
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("billingAddress")
    private String billingaddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.city
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("city")
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.postalCode
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("postalCode")
    private String postalcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.state
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("state")
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.email
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.ownership
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("ownership")
    private String ownership;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingAddress
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("shippingAddress")
    private String shippingaddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingCity
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("shippingCity")
    private String shippingcity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingPostalCode
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("shippingPostalCode")
    private String shippingpostalcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingState
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("shippingState")
    private String shippingstate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.numemployees
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Column("numemployees")
    private Integer numemployees;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.createdTime
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.createdUser
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.sAccountId
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.assignUser
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.type
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.industry
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("industry")
    private String industry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.lastUpdatedTime
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.billingCountry
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("billingCountry")
    private String billingcountry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.shippingCountry
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("shippingCountry")
    private String shippingcountry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.avatarId
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_account.description
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Account item = (Account)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1195, 825).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.id
     *
     * @return the value of m_crm_account.id
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    public void setShippingcountry(String shippingcountry) {
        this.shippingcountry = shippingcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.avatarId
     *
     * @return the value of m_crm_account.avatarId
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_account.avatarId
     *
     * @param avatarid the value for m_crm_account.avatarId
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_account.description
     *
     * @return the value of m_crm_account.description
     *
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
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
     * @mbg.generated Sat Oct 01 11:44:29 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        accountname,
        website,
        phoneoffice,
        fax,
        alternatephone,
        annualrevenue,
        billingaddress,
        city,
        postalcode,
        state,
        email,
        ownership,
        shippingaddress,
        shippingcity,
        shippingpostalcode,
        shippingstate,
        numemployees,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        type,
        industry,
        lastupdatedtime,
        billingcountry,
        shippingcountry,
        avatarid,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}