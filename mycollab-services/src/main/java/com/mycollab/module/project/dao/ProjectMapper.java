package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface ProjectMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    long countByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int deleteByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int insertSelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    List<Project> selectByExampleWithBLOBs(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    List<Project> selectByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    int updateByPrimaryKey(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    Integer insertAndReturnKey(Project value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_project
     *
     * @mbg.generated Fri Feb 22 10:43:09 CST 2019
     */
    void massUpdateWithSession(@Param("record") Project record, @Param("primaryKeys") List primaryKeys);
}