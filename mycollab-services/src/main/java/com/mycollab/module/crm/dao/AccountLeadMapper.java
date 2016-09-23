package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.AccountLead;
import com.mycollab.module.crm.domain.AccountLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    long countByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int deleteByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int insert(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int insertSelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    List<AccountLead> selectByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    AccountLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByExampleSelective(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByExample(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByPrimaryKeySelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByPrimaryKey(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    Integer insertAndReturnKey(AccountLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    void massUpdateWithSession(@Param("record") AccountLead record, @Param("primaryKeys") List primaryKeys);
}