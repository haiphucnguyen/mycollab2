package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class RelatedItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_item.id
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_item.typeid
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_item.type
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_item.refkey
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    private String refkey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_item.id
     *
     * @return the value of m_tracker_related_item.id
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_item.id
     *
     * @param id the value for m_tracker_related_item.id
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_item.typeid
     *
     * @return the value of m_tracker_related_item.typeid
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_item.typeid
     *
     * @param typeid the value for m_tracker_related_item.typeid
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_item.type
     *
     * @return the value of m_tracker_related_item.type
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_item.type
     *
     * @param type the value for m_tracker_related_item.type
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_item.refkey
     *
     * @return the value of m_tracker_related_item.refkey
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public String getRefkey() {
        return refkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_item.refkey
     *
     * @param refkey the value for m_tracker_related_item.refkey
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    public void setRefkey(String refkey) {
        this.refkey = refkey;
    }
}