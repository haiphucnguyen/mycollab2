package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.CampaignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int insert(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int insertSelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    List<Campaign> selectByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    Campaign selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByPrimaryKey(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Campaign value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}