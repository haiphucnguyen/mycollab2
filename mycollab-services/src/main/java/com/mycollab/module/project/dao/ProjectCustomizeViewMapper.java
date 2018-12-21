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
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    long countByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insert(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insertSelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<ProjectCustomizeView> selectByExample(ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    ProjectCustomizeView selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExample(@Param("record") ProjectCustomizeView record, @Param("example") ProjectCustomizeViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeySelective(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKey(ProjectCustomizeView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    Integer insertAndReturnKey(ProjectCustomizeView value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void massUpdateWithSession(@Param("record") ProjectCustomizeView record, @Param("primaryKeys") List primaryKeys);
}