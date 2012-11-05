package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.CaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int countByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int deleteByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int insert(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int insertSelective(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    List<Case> selectByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    Case selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Case record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Case record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Mon Nov 05 13:57:12 GMT+07:00 2012
     */
    int updateByPrimaryKey(Case record);
}