package com.esofthead.mycollab.module.ecm.domain;

public class ContentActivityLogWithBLOBs extends ContentActivityLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String actiondesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String basefolderpath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.actionDesc
     *
     * @return the value of m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    public String getActiondesc() {
        return actiondesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.actionDesc
     *
     * @param actiondesc the value for m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    public void setActiondesc(String actiondesc) {
        this.actiondesc = actiondesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.baseFolderPath
     *
     * @return the value of m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    public String getBasefolderpath() {
        return basefolderpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.baseFolderPath
     *
     * @param basefolderpath the value for m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    public void setBasefolderpath(String basefolderpath) {
        this.basefolderpath = basefolderpath;
    }
}