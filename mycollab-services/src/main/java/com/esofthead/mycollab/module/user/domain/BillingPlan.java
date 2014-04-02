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
/*Domain class of table s_billing_plan*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@com.esofthead.mycollab.core.db.metadata.Table("s_billing_plan")
public class BillingPlan extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.id
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.billingType
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("billingType")
    private String billingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numUsers
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("numUsers")
    private Integer numusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.volume
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("volume")
    private Long volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.numProjects
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("numProjects")
    private Integer numprojects;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.pricing
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("pricing")
    private Double pricing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.description
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=1000, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasBugEnable
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("hasBugEnable")
    private Boolean hasbugenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasStandupMeetingEnable
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("hasStandupMeetingEnable")
    private Boolean hasstandupmeetingenable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_billing_plan.hasTimeTracking
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("hasTimeTracking")
    private Boolean hastimetracking;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_billing_plan.id
     *
     * @return the value of s_billing_plan.id
     *
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
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
     * @mbggenerated Sun Feb 09 16:15:21 ICT 2014
     */
    public void setHastimetracking(Boolean hastimetracking) {
        this.hastimetracking = hastimetracking;
    }
}