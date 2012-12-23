package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.RiskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RiskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int countByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int deleteByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int insert(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int insertSelective(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    List<Risk> selectByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    Risk selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Risk record, @Param("example") RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Risk record, @Param("example") RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbggenerated Sun Dec 23 17:29:21 GMT+07:00 2012
     */
    int updateByPrimaryKey(Risk record);

    Integer insertAndReturnKey(Risk value);
}