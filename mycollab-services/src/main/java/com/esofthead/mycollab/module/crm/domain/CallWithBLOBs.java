package com.esofthead.mycollab.module.crm.domain;

@SuppressWarnings("ucd")
public class CallWithBLOBs extends Call {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.description
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.result
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("result")
    private String result;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.description
     *
     * @return the value of m_crm_call.description
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.description
     *
     * @param description the value for m_crm_call.description
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.result
     *
     * @return the value of m_crm_call.result
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_call.result
     *
     * @param result the value for m_crm_call.result
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    public void setResult(String result) {
        this.result = result;
    }

    public static enum Field {
        id,
        subject,
        startdate,
        durationinseconds,
        calltype,
        type,
        typeid,
        lastupdatedtime,
        createdtime,
        createduser,
        assignuser,
        contactid,
        saccountid,
        status,
        purpose,
        isclosed,
        description,
        result;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}