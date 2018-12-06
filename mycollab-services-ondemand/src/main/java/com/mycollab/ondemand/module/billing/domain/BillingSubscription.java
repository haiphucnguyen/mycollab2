/*Domain class of table s_billing_subscription*/
package com.mycollab.ondemand.module.billing.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_billing_subscription")
@Alias("BillingSubscription")
public class BillingSubscription extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.id
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.company
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("company")
    private String company;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.email
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.billingId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Column("billingId")
    private Integer billingid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.name
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.subReference
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("subReference")
    private String subreference;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.accountId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Column("accountId")
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.createdTime
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.status
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.country
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("country")
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.city
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("city")
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.address
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("address")
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.state
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("state")
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.zipcode
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("zipcode")
    private String zipcode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.phone
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("phone")
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.contactName
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("contactName")
    private String contactname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_subscription.subscriptionCustomerUrl
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("subscriptionCustomerUrl")
    private String subscriptioncustomerurl;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        BillingSubscription item = (BillingSubscription)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(687, 155).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.id
     *
     * @return the value of s_billing_subscription.id
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.id
     *
     * @param id the value for s_billing_subscription.id
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.company
     *
     * @return the value of s_billing_subscription.company
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.company
     *
     * @param company the value for s_billing_subscription.company
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.email
     *
     * @return the value of s_billing_subscription.email
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.email
     *
     * @param email the value for s_billing_subscription.email
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.billingId
     *
     * @return the value of s_billing_subscription.billingId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public Integer getBillingid() {
        return billingid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.billingId
     *
     * @param billingid the value for s_billing_subscription.billingId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setBillingid(Integer billingid) {
        this.billingid = billingid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.name
     *
     * @return the value of s_billing_subscription.name
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.name
     *
     * @param name the value for s_billing_subscription.name
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.subReference
     *
     * @return the value of s_billing_subscription.subReference
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getSubreference() {
        return subreference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.subReference
     *
     * @param subreference the value for s_billing_subscription.subReference
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setSubreference(String subreference) {
        this.subreference = subreference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.accountId
     *
     * @return the value of s_billing_subscription.accountId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.accountId
     *
     * @param accountid the value for s_billing_subscription.accountId
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.createdTime
     *
     * @return the value of s_billing_subscription.createdTime
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.createdTime
     *
     * @param createdtime the value for s_billing_subscription.createdTime
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.status
     *
     * @return the value of s_billing_subscription.status
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.status
     *
     * @param status the value for s_billing_subscription.status
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.country
     *
     * @return the value of s_billing_subscription.country
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.country
     *
     * @param country the value for s_billing_subscription.country
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.city
     *
     * @return the value of s_billing_subscription.city
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.city
     *
     * @param city the value for s_billing_subscription.city
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.address
     *
     * @return the value of s_billing_subscription.address
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.address
     *
     * @param address the value for s_billing_subscription.address
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.state
     *
     * @return the value of s_billing_subscription.state
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.state
     *
     * @param state the value for s_billing_subscription.state
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.zipcode
     *
     * @return the value of s_billing_subscription.zipcode
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.zipcode
     *
     * @param zipcode the value for s_billing_subscription.zipcode
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.phone
     *
     * @return the value of s_billing_subscription.phone
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.phone
     *
     * @param phone the value for s_billing_subscription.phone
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.contactName
     *
     * @return the value of s_billing_subscription.contactName
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getContactname() {
        return contactname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.contactName
     *
     * @param contactname the value for s_billing_subscription.contactName
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_subscription.subscriptionCustomerUrl
     *
     * @return the value of s_billing_subscription.subscriptionCustomerUrl
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public String getSubscriptioncustomerurl() {
        return subscriptioncustomerurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_subscription.subscriptionCustomerUrl
     *
     * @param subscriptioncustomerurl the value for s_billing_subscription.subscriptionCustomerUrl
     *
     * @mbg.generated Thu Dec 06 14:29:49 CST 2018
     */
    public void setSubscriptioncustomerurl(String subscriptioncustomerurl) {
        this.subscriptioncustomerurl = subscriptioncustomerurl;
    }

    public enum Field {
        id,
        company,
        email,
        billingid,
        name,
        subreference,
        accountid,
        createdtime,
        status,
        country,
        city,
        address,
        state,
        zipcode,
        phone,
        contactname,
        subscriptioncustomerurl;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}