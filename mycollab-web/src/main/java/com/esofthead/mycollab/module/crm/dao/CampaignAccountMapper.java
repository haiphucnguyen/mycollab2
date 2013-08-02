package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CampaignAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int countByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insert(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insertSelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    List<CampaignAccount> selectByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    CampaignAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExample(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    Integer insertAndReturnKey(CampaignAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") CampaignAccount record, @Param("primaryKeys") List primaryKeys);
}