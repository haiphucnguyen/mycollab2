package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.OpportunityLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpportunityLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int countByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int deleteByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int insert(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int insertSelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    List<OpportunityLead> selectByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    OpportunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int updateByExampleSelective(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int updateByExample(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    int updateByPrimaryKeySelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    Integer insertAndReturnKey(OpportunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Fri Jan 10 10:03:35 ICT 2014
     */
    void massUpdateWithSession(@Param("record") OpportunityLead record, @Param("primaryKeys") List primaryKeys);
}