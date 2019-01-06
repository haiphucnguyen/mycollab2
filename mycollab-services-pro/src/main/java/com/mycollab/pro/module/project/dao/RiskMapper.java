package com.mycollab.pro.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.RiskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RiskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    long countByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int deleteByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int insert(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int insertSelective(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    List<Risk> selectByExampleWithBLOBs(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    List<Risk> selectByExample(RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    Risk selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") Risk record, @Param("example") RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Risk record, @Param("example") RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByExample(@Param("record") Risk record, @Param("example") RiskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByPrimaryKeySelective(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    int updateByPrimaryKey(Risk record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    Integer insertAndReturnKey(Risk value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_risk
     *
     * @mbg.generated Sun Jan 06 16:49:52 CST 2019
     */
    void massUpdateWithSession(@Param("record") Risk record, @Param("primaryKeys") List primaryKeys);
}