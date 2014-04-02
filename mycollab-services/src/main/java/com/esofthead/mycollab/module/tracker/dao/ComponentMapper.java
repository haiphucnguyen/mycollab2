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
package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.ComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComponentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int countByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insert(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insertSelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    List<Component> selectByExampleWithBLOBs(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    List<Component> selectByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    Component selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleSelective(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExample(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByPrimaryKeySelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    Integer insertAndReturnKey(Component value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void massUpdateWithSession(@Param("record") Component record, @Param("primaryKeys") List primaryKeys);
}