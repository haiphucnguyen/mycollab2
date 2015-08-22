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
/*Domain class of table m_crm_campaign*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_campaign")
class Campaign extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.id
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.campaignName
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("campaignName")
    private String campaignname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.startDate
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("startDate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.endDate
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("endDate")
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.currencyId
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("currencyId")
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impressionnote
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("impressionnote")
    private String impressionnote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.budget
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("budget")
    private Long budget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.actualCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("actualCost")
    private Long actualcost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("expectedRevenue")
    private Long expectedrevenue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.expectedCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("expectedCost")
    private Long expectedcost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.impression
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("impression")
    private Integer impression;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdTime
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.createdUser
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.sAccountId
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.status
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.type
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.assignUser
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.lastUpdatedTime
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.avatarId
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Campaign item = (Campaign)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1085, 1573).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.id
     *
     * @return the value of m_crm_campaign.id
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.currencyId
     *
     * @param currencyid the value for m_crm_campaign.currencyId
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impressionnote
     *
     * @return the value of m_crm_campaign.impressionnote
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public String getImpressionnote() {
        return impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.impressionnote
     *
     * @param impressionnote the value for m_crm_campaign.impressionnote
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setImpressionnote(String impressionnote) {
        this.impressionnote = impressionnote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.budget
     *
     * @return the value of m_crm_campaign.budget
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public Long getBudget() {
        return budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.budget
     *
     * @param budget the value for m_crm_campaign.budget
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setBudget(Long budget) {
        this.budget = budget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.actualCost
     *
     * @return the value of m_crm_campaign.actualCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public Long getActualcost() {
        return actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.actualCost
     *
     * @param actualcost the value for m_crm_campaign.actualCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setActualcost(Long actualcost) {
        this.actualcost = actualcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedRevenue
     *
     * @return the value of m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public Long getExpectedrevenue() {
        return expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedRevenue
     *
     * @param expectedrevenue the value for m_crm_campaign.expectedRevenue
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setExpectedrevenue(Long expectedrevenue) {
        this.expectedrevenue = expectedrevenue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.expectedCost
     *
     * @return the value of m_crm_campaign.expectedCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public Long getExpectedcost() {
        return expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.expectedCost
     *
     * @param expectedcost the value for m_crm_campaign.expectedCost
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setExpectedcost(Long expectedcost) {
        this.expectedcost = expectedcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.impression
     *
     * @return the value of m_crm_campaign.impression
     *
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
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
     * @mbggenerated Fri Aug 21 10:43:48 ICT 2015
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }
}