/*Domain class of table s_user_information*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class UserInformation extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_information.sAccountId
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.id
     *
     * @return the value of s_user_information.id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.id
     *
     * @param id the value for s_user_information.id
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.username
     *
     * @return the value of s_user_information.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.username
     *
     * @param username the value for s_user_information.username
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_information.sAccountId
     *
     * @return the value of s_user_information.sAccountId
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_information.sAccountId
     *
     * @param saccountid the value for s_user_information.sAccountId
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}