package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int countByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int deleteByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int insert(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int insertSelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    List<CampaignContact> selectByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    CampaignContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int updateByExampleSelective(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int updateByExample(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    int updateByPrimaryKeySelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    Integer insertAndReturnKey(CampaignContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Tue Dec 03 10:11:35 ICT 2013
     */
    void massUpdateWithSession(@Param("record") CampaignContact record, @Param("primaryKeys") List primaryKeys);
}