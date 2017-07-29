package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CampaignAccount;
import com.mycollab.module.crm.domain.CampaignAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    long countByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int deleteByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Delete({
        "delete from m_crm_campaigns_accounts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Insert({
        "insert into m_crm_campaigns_accounts (id, campaignId, ",
        "accountId, createdTime)",
        "values (#{id,jdbcType=INTEGER}, #{campaignid,jdbcType=INTEGER}, ",
        "#{accountid,jdbcType=INTEGER}, #{createdtime,jdbcType=TIMESTAMP})"
    })
    int insert(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int insertSelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    List<CampaignAccount> selectByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Select({
        "select",
        "id, campaignId, accountId, createdTime",
        "from m_crm_campaigns_accounts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.CampaignAccountMapper.BaseResultMap")
    CampaignAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExampleSelective(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExample(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByPrimaryKeySelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Update({
        "update m_crm_campaigns_accounts",
        "set campaignId = #{campaignid,jdbcType=INTEGER},",
          "accountId = #{accountid,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    Integer insertAndReturnKey(CampaignAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void massUpdateWithSession(@Param("record") CampaignAccount record, @Param("primaryKeys") List primaryKeys);
}