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
/*Domain class of table m_crm_campaigns_accounts*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_campaigns_accounts")
public class CampaignAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.id
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    @Column("campaignId")
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    @Column("accountId")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        CampaignAccount item = (CampaignAccount)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(883, 1795).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.id
     *
     * @return the value of m_crm_campaigns_accounts.id
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.id
     *
     * @param id the value for m_crm_campaigns_accounts.id
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.campaignId
     *
     * @return the value of m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_accounts.campaignId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.accountId
     *
     * @return the value of m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.accountId
     *
     * @param accountid the value for m_crm_campaigns_accounts.accountId
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_accounts.createdTime
     *
     * @return the value of m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_accounts.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_accounts.createdTime
     *
     * @mbggenerated Wed Mar 16 18:49:58 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public enum Field {
        id,
        campaignid,
        accountid,
        createdtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}