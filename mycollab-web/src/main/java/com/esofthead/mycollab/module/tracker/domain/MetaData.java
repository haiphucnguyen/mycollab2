package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class MetaData extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_metadata.xmlstring
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    private String xmlstring;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.id
     *
     * @return the value of m_tracker_metadata.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.id
     *
     * @param id the value for m_tracker_metadata.id
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.projectid
     *
     * @return the value of m_tracker_metadata.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.projectid
     *
     * @param projectid the value for m_tracker_metadata.projectid
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_metadata.xmlstring
     *
     * @return the value of m_tracker_metadata.xmlstring
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public String getXmlstring() {
        return xmlstring;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_metadata.xmlstring
     *
     * @param xmlstring the value for m_tracker_metadata.xmlstring
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    public void setXmlstring(String xmlstring) {
        this.xmlstring = xmlstring;
    }
}