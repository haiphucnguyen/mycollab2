/*Domain class of table s_user_permission*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class UserPermission extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.Id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.module
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.type
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.hasPermission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String haspermission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.Id
     *
     * @return the value of s_user_permission.Id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.Id
     *
     * @param id the value for s_user_permission.Id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.module
     *
     * @return the value of s_user_permission.module
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.module
     *
     * @param module the value for s_user_permission.module
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.type
     *
     * @return the value of s_user_permission.type
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.type
     *
     * @param type the value for s_user_permission.type
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.hasPermission
     *
     * @return the value of s_user_permission.hasPermission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public String getHaspermission() {
        return haspermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.hasPermission
     *
     * @param haspermission the value for s_user_permission.hasPermission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setHaspermission(String haspermission) {
        this.haspermission = haspermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.username
     *
     * @return the value of s_user_permission.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.username
     *
     * @param username the value for s_user_permission.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }
}