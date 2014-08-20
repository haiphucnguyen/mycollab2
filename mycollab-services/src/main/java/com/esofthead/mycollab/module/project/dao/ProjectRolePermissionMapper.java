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
import com.esofthead.mycollab.module.project.domain.ProjectRolePermission;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectRolePermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int countByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int deleteByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int insert(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int insertSelective(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    List<ProjectRolePermission> selectByExampleWithBLOBs(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    List<ProjectRolePermission> selectByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    ProjectRolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByExampleSelective(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByExample(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByPrimaryKeySelective(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByPrimaryKeyWithBLOBs(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    int updateByPrimaryKey(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    Integer insertAndReturnKey(ProjectRolePermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 20 07:04:21 ICT 2014
     */
    void massUpdateWithSession(@Param("record") ProjectRolePermission record, @Param("primaryKeys") List primaryKeys);
}