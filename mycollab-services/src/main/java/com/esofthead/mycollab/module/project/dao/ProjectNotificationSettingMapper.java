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
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectNotificationSettingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int countByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int deleteByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int insert(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int insertSelective(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    List<ProjectNotificationSetting> selectByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    ProjectNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int updateByExampleSelective(@Param("record") ProjectNotificationSetting record, @Param("example") ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int updateByExample(@Param("record") ProjectNotificationSetting record, @Param("example") ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int updateByPrimaryKeySelective(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    int updateByPrimaryKey(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    Integer insertAndReturnKey(ProjectNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbggenerated Wed Sep 24 11:18:45 ICT 2014
     */
    void massUpdateWithSession(@Param("record") ProjectNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}