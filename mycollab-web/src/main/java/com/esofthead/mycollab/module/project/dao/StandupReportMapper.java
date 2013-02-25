package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportExample;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandupReportMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int countByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int deleteByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int insert(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int insertSelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    List<StandupReportWithBLOBs> selectByExampleWithBLOBs(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    List<StandupReport> selectByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    StandupReportWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByExample(@Param("record") StandupReport record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByPrimaryKeyWithBLOBs(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByPrimaryKey(StandupReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    Integer insertAndReturnKey(StandupReport value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}