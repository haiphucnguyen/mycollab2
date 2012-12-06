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
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int countByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int deleteByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int insert(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int insertSelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    List<Opportunity> selectByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    Opportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Thu Dec 06 08:44:39 GMT+07:00 2012
     */
    int updateByPrimaryKey(Opportunity record);
}