/*Domain class of table s_user_account*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_user_account")
public class UserAccount extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.id
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.username
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.accountId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("accountId")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.isAccountOwner
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("isAccountOwner")
    private Boolean isaccountowner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.roleId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("roleId")
    private Integer roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registeredTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("registeredTime")
    private Date registeredtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registerStatus
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("registerStatus")
    private String registerstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.lastAccessedTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastAccessedTime")
    private Date lastaccessedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registrationSource
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("registrationSource")
    private String registrationsource;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.id
     *
     * @return the value of s_user_account.id
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.id
     *
     * @param id the value for s_user_account.id
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.username
     *
     * @return the value of s_user_account.username
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.username
     *
     * @param username the value for s_user_account.username
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.accountId
     *
     * @return the value of s_user_account.accountId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.accountId
     *
     * @param accountid the value for s_user_account.accountId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.isAccountOwner
     *
     * @return the value of s_user_account.isAccountOwner
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Boolean getIsaccountowner() {
        return isaccountowner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.isAccountOwner
     *
     * @param isaccountowner the value for s_user_account.isAccountOwner
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setIsaccountowner(Boolean isaccountowner) {
        this.isaccountowner = isaccountowner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.roleId
     *
     * @return the value of s_user_account.roleId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.roleId
     *
     * @param roleid the value for s_user_account.roleId
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.registeredTime
     *
     * @return the value of s_user_account.registeredTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Date getRegisteredtime() {
        return registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.registeredTime
     *
     * @param registeredtime the value for s_user_account.registeredTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setRegisteredtime(Date registeredtime) {
        this.registeredtime = registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.registerStatus
     *
     * @return the value of s_user_account.registerStatus
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public String getRegisterstatus() {
        return registerstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.registerStatus
     *
     * @param registerstatus the value for s_user_account.registerStatus
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setRegisterstatus(String registerstatus) {
        this.registerstatus = registerstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.lastAccessedTime
     *
     * @return the value of s_user_account.lastAccessedTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public Date getLastaccessedtime() {
        return lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.lastAccessedTime
     *
     * @param lastaccessedtime the value for s_user_account.lastAccessedTime
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setLastaccessedtime(Date lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.registrationSource
     *
     * @return the value of s_user_account.registrationSource
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public String getRegistrationsource() {
        return registrationsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.registrationSource
     *
     * @param registrationsource the value for s_user_account.registrationSource
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    public void setRegistrationsource(String registrationsource) {
        this.registrationsource = registrationsource;
    }

    public static enum Field {
        id,
        username,
        accountid,
        isaccountowner,
        roleid,
        registeredtime,
        registerstatus,
        lastaccessedtime,
        registrationsource;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}