package com.esofthead.mycollab.common.domain;

public class RelayEmailWithBLOBs extends RelayEmail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.bodyContent
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("bodyContent")
    private String bodycontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.recipients
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("recipients")
    private String recipients;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.subject
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("subject")
    private String subject;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.bodyContent
     *
     * @return the value of s_relay_mail.bodyContent
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public String getBodycontent() {
        return bodycontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.bodyContent
     *
     * @param bodycontent the value for s_relay_mail.bodyContent
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setBodycontent(String bodycontent) {
        this.bodycontent = bodycontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.recipients
     *
     * @return the value of s_relay_mail.recipients
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.recipients
     *
     * @param recipients the value for s_relay_mail.recipients
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.subject
     *
     * @return the value of s_relay_mail.subject
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.subject
     *
     * @param subject the value for s_relay_mail.subject
     *
     * @mbggenerated Sat Mar 08 17:38:10 ICT 2014
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}