package com.esofthead.mycollab.module.crm.domain;

@SuppressWarnings("ucd")
public class CaseWithBLOBs extends Case {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.description
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_case.resolution
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("resolution")
    private String resolution;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_case.description
     *
     * @return the value of m_crm_case.description
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
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
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
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
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
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
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public static enum Field {
        id,
        priority,
        status,
        type,
        subject,
        accountid,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        lastupdatedtime,
        reason,
        origin,
        email,
        phonenumber,
        description,
        resolution;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}