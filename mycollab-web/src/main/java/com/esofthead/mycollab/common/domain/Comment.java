package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Comment extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.id
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.comment
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdUser
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdTime
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.type
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.typeId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.sAccountId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.id
     *
     * @return the value of m_comment.id
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.id
     *
     * @param id the value for m_comment.id
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.comment
     *
     * @return the value of m_comment.comment
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.comment
     *
     * @param comment the value for m_comment.comment
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.createdUser
     *
     * @return the value of m_comment.createdUser
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.createdUser
     *
     * @param createduser the value for m_comment.createdUser
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.createdTime
     *
     * @return the value of m_comment.createdTime
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.createdTime
     *
     * @param createdtime the value for m_comment.createdTime
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.type
     *
     * @return the value of m_comment.type
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.type
     *
     * @param type the value for m_comment.type
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.typeId
     *
     * @return the value of m_comment.typeId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.typeId
     *
     * @param typeid the value for m_comment.typeId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.sAccountId
     *
     * @return the value of m_comment.sAccountId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.sAccountId
     *
     * @param saccountid the value for m_comment.sAccountId
     *
     * @mbggenerated Mon Apr 01 16:56:56 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}