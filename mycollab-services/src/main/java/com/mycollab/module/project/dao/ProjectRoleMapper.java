package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.ProjectRole;
import com.mycollab.module.project.domain.ProjectRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface ProjectRoleMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    long countByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int deleteByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int insert(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int insertSelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    List<ProjectRole> selectByExampleWithBLOBs(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    List<ProjectRole> selectByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    ProjectRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByExampleSelective(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByExample(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByPrimaryKeySelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByPrimaryKeyWithBLOBs(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByPrimaryKey(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    Integer insertAndReturnKey(ProjectRole value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    void massUpdateWithSession(@Param("record") ProjectRole record, @Param("primaryKeys") List primaryKeys);
}