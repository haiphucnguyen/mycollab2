/*Domain class of table s_billing_plan*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_billing_plan")
@Alias("BillingPlan")
public class BillingPlan extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.id
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.billingType
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("billingType")
    private String billingtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numUsers
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Column("numUsers")
    private Integer numusers;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.volume
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Column("volume")
    private Long volume;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numProjects
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Column("numProjects")
    private Integer numprojects;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.pricing
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Column("pricing")
    private Double pricing;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.description
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=1000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.shoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("shoppingUrl")
    private String shoppingurl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.productPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("productPath")
    private String productpath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.bankTransferPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("bankTransferPath")
    private String banktransferpath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.yearlyShoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("yearlyShoppingUrl")
    private String yearlyshoppingurl;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        BillingPlan item = (BillingPlan)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(549, 1203).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.id
     *
     * @return the value of s_billing_plan.id
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.id
     *
     * @param id the value for s_billing_plan.id
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.billingType
     *
     * @return the value of s_billing_plan.billingType
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getBillingtype() {
        return billingtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.billingType
     *
     * @param billingtype the value for s_billing_plan.billingType
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setBillingtype(String billingtype) {
        this.billingtype = billingtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.numUsers
     *
     * @return the value of s_billing_plan.numUsers
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public Integer getNumusers() {
        return numusers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.numUsers
     *
     * @param numusers the value for s_billing_plan.numUsers
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setNumusers(Integer numusers) {
        this.numusers = numusers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.volume
     *
     * @return the value of s_billing_plan.volume
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public Long getVolume() {
        return volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.volume
     *
     * @param volume the value for s_billing_plan.volume
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.numProjects
     *
     * @return the value of s_billing_plan.numProjects
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public Integer getNumprojects() {
        return numprojects;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.numProjects
     *
     * @param numprojects the value for s_billing_plan.numProjects
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setNumprojects(Integer numprojects) {
        this.numprojects = numprojects;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.pricing
     *
     * @return the value of s_billing_plan.pricing
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public Double getPricing() {
        return pricing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.pricing
     *
     * @param pricing the value for s_billing_plan.pricing
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setPricing(Double pricing) {
        this.pricing = pricing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.description
     *
     * @return the value of s_billing_plan.description
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.description
     *
     * @param description the value for s_billing_plan.description
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.shoppingUrl
     *
     * @return the value of s_billing_plan.shoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getShoppingurl() {
        return shoppingurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.shoppingUrl
     *
     * @param shoppingurl the value for s_billing_plan.shoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setShoppingurl(String shoppingurl) {
        this.shoppingurl = shoppingurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.productPath
     *
     * @return the value of s_billing_plan.productPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getProductpath() {
        return productpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.productPath
     *
     * @param productpath the value for s_billing_plan.productPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setProductpath(String productpath) {
        this.productpath = productpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.bankTransferPath
     *
     * @return the value of s_billing_plan.bankTransferPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getBanktransferpath() {
        return banktransferpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.bankTransferPath
     *
     * @param banktransferpath the value for s_billing_plan.bankTransferPath
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setBanktransferpath(String banktransferpath) {
        this.banktransferpath = banktransferpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.yearlyShoppingUrl
     *
     * @return the value of s_billing_plan.yearlyShoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public String getYearlyshoppingurl() {
        return yearlyshoppingurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.yearlyShoppingUrl
     *
     * @param yearlyshoppingurl the value for s_billing_plan.yearlyShoppingUrl
     *
     * @mbg.generated Fri Nov 30 08:06:39 CST 2018
     */
    public void setYearlyshoppingurl(String yearlyshoppingurl) {
        this.yearlyshoppingurl = yearlyshoppingurl;
    }

    public enum Field {
        id,
        billingtype,
        numusers,
        volume,
        numprojects,
        pricing,
        description,
        shoppingurl,
        productpath,
        banktransferpath,
        yearlyshoppingurl;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}