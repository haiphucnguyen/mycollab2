package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CampaignExample;
import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    long countByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByExample(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insert(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insertSelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    List<CampaignWithBLOBs> selectByExampleWithBLOBs(CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    CampaignWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") CampaignWithBLOBs record, @Param("example") CampaignExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKeySelective(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(CampaignWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    Integer insertAndReturnKey(CampaignWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaign
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CampaignWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}