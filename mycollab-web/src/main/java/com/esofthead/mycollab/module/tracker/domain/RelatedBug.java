package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class RelatedBug extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.id
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.bugid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    private Integer bugid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.relatedid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    private Integer relatedid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.relatetype
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    private Long relatetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_related_bug.comment
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    private String comment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.id
     *
     * @return the value of m_tracker_related_bug.id
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.id
     *
     * @param id the value for m_tracker_related_bug.id
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.bugid
     *
     * @return the value of m_tracker_related_bug.bugid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public Integer getBugid() {
        return bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.bugid
     *
     * @param bugid the value for m_tracker_related_bug.bugid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public void setBugid(Integer bugid) {
        this.bugid = bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.relatedid
     *
     * @return the value of m_tracker_related_bug.relatedid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public Integer getRelatedid() {
        return relatedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.relatedid
     *
     * @param relatedid the value for m_tracker_related_bug.relatedid
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public void setRelatedid(Integer relatedid) {
        this.relatedid = relatedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.relatetype
     *
     * @return the value of m_tracker_related_bug.relatetype
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public Long getRelatetype() {
        return relatetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.relatetype
     *
     * @param relatetype the value for m_tracker_related_bug.relatetype
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public void setRelatetype(Long relatetype) {
        this.relatetype = relatetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_related_bug.comment
     *
     * @return the value of m_tracker_related_bug.comment
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_related_bug.comment
     *
     * @param comment the value for m_tracker_related_bug.comment
     *
     * @mbggenerated Fri Dec 14 14:31:22 GMT+07:00 2012
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}