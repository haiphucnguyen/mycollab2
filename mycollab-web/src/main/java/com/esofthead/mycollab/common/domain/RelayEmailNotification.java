/*Domain class of table s_relay_email_notification*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class RelayEmailNotification extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.id
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.sAccountId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.type
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.typeid
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.action
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.changeBy
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String changeby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=400, message="Field value is too long")
    private String emailhandlerbean;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.changeComment
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String changecomment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.id
     *
     * @return the value of s_relay_email_notification.id
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.id
     *
     * @param id the value for s_relay_email_notification.id
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.sAccountId
     *
     * @return the value of s_relay_email_notification.sAccountId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.sAccountId
     *
     * @param saccountid the value for s_relay_email_notification.sAccountId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.type
     *
     * @return the value of s_relay_email_notification.type
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.type
     *
     * @param type the value for s_relay_email_notification.type
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.typeid
     *
     * @return the value of s_relay_email_notification.typeid
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.typeid
     *
     * @param typeid the value for s_relay_email_notification.typeid
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.action
     *
     * @return the value of s_relay_email_notification.action
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.action
     *
     * @param action the value for s_relay_email_notification.action
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.changeBy
     *
     * @return the value of s_relay_email_notification.changeBy
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public String getChangeby() {
        return changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.changeBy
     *
     * @param changeby the value for s_relay_email_notification.changeBy
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setChangeby(String changeby) {
        this.changeby = changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.extraTypeId
     *
     * @return the value of s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.extraTypeId
     *
     * @param extratypeid the value for s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.emailHandlerBean
     *
     * @return the value of s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public String getEmailhandlerbean() {
        return emailhandlerbean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.emailHandlerBean
     *
     * @param emailhandlerbean the value for s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setEmailhandlerbean(String emailhandlerbean) {
        this.emailhandlerbean = emailhandlerbean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.changeComment
     *
     * @return the value of s_relay_email_notification.changeComment
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public String getChangecomment() {
        return changecomment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.changeComment
     *
     * @param changecomment the value for s_relay_email_notification.changeComment
     *
     * @mbggenerated Mon Jul 29 14:08:06 GMT+07:00 2013
     */
    public void setChangecomment(String changecomment) {
        this.changecomment = changecomment;
    }
}