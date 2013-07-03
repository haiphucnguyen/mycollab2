package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    List<Account> selectByExampleWithBLOBs(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Account value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Account record, @Param("primaryKeys") List primaryKeys);
}