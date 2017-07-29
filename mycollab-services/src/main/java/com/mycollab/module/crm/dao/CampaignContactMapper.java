package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CampaignContact;
import com.mycollab.module.crm.domain.CampaignContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    long countByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int deleteByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Delete({
        "delete from m_crm_campaigns_contacts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Insert({
        "insert into m_crm_campaigns_contacts (id, campaignId, ",
        "contactId, createdTime)",
        "values (#{id,jdbcType=INTEGER}, #{campaignid,jdbcType=INTEGER}, ",
        "#{contactid,jdbcType=INTEGER}, #{createdtime,jdbcType=TIMESTAMP})"
    })
    int insert(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int insertSelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    List<CampaignContact> selectByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Select({
        "select",
        "id, campaignId, contactId, createdTime",
        "from m_crm_campaigns_contacts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.CampaignContactMapper.BaseResultMap")
    CampaignContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExampleSelective(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExample(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByPrimaryKeySelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Update({
        "update m_crm_campaigns_contacts",
        "set campaignId = #{campaignid,jdbcType=INTEGER},",
          "contactId = #{contactid,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    Integer insertAndReturnKey(CampaignContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void massUpdateWithSession(@Param("record") CampaignContact record, @Param("primaryKeys") List primaryKeys);
}