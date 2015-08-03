package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.AccountLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int countByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insert(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insertSelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    List<AccountLead> selectByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    AccountLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExampleSelective(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExample(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKeySelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKey(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    Integer insertAndReturnKey(AccountLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void massUpdateWithSession(@Param("record") AccountLead record, @Param("primaryKeys") List primaryKeys);
}