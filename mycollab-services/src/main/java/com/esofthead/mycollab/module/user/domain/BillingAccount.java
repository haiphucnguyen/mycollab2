/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table s_account*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_account")
public class BillingAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.id
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.createdTime
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.billingPlanId
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("billingPlanId")
    private Integer billingplanid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.accountName
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("accountName")
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.status
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.paymentMethod
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("paymentMethod")
    private String paymentmethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricing
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("pricing")
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectFrom
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("pricingEffectFrom")
    private Date pricingeffectfrom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectTo
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("pricingEffectTo")
    private Date pricingeffectto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.subdomain
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("subdomain")
    private String subdomain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.reminderStatus
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("reminderStatus")
    private String reminderstatus;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.id
     *
     * @return the value of s_account.id
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
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
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.reminderStatus
     *
     * @return the value of s_account.reminderStatus
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    public String getReminderstatus() {
        return reminderstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.reminderStatus
     *
     * @param reminderstatus the value for s_account.reminderStatus
     *
     * @mbggenerated Wed Nov 05 10:16:31 ICT 2014
     */
    public void setReminderstatus(String reminderstatus) {
        this.reminderstatus = reminderstatus;
    }

    public static enum Field {
        id,
        createdtime,
        billingplanid,
        accountname,
        status,
        paymentmethod,
        pricing,
        pricingeffectfrom,
        pricingeffectto,
        subdomain,
        reminderstatus;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}