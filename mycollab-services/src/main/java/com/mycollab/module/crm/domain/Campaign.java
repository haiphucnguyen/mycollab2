/*Domain class of table m_crm_campaign*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_campaign")
class Campaign extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.id
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.campaignName
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("campaignName")
    private String campaignname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.startDate
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("startDate")
    private Date startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.endDate
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("endDate")
    private Date enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.currencyId
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=4, message="Field value is too long")
    @Column("currencyId")
    private String currencyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.budget
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("budget")
    private Double budget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.actualCost
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("actualCost")
    private Double actualcost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedRevenue
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("expectedRevenue")
    private Double expectedrevenue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedCost
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("expectedCost")
    private Double expectedcost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impression
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("impression")
    private Integer impression;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdTime
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdUser
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.sAccountId
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.status
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.type
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.assignUser
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.lastUpdatedTime
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.avatarId
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
        return new HashCodeBuilder(989, 1801).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.id
     *
     * @return the value of m_crm_campaign.id
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.startDate
     *
     * @param startdate the value for m_crm_campaign.startDate
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.endDate
     *
     * @return the value of m_crm_campaign.endDate
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.endDate
     *
     * @param enddate the value for m_crm_campaign.endDate
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.currencyId
     *
     * @return the value of m_crm_campaign.currencyId
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.createdTime
     *
     * @param createdtime the value for m_crm_campaign.createdTime
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.createdUser
     *
     * @return the value of m_crm_campaign.createdUser
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_campaign.lastUpdatedTime
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.avatarId
     *
     * @return the value of m_crm_campaign.avatarId
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }
}