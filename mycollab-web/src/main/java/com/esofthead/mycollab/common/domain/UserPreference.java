package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class UserPreference extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_preference.id
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_preference.username
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_preference.lastModuleVisit
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    private String lastmodulevisit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_preference.lastAccessedTime
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    private Date lastaccessedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_preference.id
     *
     * @return the value of s_user_preference.id
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_preference.id
     *
     * @param id the value for s_user_preference.id
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_preference.username
     *
     * @return the value of s_user_preference.username
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_preference.username
     *
     * @param username the value for s_user_preference.username
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_preference.lastModuleVisit
     *
     * @return the value of s_user_preference.lastModuleVisit
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public String getLastmodulevisit() {
        return lastmodulevisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_preference.lastModuleVisit
     *
     * @param lastmodulevisit the value for s_user_preference.lastModuleVisit
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public void setLastmodulevisit(String lastmodulevisit) {
        this.lastmodulevisit = lastmodulevisit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_preference.lastAccessedTime
     *
     * @return the value of s_user_preference.lastAccessedTime
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public Date getLastaccessedtime() {
        return lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_preference.lastAccessedTime
     *
     * @param lastaccessedtime the value for s_user_preference.lastAccessedTime
     *
     * @mbggenerated Fri Apr 12 16:07:31 GMT+07:00 2013
     */
    public void setLastaccessedtime(Date lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }
}