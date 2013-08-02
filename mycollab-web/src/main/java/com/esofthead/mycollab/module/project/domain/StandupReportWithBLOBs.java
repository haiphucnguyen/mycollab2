package com.esofthead.mycollab.module.project.domain;

public class StandupReportWithBLOBs extends StandupReport {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whatlastday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String whatlastday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whattoday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String whattoday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.whatproblem
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String whatproblem;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whatlastday
     *
     * @return the value of m_prj_standup.whatlastday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public String getWhatlastday() {
        return whatlastday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whatlastday
     *
     * @param whatlastday the value for m_prj_standup.whatlastday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setWhatlastday(String whatlastday) {
        this.whatlastday = whatlastday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whattoday
     *
     * @return the value of m_prj_standup.whattoday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public String getWhattoday() {
        return whattoday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whattoday
     *
     * @param whattoday the value for m_prj_standup.whattoday
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setWhattoday(String whattoday) {
        this.whattoday = whattoday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.whatproblem
     *
     * @return the value of m_prj_standup.whatproblem
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public String getWhatproblem() {
        return whatproblem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.whatproblem
     *
     * @param whatproblem the value for m_prj_standup.whatproblem
     *
     * @mbggenerated Wed Jul 31 16:59:03 GMT+07:00 2013
     */
    public void setWhatproblem(String whatproblem) {
        this.whatproblem = whatproblem;
    }
}