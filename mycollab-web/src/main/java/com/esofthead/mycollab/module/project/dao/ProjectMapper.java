package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int countByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int deleteByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int insertSelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    List<Project> selectByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    int updateByPrimaryKey(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Project value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbggenerated Tue Jan 22 08:33:56 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}