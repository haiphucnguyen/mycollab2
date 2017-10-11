/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_crm_campaigns_contacts*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;

@SuppressWarnings("ucd")
@Table("m_crm_campaigns_contacts")
@Alias("CampaignContact")
public class CampaignContact extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.campaignId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("campaignId")
    private Integer campaignid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.contactId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("contactId")
    private Integer contactid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        CampaignContact item = (CampaignContact)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(695, 1653).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.id
     *
     * @return the value of m_crm_campaigns_contacts.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.id
     *
     * @param id the value for m_crm_campaigns_contacts.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.campaignId
     *
     * @return the value of m_crm_campaigns_contacts.campaignId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_contacts.campaignId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.contactId
     *
     * @return the value of m_crm_campaigns_contacts.contactId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.contactId
     *
     * @param contactid the value for m_crm_campaigns_contacts.contactId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.createdTime
     *
     * @return the value of m_crm_campaigns_contacts.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_contacts.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public enum Field {
        id,
        campaignid,
        contactid,
        createdtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}