package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class MonitorItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.id
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.user
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private String user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.monitor_date
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private Date monitorDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.type
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.typeid
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    private Integer typeid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.id
     *
     * @return the value of m_monitor_item.id
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.id
     *
     * @param id the value for m_monitor_item.id
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.user
     *
     * @return the value of m_monitor_item.user
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.user
     *
     * @param user the value for m_monitor_item.user
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.monitor_date
     *
     * @return the value of m_monitor_item.monitor_date
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public Date getMonitorDate() {
        return monitorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.monitor_date
     *
     * @param monitorDate the value for m_monitor_item.monitor_date
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.type
     *
     * @return the value of m_monitor_item.type
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.type
     *
     * @param type the value for m_monitor_item.type
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.typeid
     *
     * @return the value of m_monitor_item.typeid
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.typeid
     *
     * @param typeid the value for m_monitor_item.typeid
     *
     * @mbggenerated Thu Feb 28 09:15:30 GMT+07:00 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}