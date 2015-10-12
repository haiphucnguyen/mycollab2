/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.domain.MonitorItemExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MonitorItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int countByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int deleteByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int insert(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int insertSelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    List<MonitorItem> selectByExample(MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    MonitorItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int updateByExampleSelective(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int updateByExample(@Param("record") MonitorItem record, @Param("example") MonitorItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int updateByPrimaryKeySelective(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    int updateByPrimaryKey(MonitorItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    Integer insertAndReturnKey(MonitorItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_monitor_item
     *
     * @mbggenerated Mon Oct 12 13:22:25 ICT 2015
     */
    void massUpdateWithSession(@Param("record") MonitorItem record, @Param("primaryKeys") List primaryKeys);
}