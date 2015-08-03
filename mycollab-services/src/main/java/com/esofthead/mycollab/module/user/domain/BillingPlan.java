/*Domain class of table s_billing_plan*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_billing_plan")
public class BillingPlan extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.billingType
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("billingType")
    private String billingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numUsers
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("numUsers")
    private Integer numusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.volume
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("volume")
    private Long volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numProjects
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("numProjects")
    private Integer numprojects;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.pricing
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("pricing")
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.description
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=1000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasBugEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("hasBugEnable")
    private Boolean hasbugenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasStandupMeetingEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("hasStandupMeetingEnable")
    private Boolean hasstandupmeetingenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasTimeTracking
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("hasTimeTracking")
    private Boolean hastimetracking;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        BillingPlan item = (BillingPlan)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1349, 1571).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.id
     *
     * @return the value of s_billing_plan.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
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
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.hasBugEnable
     *
     * @return the value of s_billing_plan.hasBugEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Boolean getHasbugenable() {
        return hasbugenable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.hasBugEnable
     *
     * @param hasbugenable the value for s_billing_plan.hasBugEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setHasbugenable(Boolean hasbugenable) {
        this.hasbugenable = hasbugenable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.hasStandupMeetingEnable
     *
     * @return the value of s_billing_plan.hasStandupMeetingEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Boolean getHasstandupmeetingenable() {
        return hasstandupmeetingenable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.hasStandupMeetingEnable
     *
     * @param hasstandupmeetingenable the value for s_billing_plan.hasStandupMeetingEnable
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setHasstandupmeetingenable(Boolean hasstandupmeetingenable) {
        this.hasstandupmeetingenable = hasstandupmeetingenable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.hasTimeTracking
     *
     * @return the value of s_billing_plan.hasTimeTracking
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Boolean getHastimetracking() {
        return hastimetracking;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.hasTimeTracking
     *
     * @param hastimetracking the value for s_billing_plan.hasTimeTracking
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setHastimetracking(Boolean hastimetracking) {
        this.hastimetracking = hastimetracking;
    }

    public enum Field {
        id,
        billingtype,
        numusers,
        volume,
        numprojects,
        pricing,
        description,
        hasbugenable,
        hasstandupmeetingenable,
        hastimetracking;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}