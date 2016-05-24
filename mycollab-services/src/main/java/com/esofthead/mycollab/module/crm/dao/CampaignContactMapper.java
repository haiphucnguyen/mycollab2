package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int countByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int deleteByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int insert(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int insertSelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    List<CampaignContact> selectByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    CampaignContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int updateByExample(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int updateByPrimaryKeySelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    int updateByPrimaryKey(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    Integer insertAndReturnKey(CampaignContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue May 24 18:34:08 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CampaignContact record, @Param("primaryKeys") List primaryKeys);
}