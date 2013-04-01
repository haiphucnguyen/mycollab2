package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class BillingPlan extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.id
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.billingType
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    private String billingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numUsers
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    private Integer numusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.volume
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    private Integer volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numProjects
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    private Integer numprojects;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.id
     *
     * @return the value of s_billing_plan.id
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_billing_plan.volume
     *
     * @param volume the value for s_billing_plan.volume
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.numProjects
     *
     * @return the value of s_billing_plan.numProjects
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
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
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    public void setNumprojects(Integer numprojects) {
        this.numprojects = numprojects;
    }
}