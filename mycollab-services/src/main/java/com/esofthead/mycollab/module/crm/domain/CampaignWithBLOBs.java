package com.esofthead.mycollab.module.crm.domain;

public class CampaignWithBLOBs extends Campaign {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.objective
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String objective;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_campaign.description
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_campaign.objective
     *
     * @return the value of m_crm_campaign.objective
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
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
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
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
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
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
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    public void setDescription(String description) {
        this.description = description;
    }
}