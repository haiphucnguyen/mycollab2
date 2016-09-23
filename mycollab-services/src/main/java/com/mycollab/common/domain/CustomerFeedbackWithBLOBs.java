package com.mycollab.common.domain;

import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class CustomerFeedbackWithBLOBs extends CustomerFeedback {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToLeave
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("reasonToLeave")
    private String reasontoleave;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.reasonToBack
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("reasonToBack")
    private String reasontoback;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.reasonToLeave
     *
     * @return the value of s_customer_feedback.reasonToLeave
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public void setReasontoback(String reasontoback) {
        this.reasontoback = reasontoback;
    }

    public enum Field {
        id,
        saccountid,
        username,
        leavetype,
        othertool,
        reasontoleave,
        reasontoback;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}