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
package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.Component;
import com.mycollab.module.tracker.domain.ComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ComponentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int countByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int deleteByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int insert(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int insertSelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    List<Component> selectByExampleWithBLOBs(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    List<Component> selectByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    Component selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByExample(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByPrimaryKeySelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    int updateByPrimaryKey(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    Integer insertAndReturnKey(Component value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Tue Aug 16 15:25:25 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Component record, @Param("primaryKeys") List primaryKeys);
}