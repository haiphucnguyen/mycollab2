package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermission;
import com.esofthead.mycollab.module.project.domain.ProjectRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectRolePermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int countByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int deleteByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int insert(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int insertSelective(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    List<ProjectRolePermission> selectByExampleWithBLOBs(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    List<ProjectRolePermission> selectByExample(ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    ProjectRolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int updateByExample(@Param("record") ProjectRolePermission record, @Param("example") ProjectRolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ProjectRolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ProjectRolePermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role_permission
     *
     * @mbggenerated Wed Aug 14 17:38:37 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") ProjectRolePermission record, @Param("primaryKeys") List primaryKeys);
}