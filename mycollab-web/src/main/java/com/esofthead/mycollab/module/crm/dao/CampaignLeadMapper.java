package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.CampaignLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int countByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insert(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insertSelective(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    List<CampaignLead> selectByExample(CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    CampaignLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") CampaignLead record, @Param("example") CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExample(@Param("record") CampaignLead record, @Param("example") CampaignLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(CampaignLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    Integer insertAndReturnKey(CampaignLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_leads
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") CampaignLead record, @Param("primaryKeys") List primaryKeys);
}