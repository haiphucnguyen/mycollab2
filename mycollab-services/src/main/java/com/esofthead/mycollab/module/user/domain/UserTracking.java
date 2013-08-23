/*Domain class of table s_user_tracking*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class UserTracking extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.username
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String useragent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.id
     *
     * @return the value of s_user_tracking.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.id
     *
     * @param id the value for s_user_tracking.id
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.username
     *
     * @return the value of s_user_tracking.username
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.username
     *
     * @param username the value for s_user_tracking.username
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.createdTime
     *
     * @return the value of s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.createdTime
     *
     * @param createdtime the value for s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.sAccountId
     *
     * @return the value of s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.sAccountId
     *
     * @param saccountid the value for s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.userAgent
     *
     * @return the value of s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.userAgent
     *
     * @param useragent the value for s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 12 16:42:04 GMT+07:00 2013
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }
}