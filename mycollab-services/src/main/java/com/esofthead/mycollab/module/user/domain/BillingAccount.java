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
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_account")
public class BillingAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.id
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.createdTime
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.billingPlanId
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("billingPlanId")
    private Integer billingplanid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.accountName
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("accountName")
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.status
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.paymentMethod
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("paymentMethod")
    private String paymentmethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricing
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("pricing")
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectFrom
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("pricingEffectFrom")
    private Date pricingeffectfrom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.pricingEffectTo
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Column("pricingEffectTo")
    private Date pricingeffectto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.subdomain
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("subdomain")
    private String subdomain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.reminderStatus
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("reminderStatus")
    private String reminderstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.sitename
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("sitename")
    private String sitename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.logoPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("logoPath")
    private String logopath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.defaultTimezone
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("defaultTimezone")
    private String defaulttimezone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account.faviconPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    @Length(max=225, message="Field value is too long")
    @Column("faviconPath")
    private String faviconpath;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        BillingAccount item = (BillingAccount)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1645, 1949).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.id
     *
     * @return the value of s_account.id
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
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
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public void setReminderstatus(String reminderstatus) {
        this.reminderstatus = reminderstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.sitename
     *
     * @return the value of s_account.sitename
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.sitename
     *
     * @param sitename the value for s_account.sitename
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.logoPath
     *
     * @return the value of s_account.logoPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public String getLogopath() {
        return logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.logoPath
     *
     * @param logopath the value for s_account.logoPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.defaultTimezone
     *
     * @return the value of s_account.defaultTimezone
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public String getDefaulttimezone() {
        return defaulttimezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.defaultTimezone
     *
     * @param defaulttimezone the value for s_account.defaultTimezone
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public void setDefaulttimezone(String defaulttimezone) {
        this.defaulttimezone = defaulttimezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account.faviconPath
     *
     * @return the value of s_account.faviconPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public String getFaviconpath() {
        return faviconpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account.faviconPath
     *
     * @param faviconpath the value for s_account.faviconPath
     *
     * @mbggenerated Mon Mar 21 16:11:39 ICT 2016
     */
    public void setFaviconpath(String faviconpath) {
        this.faviconpath = faviconpath;
    }

    public enum Field {
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
        reminderstatus,
        sitename,
        logopath,
        defaulttimezone,
        faviconpath;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}