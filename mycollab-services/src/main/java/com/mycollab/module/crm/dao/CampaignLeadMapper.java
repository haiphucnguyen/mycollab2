package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CampaignLead;
import com.mycollab.module.crm.domain.CampaignLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    long countByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insert(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insertSelective(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    List<CampaignLead> selectByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    CampaignLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CampaignLead record, @Param("example") CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExample(@Param("record") CampaignLead record, @Param("example") CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKeySelective(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKey(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    Integer insertAndReturnKey(CampaignLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CampaignLead record, @Param("primaryKeys") List primaryKeys);
}