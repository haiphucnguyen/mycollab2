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
package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.ProjectCustomizeView;
import com.mycollab.module.project.domain.ProjectCustomizeViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectCustomizeViewMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int countByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int deleteByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int insert(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int insertSelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    List<ProjectCustomizeView> selectByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    ProjectCustomizeView selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int updateByExample(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int updateByPrimaryKeySelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    int updateByPrimaryKey(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    Integer insertAndReturnKey(ProjectCustomizeView value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Tue Aug 16 15:25:22 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ProjectCustomizeView record, @Param("primaryKeys") List primaryKeys);
}