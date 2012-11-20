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
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int insert(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int insertSelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    List<Campaign> selectByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    Campaign selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Campaign record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Campaign record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByPrimaryKey(Campaign record);
}