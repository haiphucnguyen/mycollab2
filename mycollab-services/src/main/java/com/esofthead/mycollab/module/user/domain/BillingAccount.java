/*Domain class of table s_account*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class BillingAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.billingPlanId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Integer billingplanid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.accountName
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.status
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.paymentMethod
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String paymentmethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricing
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectFrom
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Date pricingeffectfrom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectTo
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Date pricingeffectto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.subdomain
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String subdomain;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.id
     *
     * @return the value of s_account.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.id
     *
     * @param id the value for s_account.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.createdTime
     *
     * @return the value of s_account.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.createdTime
     *
     * @param createdtime the value for s_account.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.billingPlanId
     *
     * @return the value of s_account.billingPlanId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Integer getBillingplanid() {
        return billingplanid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.billingPlanId
     *
     * @param billingplanid the value for s_account.billingPlanId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setBillingplanid(Integer billingplanid) {
        this.billingplanid = billingplanid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.accountName
     *
     * @return the value of s_account.accountName
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.accountName
     *
     * @param accountname the value for s_account.accountName
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.status
     *
     * @return the value of s_account.status
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.status
     *
     * @param status the value for s_account.status
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.paymentMethod
     *
     * @return the value of s_account.paymentMethod
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getPaymentmethod() {
        return paymentmethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.paymentMethod
     *
     * @param paymentmethod the value for s_account.paymentMethod
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.pricing
     *
     * @return the value of s_account.pricing
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Double getPricing() {
        return pricing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.pricing
     *
     * @param pricing the value for s_account.pricing
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.pricingEffectFrom
     *
     * @return the value of s_account.pricingEffectFrom
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Date getPricingeffectfrom() {
        return pricingeffectfrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.pricingEffectFrom
     *
     * @param pricingeffectfrom the value for s_account.pricingEffectFrom
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setPricingeffectfrom(Date pricingeffectfrom) {
        this.pricingeffectfrom = pricingeffectfrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.pricingEffectTo
     *
     * @return the value of s_account.pricingEffectTo
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Date getPricingeffectto() {
        return pricingeffectto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.pricingEffectTo
     *
     * @param pricingeffectto the value for s_account.pricingEffectTo
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setPricingeffectto(Date pricingeffectto) {
        this.pricingeffectto = pricingeffectto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.subdomain
     *
     * @return the value of s_account.subdomain
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getSubdomain() {
        return subdomain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.subdomain
     *
     * @param subdomain the value for s_account.subdomain
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }
}