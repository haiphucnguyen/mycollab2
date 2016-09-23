package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.OpportunityLead;
import com.mycollab.module.crm.domain.OpportunityLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OpportunityLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    long countByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insert(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insertSelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    List<OpportunityLead> selectByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    OpportunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExampleSelective(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExample(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKeySelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKey(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    Integer insertAndReturnKey(OpportunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void massUpdateWithSession(@Param("record") OpportunityLead record, @Param("primaryKeys") List primaryKeys);
}