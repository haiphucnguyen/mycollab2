package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int countByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int deleteByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int insert(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int insertSelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    List<Opportunity> selectByExampleWithBLOBs(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    List<Opportunity> selectByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    Opportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByExampleSelective(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByExample(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByPrimaryKeySelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByPrimaryKeyWithBLOBs(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    int updateByPrimaryKey(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    Integer insertAndReturnKey(Opportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Sun Nov 30 15:08:33 ICT 2014
     */
    void massUpdateWithSession(@Param("record") Opportunity record, @Param("primaryKeys") List primaryKeys);
}