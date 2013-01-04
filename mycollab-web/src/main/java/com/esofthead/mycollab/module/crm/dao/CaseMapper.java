package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.CaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int countByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int deleteByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int insert(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int insertSelective(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    List<Case> selectByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    Case selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Case record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Case record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByPrimaryKey(Case record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Case value);
}