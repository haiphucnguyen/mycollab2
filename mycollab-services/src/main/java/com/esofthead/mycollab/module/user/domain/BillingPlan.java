/*Domain class of table s_billing_plan*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class BillingPlan extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.id
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.billingType
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String billingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numUsers
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Integer numusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.volume
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Long volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numProjects
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Integer numprojects;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.pricing
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.description
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=1000, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasBugEnable
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Boolean hasbugenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasStandupMeetingEnable
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Boolean hasstandupmeetingenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasTimeTracking
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    private Boolean hastimetracking;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.id
     *
     * @return the value of s_billing_plan.id
     *
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
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
     * @mbggenerated Thu Oct 24 13:55:36 ICT 2013
     */
    public void setHastimetracking(Boolean hastimetracking) {
        this.hastimetracking = hastimetracking;
    }
}