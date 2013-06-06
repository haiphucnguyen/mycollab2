/*Domain class of table s_relay_mail*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

class RelayEmail extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.id
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromName
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String fromname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromEmail
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String fromemail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.id
     *
     * @return the value of s_relay_mail.id
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.id
     *
     * @param id the value for s_relay_mail.id
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.sAccountId
     *
     * @return the value of s_relay_mail.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.sAccountId
     *
     * @param saccountid the value for s_relay_mail.sAccountId
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.fromName
     *
     * @return the value of s_relay_mail.fromName
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public String getFromname() {
        return fromname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.fromName
     *
     * @param fromname the value for s_relay_mail.fromName
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.fromEmail
     *
     * @return the value of s_relay_mail.fromEmail
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public String getFromemail() {
        return fromemail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.fromEmail
     *
     * @param fromemail the value for s_relay_mail.fromEmail
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }
}