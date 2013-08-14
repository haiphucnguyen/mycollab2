/*Domain class of table s_table_customize_view*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class CustomViewStore extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.id
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.createdUser
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.createdTime
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.viewId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String viewid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.sAccountId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_table_customize_view.viewInfo
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String viewinfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.id
     *
     * @return the value of s_table_customize_view.id
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.id
     *
     * @param id the value for s_table_customize_view.id
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.createdUser
     *
     * @return the value of s_table_customize_view.createdUser
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.createdUser
     *
     * @param createduser the value for s_table_customize_view.createdUser
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.createdTime
     *
     * @return the value of s_table_customize_view.createdTime
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.createdTime
     *
     * @param createdtime the value for s_table_customize_view.createdTime
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.viewId
     *
     * @return the value of s_table_customize_view.viewId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public String getViewid() {
        return viewid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.viewId
     *
     * @param viewid the value for s_table_customize_view.viewId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setViewid(String viewid) {
        this.viewid = viewid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.sAccountId
     *
     * @return the value of s_table_customize_view.sAccountId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.sAccountId
     *
     * @param saccountid the value for s_table_customize_view.sAccountId
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_table_customize_view.viewInfo
     *
     * @return the value of s_table_customize_view.viewInfo
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public String getViewinfo() {
        return viewinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_table_customize_view.viewInfo
     *
     * @param viewinfo the value for s_table_customize_view.viewInfo
     *
     * @mbggenerated Wed Aug 14 17:38:36 GMT+07:00 2013
     */
    public void setViewinfo(String viewinfo) {
        this.viewinfo = viewinfo;
    }
}