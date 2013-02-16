package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.MilestoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MilestoneMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int countByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int deleteByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int insert(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int insertSelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    List<Milestone> selectByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    Milestone selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    int updateByPrimaryKey(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Milestone value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbggenerated Sat Feb 16 06:57:17 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}