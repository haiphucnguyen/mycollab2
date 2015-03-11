package com.esofthead.mycollab.module.crm.domain;

@SuppressWarnings("ucd")
public class CampaignWithBLOBs extends Campaign {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.objective
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("objective")
    private String objective;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.description
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.objective
     *
     * @return the value of m_crm_campaign.objective
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    public String getObjective() {
        return objective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.objective
     *
     * @param objective the value for m_crm_campaign.objective
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.description
     *
     * @return the value of m_crm_campaign.description
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_campaign.description
     *
     * @param description the value for m_crm_campaign.description
     *
     * @mbggenerated Wed Mar 11 09:10:39 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public static enum Field {
        id,
        campaignname,
        startdate,
        enddate,
        currencyid,
        impressionnote,
        budget,
        actualcost,
        expectedrevenue,
        expectedcost,
        impression,
        createdtime,
        createduser,
        saccountid,
        status,
        type,
        assignuser,
        lastupdatedtime,
        avatarid,
        objective,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}