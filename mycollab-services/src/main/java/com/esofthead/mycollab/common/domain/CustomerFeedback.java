/*Domain class of table s_customer_feedback*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_customer_feedback")
class CustomerFeedback extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.id
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.username
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.leaveType
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("leaveType")
    private Integer leavetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.otherTool
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=400, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("otherTool")
    private String othertool;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.id
     *
     * @return the value of s_customer_feedback.id
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.id
     *
     * @param id the value for s_customer_feedback.id
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.sAccountId
     *
     * @return the value of s_customer_feedback.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.sAccountId
     *
     * @param saccountid the value for s_customer_feedback.sAccountId
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.username
     *
     * @return the value of s_customer_feedback.username
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.username
     *
     * @param username the value for s_customer_feedback.username
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.leaveType
     *
     * @return the value of s_customer_feedback.leaveType
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public Integer getLeavetype() {
        return leavetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.leaveType
     *
     * @param leavetype the value for s_customer_feedback.leaveType
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setLeavetype(Integer leavetype) {
        this.leavetype = leavetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.otherTool
     *
     * @return the value of s_customer_feedback.otherTool
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public String getOthertool() {
        return othertool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.otherTool
     *
     * @param othertool the value for s_customer_feedback.otherTool
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setOthertool(String othertool) {
        this.othertool = othertool;
    }
}