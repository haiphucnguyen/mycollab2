package com.esofthead.mycollab.module.crm.domain;

public class CaseWithBLOBs extends Case {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.description
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.resolution
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String resolution;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.description
     *
     * @return the value of m_crm_case.description
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.description
     *
     * @param description the value for m_crm_case.description
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.resolution
     *
     * @return the value of m_crm_case.resolution
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_case.resolution
     *
     * @param resolution the value for m_crm_case.resolution
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}