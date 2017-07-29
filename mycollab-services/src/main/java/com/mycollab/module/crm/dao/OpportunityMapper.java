package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.OpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    long countByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int deleteByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Delete({
        "delete from m_crm_opportunity",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Insert({
        "insert into m_crm_opportunity (id, opportunityName, ",
        "currencyid, accountid, ",
        "amount, type, source, ",
        "expectedClosedDate, campaignid, ",
        "nextStep, probability, ",
        "createdTime, createdUser, ",
        "sAccountId, assignUser, ",
        "opportunityType, salesStage, ",
        "lastUpdatedTime, avatarId, ",
        "description)",
        "values (#{id,jdbcType=INTEGER}, #{opportunityname,jdbcType=VARCHAR}, ",
        "#{currencyid,jdbcType=VARCHAR}, #{accountid,jdbcType=INTEGER}, ",
        "#{amount,jdbcType=DOUBLE}, #{type,jdbcType=VARCHAR}, #{source,jdbcType=VARCHAR}, ",
        "#{expectedcloseddate,jdbcType=TIMESTAMP}, #{campaignid,jdbcType=INTEGER}, ",
        "#{nextstep,jdbcType=VARCHAR}, #{probability,jdbcType=INTEGER}, ",
        "#{createdtime,jdbcType=TIMESTAMP}, #{createduser,jdbcType=VARCHAR}, ",
        "#{saccountid,jdbcType=INTEGER}, #{assignuser,jdbcType=VARCHAR}, ",
        "#{opportunitytype,jdbcType=VARCHAR}, #{salesstage,jdbcType=VARCHAR}, ",
        "#{lastupdatedtime,jdbcType=TIMESTAMP}, #{avatarid,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int insertSelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    List<Opportunity> selectByExampleWithBLOBs(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    List<Opportunity> selectByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Select({
        "select",
        "id, opportunityName, currencyid, accountid, amount, type, source, expectedClosedDate, ",
        "campaignid, nextStep, probability, createdTime, createdUser, sAccountId, assignUser, ",
        "opportunityType, salesStage, lastUpdatedTime, avatarId, description",
        "from m_crm_opportunity",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.OpportunityMapper.ResultMapWithBLOBs")
    Opportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExample(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByPrimaryKeySelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Update({
        "update m_crm_opportunity",
        "set opportunityName = #{opportunityname,jdbcType=VARCHAR},",
          "currencyid = #{currencyid,jdbcType=VARCHAR},",
          "accountid = #{accountid,jdbcType=INTEGER},",
          "amount = #{amount,jdbcType=DOUBLE},",
          "type = #{type,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "expectedClosedDate = #{expectedcloseddate,jdbcType=TIMESTAMP},",
          "campaignid = #{campaignid,jdbcType=INTEGER},",
          "nextStep = #{nextstep,jdbcType=VARCHAR},",
          "probability = #{probability,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "opportunityType = #{opportunitytype,jdbcType=VARCHAR},",
          "salesStage = #{salesstage,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "avatarId = #{avatarid,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Update({
        "update m_crm_opportunity",
        "set opportunityName = #{opportunityname,jdbcType=VARCHAR},",
          "currencyid = #{currencyid,jdbcType=VARCHAR},",
          "accountid = #{accountid,jdbcType=INTEGER},",
          "amount = #{amount,jdbcType=DOUBLE},",
          "type = #{type,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "expectedClosedDate = #{expectedcloseddate,jdbcType=TIMESTAMP},",
          "campaignid = #{campaignid,jdbcType=INTEGER},",
          "nextStep = #{nextstep,jdbcType=VARCHAR},",
          "probability = #{probability,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "opportunityType = #{opportunitytype,jdbcType=VARCHAR},",
          "salesStage = #{salesstage,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "avatarId = #{avatarid,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    Integer insertAndReturnKey(Opportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Opportunity record, @Param("primaryKeys") List primaryKeys);
}