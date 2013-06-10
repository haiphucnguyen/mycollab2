package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.AccountContact;
import com.esofthead.mycollab.module.crm.domain.AccountContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int countByExample(AccountContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int deleteByExample(AccountContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int insert(AccountContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int insertSelective(AccountContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    List<AccountContact> selectByExample(AccountContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    AccountContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") AccountContact record, @Param("example") AccountContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByExample(@Param("record") AccountContact record, @Param("example") AccountContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(AccountContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    Integer insertAndReturnKey(AccountContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_contacts
     *
     * @mbggenerated Mon Jun 10 15:53:32 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") AccountContact record, @Param("primaryKeys") List primaryKeys);
}