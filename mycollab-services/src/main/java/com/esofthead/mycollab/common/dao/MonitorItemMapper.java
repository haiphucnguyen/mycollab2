package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.MonitorItemExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int countByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int deleteByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int insert(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int insertSelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    List<MonitorItem> selectByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    MonitorItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByExampleSelective(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByExample(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByPrimaryKeySelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    Integer insertAndReturnKey(MonitorItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    void massUpdateWithSession(@Param("record") MonitorItem record, @Param("primaryKeys") List primaryKeys);
}