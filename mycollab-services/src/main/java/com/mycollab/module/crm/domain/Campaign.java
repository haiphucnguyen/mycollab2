/*Domain class of table m_crm_campaign*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_campaign")
@Alias("Campaign")
class Campaign extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.campaignName
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=255, message="Field value is too long")
    @Column("campaignName")
    private String campaignname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.startDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("startDate")
    private LocalDateTime startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.endDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("endDate")
    private LocalDateTime enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.currencyId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=4, message="Field value is too long")
    @Column("currencyId")
    private String currencyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.budget
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("budget")
    private Double budget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.actualCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("actualCost")
    private Double actualcost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedRevenue
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("expectedRevenue")
    private Double expectedrevenue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("expectedCost")
    private Double expectedcost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impression
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("impression")
    private Integer impression;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.sAccountId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.type
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.assignUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.lastUpdatedTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.avatarId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Campaign item = (Campaign)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(55, 909).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.id
     *
     * @return the value of m_crm_campaign.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.id
     *
     * @param id the value for m_crm_campaign.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.campaignName
     *
     * @return the value of m_crm_campaign.campaignName
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getCampaignname() {
        return campaignname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.campaignName
     *
     * @param campaignname the value for m_crm_campaign.campaignName
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCampaignname(String campaignname) {
        this.campaignname = campaignname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.startDate
     *
     * @return the value of m_crm_campaign.startDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.startDate
     *
     * @param startdate the value for m_crm_campaign.startDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.endDate
     *
     * @return the value of m_crm_campaign.endDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.endDate
     *
     * @param enddate the value for m_crm_campaign.endDate
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setEnddate(LocalDateTime enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.currencyId
     *
     * @return the value of m_crm_campaign.currencyId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.currencyId
     *
     * @param currencyid the value for m_crm_campaign.currencyId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.budget
     *
     * @return the value of m_crm_campaign.budget
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Double getBudget() {
        return budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.budget
     *
     * @param budget the value for m_crm_campaign.budget
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.actualCost
     *
     * @return the value of m_crm_campaign.actualCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Double getActualcost() {
        return actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.actualCost
     *
     * @param actualcost the value for m_crm_campaign.actualCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setActualcost(Double actualcost) {
        this.actualcost = actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedRevenue
     *
     * @return the value of m_crm_campaign.expectedRevenue
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Double getExpectedrevenue() {
        return expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedRevenue
     *
     * @param expectedrevenue the value for m_crm_campaign.expectedRevenue
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setExpectedrevenue(Double expectedrevenue) {
        this.expectedrevenue = expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedCost
     *
     * @return the value of m_crm_campaign.expectedCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Double getExpectedcost() {
        return expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedCost
     *
     * @param expectedcost the value for m_crm_campaign.expectedCost
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setExpectedcost(Double expectedcost) {
        this.expectedcost = expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impression
     *
     * @return the value of m_crm_campaign.impression
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Integer getImpression() {
        return impression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.impression
     *
     * @param impression the value for m_crm_campaign.impression
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.createdTime
     *
     * @return the value of m_crm_campaign.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.createdTime
     *
     * @param createdtime the value for m_crm_campaign.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.createdUser
     *
     * @return the value of m_crm_campaign.createdUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.createdUser
     *
     * @param createduser the value for m_crm_campaign.createdUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.sAccountId
     *
     * @return the value of m_crm_campaign.sAccountId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.sAccountId
     *
     * @param saccountid the value for m_crm_campaign.sAccountId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.status
     *
     * @return the value of m_crm_campaign.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.status
     *
     * @param status the value for m_crm_campaign.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.type
     *
     * @return the value of m_crm_campaign.type
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.type
     *
     * @param type the value for m_crm_campaign.type
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.assignUser
     *
     * @return the value of m_crm_campaign.assignUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.assignUser
     *
     * @param assignuser the value for m_crm_campaign.assignUser
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.lastUpdatedTime
     *
     * @return the value of m_crm_campaign.lastUpdatedTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_campaign.lastUpdatedTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.avatarId
     *
     * @return the value of m_crm_campaign.avatarId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.avatarId
     *
     * @param avatarid the value for m_crm_campaign.avatarId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }
}