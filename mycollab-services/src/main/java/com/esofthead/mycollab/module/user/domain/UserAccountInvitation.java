/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table s_user_account_invitation*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_user_account_invitation")
public class UserAccountInvitation extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.id
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.username
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.accountId
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("accountId")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.invitationStatus
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("invitationStatus")
    private String invitationstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.sentDate
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sentDate")
    private Date sentdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.createdTime
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account_invitation.inviteUser
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("inviteUser")
    private String inviteuser;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.id
     *
     * @return the value of s_user_account_invitation.id
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
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
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account_invitation.inviteUser
     *
     * @return the value of s_user_account_invitation.inviteUser
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    public String getInviteuser() {
        return inviteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account_invitation.inviteUser
     *
     * @param inviteuser the value for s_user_account_invitation.inviteUser
     *
     * @mbggenerated Thu Feb 26 15:08:42 ICT 2015
     */
    public void setInviteuser(String inviteuser) {
        this.inviteuser = inviteuser;
    }

    public static enum Field {
        id,
        username,
        accountid,
        invitationstatus,
        sentdate,
        createdtime,
        inviteuser;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}