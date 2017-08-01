package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.ContactOpportunity;
import com.mycollab.module.crm.domain.ContactOpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactOpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    long countByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int deleteByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Delete({
        "delete from m_crm_contacts_opportunities",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Insert({
        "insert into m_crm_contacts_opportunities (id, contactId, ",
        "opportunityId, createdTime, ",
        "decisionRole)",
        "values (#{id,jdbcType=INTEGER}, #{contactid,jdbcType=INTEGER}, ",
        "#{opportunityid,jdbcType=INTEGER}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{decisionrole,jdbcType=VARCHAR})"
    })
    int insert(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int insertSelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<ContactOpportunity> selectByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Select({
        "select",
        "id, contactId, opportunityId, createdTime, decisionRole",
        "from m_crm_contacts_opportunities",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.ContactOpportunityMapper.BaseResultMap")
    ContactOpportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExample(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByPrimaryKeySelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_contacts_opportunities",
        "set contactId = #{contactid,jdbcType=INTEGER},",
          "opportunityId = #{opportunityid,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "decisionRole = #{decisionrole,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    Integer insertAndReturnKey(ContactOpportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") ContactOpportunity record, @Param("primaryKeys") List primaryKeys);
}