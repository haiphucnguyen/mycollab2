package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CaseExample;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    long countByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int deleteByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int insert(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int insertSelective(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    List<CaseWithBLOBs> selectByExampleWithBLOBs(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    CaseWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CaseWithBLOBs record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") CaseWithBLOBs record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int updateByPrimaryKeySelective(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    Integer insertAndReturnKey(CaseWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Mon Oct 03 14:51:42 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CaseWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}