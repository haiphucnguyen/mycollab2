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
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int countByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int deleteByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int insert(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int insertSelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    List<ProjectRole> selectByExampleWithBLOBs(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    List<ProjectRole> selectByExample(ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    ProjectRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int updateByExampleSelective(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int updateByExample(@Param("record") ProjectRole record, @Param("example") ProjectRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    int updateByPrimaryKeySelective(ProjectRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    Integer insertAndReturnKey(ProjectRole value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Thu Sep 05 21:25:59 ICT 2013
     */
    void massUpdateWithSession(@Param("record") ProjectRole record, @Param("primaryKeys") List primaryKeys);
}