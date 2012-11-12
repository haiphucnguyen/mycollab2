package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int countByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int deleteByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int insertSelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    List<Project> selectByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByPrimaryKey(Project record);
}