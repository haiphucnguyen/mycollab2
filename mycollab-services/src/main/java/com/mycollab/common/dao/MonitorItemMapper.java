package com.mycollab.common.dao;

import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.domain.MonitorItemExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MonitorItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    long countByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int deleteByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int insert(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int insertSelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    List<MonitorItem> selectByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    MonitorItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByExample(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByPrimaryKeySelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByPrimaryKey(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    Integer insertAndReturnKey(MonitorItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    void massUpdateWithSession(@Param("record") MonitorItem record, @Param("primaryKeys") List primaryKeys);
}