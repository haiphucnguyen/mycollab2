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
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.ProjectRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectRoleMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int countByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int deleteByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int insert(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int insertSelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    List<ProjectRole> selectByExampleWithBLOBs(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    List<ProjectRole> selectByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    ProjectRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int updateByExampleSelective(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int updateByExample(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    int updateByPrimaryKeySelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    Integer insertAndReturnKey(ProjectRole value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    void massUpdateWithSession(@Param("record") ProjectRole record, @Param("primaryKeys") List primaryKeys);
}