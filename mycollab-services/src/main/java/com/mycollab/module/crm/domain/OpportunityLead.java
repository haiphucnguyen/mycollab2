/*Domain class of table m_crm_opportunities_leads*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("ucd")
@Table("m_crm_opportunities_leads")
public class OpportunityLead extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.id
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.opportunityId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    @Column("opportunityId")
    private Integer opportunityid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.leadId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    @Column("leadId")
    private Integer leadid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.createdTime
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_opportunities_leads.isConvertRel
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    @Column("isConvertRel")
    private Boolean isconvertrel;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        OpportunityLead item = (OpportunityLead)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1551, 293).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.id
     *
     * @return the value of m_crm_opportunities_leads.id
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.id
     *
     * @param id the value for m_crm_opportunities_leads.id
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.opportunityId
     *
     * @return the value of m_crm_opportunities_leads.opportunityId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.opportunityId
     *
     * @param opportunityid the value for m_crm_opportunities_leads.opportunityId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.leadId
     *
     * @return the value of m_crm_opportunities_leads.leadId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public Integer getLeadid() {
        return leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.leadId
     *
     * @param leadid the value for m_crm_opportunities_leads.leadId
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public void setLeadid(Integer leadid) {
        this.leadid = leadid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.createdTime
     *
     * @return the value of m_crm_opportunities_leads.createdTime
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.createdTime
     *
     * @param createdtime the value for m_crm_opportunities_leads.createdTime
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_opportunities_leads.isConvertRel
     *
     * @return the value of m_crm_opportunities_leads.isConvertRel
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public Boolean getIsconvertrel() {
        return isconvertrel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_opportunities_leads.isConvertRel
     *
     * @param isconvertrel the value for m_crm_opportunities_leads.isConvertRel
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    public void setIsconvertrel(Boolean isconvertrel) {
        this.isconvertrel = isconvertrel;
    }

    public enum Field {
        id,
        opportunityid,
        leadid,
        createdtime,
        isconvertrel;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}