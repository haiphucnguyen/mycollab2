/*Domain class of table m_tracker_bug_related_item*/
package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class BugRelatedItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer bugid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    private Integer typeid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.id
     *
     * @return the value of m_tracker_bug_related_item.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.id
     *
     * @param id the value for m_tracker_bug_related_item.id
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.bugid
     *
     * @return the value of m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getBugid() {
        return bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.bugid
     *
     * @param bugid the value for m_tracker_bug_related_item.bugid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setBugid(Integer bugid) {
        this.bugid = bugid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.type
     *
     * @return the value of m_tracker_bug_related_item.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.type
     *
     * @param type the value for m_tracker_bug_related_item.type
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_bug_related_item.typeid
     *
     * @return the value of m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_bug_related_item.typeid
     *
     * @param typeid the value for m_tracker_bug_related_item.typeid
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}