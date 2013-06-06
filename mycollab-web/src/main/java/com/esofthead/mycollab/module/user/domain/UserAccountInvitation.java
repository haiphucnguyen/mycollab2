/*Domain class of table s_user_account_invitation*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class UserAccountInvitation extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.id
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.username
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.accountId
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.invitationStatus
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @javax.validation.constraints.NotNull(message="Field value must be not null")
    private String invitationstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.sentDate
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    private Date sentdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.id
     *
     * @return the value of s_user_account_invitation.id
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.id
     *
     * @param id the value for s_user_account_invitation.id
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.username
     *
     * @return the value of s_user_account_invitation.username
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.username
     *
     * @param username the value for s_user_account_invitation.username
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.accountId
     *
     * @return the value of s_user_account_invitation.accountId
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.accountId
     *
     * @param accountid the value for s_user_account_invitation.accountId
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.invitationStatus
     *
     * @return the value of s_user_account_invitation.invitationStatus
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public String getInvitationstatus() {
        return invitationstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.invitationStatus
     *
     * @param invitationstatus the value for s_user_account_invitation.invitationStatus
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setInvitationstatus(String invitationstatus) {
        this.invitationstatus = invitationstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.sentDate
     *
     * @return the value of s_user_account_invitation.sentDate
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public Date getSentdate() {
        return sentdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.sentDate
     *
     * @param sentdate the value for s_user_account_invitation.sentDate
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.createdTime
     *
     * @return the value of s_user_account_invitation.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.createdTime
     *
     * @param createdtime the value for s_user_account_invitation.createdTime
     *
     * @mbggenerated Thu Jun 06 11:18:19 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}