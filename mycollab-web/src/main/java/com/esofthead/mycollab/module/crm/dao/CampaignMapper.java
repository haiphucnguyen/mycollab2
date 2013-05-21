package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignExample;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int insert(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int insertSelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    List<CampaignWithBLOBs> selectByExampleWithBLOBs(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    CampaignWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    Integer insertAndReturnKey(CampaignWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}