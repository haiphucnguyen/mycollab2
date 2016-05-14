package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.MilestoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MilestoneMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int countByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int deleteByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int insert(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int insertSelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    List<Milestone> selectByExampleWithBLOBs(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    List<Milestone> selectByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    Milestone selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByExample(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByPrimaryKeySelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    int updateByPrimaryKey(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    Integer insertAndReturnKey(Milestone value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sun May 15 02:13:11 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Milestone record, @Param("primaryKeys") List primaryKeys);
}