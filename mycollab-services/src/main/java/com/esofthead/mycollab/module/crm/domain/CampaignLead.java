/*Domain class of table m_crm_campaigns_leads*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_campaigns_leads")
public class CampaignLead extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.id
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    @Column("campaignId")
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    @Column("leadId")
    private Integer leadid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        CampaignLead item = (CampaignLead)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1231, 1875).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.id
     *
     * @return the value of m_crm_campaigns_leads.id
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.id
     *
     * @param id the value for m_crm_campaigns_leads.id
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.campaignId
     *
     * @return the value of m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.campaignId
     *
     * @param campaignid the value for m_crm_campaigns_leads.campaignId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.leadId
     *
     * @return the value of m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.leadId
     *
     * @param leadid the value for m_crm_campaigns_leads.leadId
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaigns_leads.createdTime
     *
     * @return the value of m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaigns_leads.createdTime
     *
     * @param createdtime the value for m_crm_campaigns_leads.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public enum Field {
        id,
        campaignid,
        leadid,
        createdtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}