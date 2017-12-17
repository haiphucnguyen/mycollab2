/*Domain class of table s_user_account*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user_account")
@Alias("UserAccount")
public class UserAccount extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.id
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.username
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.accountId
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("accountId")
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.isAccountOwner
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("isAccountOwner")
    private Boolean isaccountowner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.roleId
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("roleId")
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registeredTime
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("registeredTime")
    private Date registeredtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registerStatus
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("registerStatus")
    private String registerstatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.lastAccessedTime
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Column("lastAccessedTime")
    private Date lastaccessedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.registrationSource
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("registrationSource")
    private String registrationsource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.lastModuleVisit
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastModuleVisit")
    private String lastmodulevisit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_account.inviteUser
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("inviteUser")
    private String inviteuser;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        UserAccount item = (UserAccount)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1735, 895).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.id
     *
     * @return the value of s_user_account.id
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    public void setRegistrationsource(String registrationsource) {
        this.registrationsource = registrationsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.lastModuleVisit
     *
     * @return the value of s_user_account.lastModuleVisit
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    public String getLastmodulevisit() {
        return lastmodulevisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.lastModuleVisit
     *
     * @param lastmodulevisit the value for s_user_account.lastModuleVisit
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    public void setLastmodulevisit(String lastmodulevisit) {
        this.lastmodulevisit = lastmodulevisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_account.inviteUser
     *
     * @return the value of s_user_account.inviteUser
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    public String getInviteuser() {
        return inviteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_account.inviteUser
     *
     * @param inviteuser the value for s_user_account.inviteUser
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    public void setInviteuser(String inviteuser) {
        this.inviteuser = inviteuser;
    }

    public enum Field {
        id,
        username,
        accountid,
        isaccountowner,
        roleid,
        registeredtime,
        registerstatus,
        lastaccessedtime,
        registrationsource,
        lastmodulevisit,
        inviteuser;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}