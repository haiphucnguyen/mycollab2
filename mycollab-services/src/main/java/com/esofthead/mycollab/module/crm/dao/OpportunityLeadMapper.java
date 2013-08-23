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
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int countByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int deleteByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int insert(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int insertSelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    List<OpportunityLead> selectByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    OpportunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int updateByExample(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    Integer insertAndReturnKey(OpportunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Thu Aug 08 17:37:38 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") OpportunityLead record, @Param("primaryKeys") List primaryKeys);
}