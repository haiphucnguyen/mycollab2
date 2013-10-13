package com.esofthead.mycollab.common.domain;

public class CustomerFeedbackWithBLOBs extends CustomerFeedback {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String reasontoleave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String reasontoback;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.reasonToLeave
     *
     * @return the value of s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getReasontoleave() {
        return reasontoleave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.reasonToLeave
     *
     * @param reasontoleave the value for s_customer_feedback.reasonToLeave
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setReasontoleave(String reasontoleave) {
        this.reasontoleave = reasontoleave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.reasonToBack
     *
     * @return the value of s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getReasontoback() {
        return reasontoback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.reasonToBack
     *
     * @param reasontoback the value for s_customer_feedback.reasonToBack
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setReasontoback(String reasontoback) {
        this.reasontoback = reasontoback;
    }
}