package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignExample;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insert(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insertSelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    List<CampaignWithBLOBs> selectByExampleWithBLOBs(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    CampaignWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKeySelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    Integer insertAndReturnKey(CampaignWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CampaignWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}