package com.mycollab.pro.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.StandupReportExample;
import com.mycollab.module.project.domain.StandupReportWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface StandupReportMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    long countByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insert(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insertSelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<StandupReportWithBLOBs> selectByExampleWithBLOBs(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    StandupReportWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeySelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    Integer insertAndReturnKey(StandupReportWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void massUpdateWithSession(@Param("record") StandupReportWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}