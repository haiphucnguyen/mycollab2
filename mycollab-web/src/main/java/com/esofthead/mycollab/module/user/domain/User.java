package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class User extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.username
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.firstname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.middlename
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String middlename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.nickname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.dateofbirth
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Date dateofbirth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.password
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.email
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.website
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.registeredTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Date registeredtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastAccessedTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Date lastaccessedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.accountId
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.countryid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Integer countryid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.company
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String company;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.timezoneid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String timezoneid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.registrationSource
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String registrationsource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.isAdmin
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Boolean isadmin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.registerStatus
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private String registerstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.roleid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    private Integer roleid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.username
     *
     * @return the value of s_user.username
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.username
     *
     * @param username the value for s_user.username
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.firstname
     *
     * @return the value of s_user.firstname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.firstname
     *
     * @param firstname the value for s_user.firstname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.middlename
     *
     * @return the value of s_user.middlename
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.middlename
     *
     * @param middlename the value for s_user.middlename
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastname
     *
     * @return the value of s_user.lastname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.lastname
     *
     * @param lastname the value for s_user.lastname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.nickname
     *
     * @return the value of s_user.nickname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.nickname
     *
     * @param nickname the value for s_user.nickname
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.dateofbirth
     *
     * @return the value of s_user.dateofbirth
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Date getDateofbirth() {
        return dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.dateofbirth
     *
     * @param dateofbirth the value for s_user.dateofbirth
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.password
     *
     * @return the value of s_user.password
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.password
     *
     * @param password the value for s_user.password
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.email
     *
     * @return the value of s_user.email
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.email
     *
     * @param email the value for s_user.email
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.website
     *
     * @return the value of s_user.website
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.website
     *
     * @param website the value for s_user.website
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.registeredTime
     *
     * @return the value of s_user.registeredTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Date getRegisteredtime() {
        return registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.registeredTime
     *
     * @param registeredtime the value for s_user.registeredTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setRegisteredtime(Date registeredtime) {
        this.registeredtime = registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastAccessedTime
     *
     * @return the value of s_user.lastAccessedTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Date getLastaccessedtime() {
        return lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.lastAccessedTime
     *
     * @param lastaccessedtime the value for s_user.lastAccessedTime
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setLastaccessedtime(Date lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.accountId
     *
     * @return the value of s_user.accountId
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.accountId
     *
     * @param accountid the value for s_user.accountId
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.countryid
     *
     * @return the value of s_user.countryid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Integer getCountryid() {
        return countryid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.countryid
     *
     * @param countryid the value for s_user.countryid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.company
     *
     * @return the value of s_user.company
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.company
     *
     * @param company the value for s_user.company
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.timezoneid
     *
     * @return the value of s_user.timezoneid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getTimezoneid() {
        return timezoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.timezoneid
     *
     * @param timezoneid the value for s_user.timezoneid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setTimezoneid(String timezoneid) {
        this.timezoneid = timezoneid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.registrationSource
     *
     * @return the value of s_user.registrationSource
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getRegistrationsource() {
        return registrationsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.registrationSource
     *
     * @param registrationsource the value for s_user.registrationSource
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setRegistrationsource(String registrationsource) {
        this.registrationsource = registrationsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.isAdmin
     *
     * @return the value of s_user.isAdmin
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Boolean getIsadmin() {
        return isadmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.isAdmin
     *
     * @param isadmin the value for s_user.isAdmin
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.registerStatus
     *
     * @return the value of s_user.registerStatus
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public String getRegisterstatus() {
        return registerstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.registerStatus
     *
     * @param registerstatus the value for s_user.registerStatus
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setRegisterstatus(String registerstatus) {
        this.registerstatus = registerstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.roleid
     *
     * @return the value of s_user.roleid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.roleid
     *
     * @param roleid the value for s_user.roleid
     *
     * @mbggenerated Tue Jan 15 17:06:11 GMT+07:00 2013
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}