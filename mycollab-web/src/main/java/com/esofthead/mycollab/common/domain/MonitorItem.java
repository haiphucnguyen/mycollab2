package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class MonitorItem extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.id
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.user
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    private String user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.itemid
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    private String itemid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_monitor_item.monitor_date
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    private Date monitorDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.id
     *
     * @return the value of m_monitor_item.id
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
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
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
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
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
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
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.itemid
     *
     * @return the value of m_monitor_item.itemid
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    public String getItemid() {
        return itemid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_monitor_item.itemid
     *
     * @param itemid the value for m_monitor_item.itemid
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_monitor_item.monitor_date
     *
     * @return the value of m_monitor_item.monitor_date
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
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
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    public void setMonitorDate(Date monitorDate) {
        this.monitorDate = monitorDate;
    }
}