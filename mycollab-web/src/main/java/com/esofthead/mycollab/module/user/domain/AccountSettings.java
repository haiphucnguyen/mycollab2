package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class AccountSettings extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.id
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.sAccountId
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.logoPath
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    private String logopath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    private String defaulttimezone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.id
     *
     * @return the value of s_account_settings.id
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.id
     *
     * @param id the value for s_account_settings.id
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.sAccountId
     *
     * @return the value of s_account_settings.sAccountId
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.sAccountId
     *
     * @param saccountid the value for s_account_settings.sAccountId
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.logoPath
     *
     * @return the value of s_account_settings.logoPath
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public String getLogopath() {
        return logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.logoPath
     *
     * @param logopath the value for s_account_settings.logoPath
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_settings.defaultTimezone
     *
     * @return the value of s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public String getDefaulttimezone() {
        return defaulttimezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_settings.defaultTimezone
     *
     * @param defaulttimezone the value for s_account_settings.defaultTimezone
     *
     * @mbggenerated Mon Apr 08 14:36:29 GMT+07:00 2013
     */
    public void setDefaulttimezone(String defaulttimezone) {
        this.defaulttimezone = defaulttimezone;
    }
}