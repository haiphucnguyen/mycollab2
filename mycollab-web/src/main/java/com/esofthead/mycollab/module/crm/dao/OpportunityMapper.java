package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int countByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int deleteByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int insert(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int insertSelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    List<Opportunity> selectByExampleWithBLOBs(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    List<Opportunity> selectByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    Opportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Opportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Opportunity record, @Param("primaryKeys") List primaryKeys);
}