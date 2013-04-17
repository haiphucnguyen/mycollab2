package com.esofthead.mycollab.module.crm.domain;

public class CallWithBLOBs extends Call {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.description
     *
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_call.result
     *
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String result;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_call.description
     *
     * @return the value of m_crm_call.description
     *
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
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
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
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
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
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
     * @mbggenerated Wed Apr 17 15:12:09 GMT+07:00 2013
     */
    public void setResult(String result) {
        this.result = result;
    }
}