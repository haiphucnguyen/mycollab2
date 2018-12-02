/*Domain class of table m_crm_campaigns_contacts*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.campaignId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("campaignId")
    private Integer campaignid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.contactId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("contactId")
    private Integer contactid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_contacts.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        CampaignContact item = (CampaignContact)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(557, 1857).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_contacts.id
     *
     * @return the value of m_crm_campaigns_contacts.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
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
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_contacts.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_contacts.createdTime
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setCreatedtime(LocalDateTime createdtime) {
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