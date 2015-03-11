package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectCustomizeViewMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int countByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insert(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insertSelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    List<ProjectCustomizeView> selectByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    ProjectCustomizeView selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExample(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKeySelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKey(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    Integer insertAndReturnKey(ProjectCustomizeView value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ProjectCustomizeView record, @Param("primaryKeys") List primaryKeys);
}