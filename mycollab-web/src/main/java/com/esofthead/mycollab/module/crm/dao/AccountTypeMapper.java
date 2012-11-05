package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.AccountType;
import com.esofthead.mycollab.module.crm.domain.AccountTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountTypeMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int countByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int deleteByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int insert(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int insertSelective(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    List<AccountType> selectByExample(AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    AccountType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int updateByExample(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(AccountType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounttype
     *
     * @mbggenerated Mon Nov 05 17:56:49 GMT+07:00 2012
     */
    int updateByPrimaryKey(AccountType record);
}