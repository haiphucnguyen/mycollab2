package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.AccountCurrency;
import com.esofthead.mycollab.common.domain.AccountCurrencyExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountCurrencyMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int countByExample(AccountCurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int deleteByExample(AccountCurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int insert(AccountCurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int insertSelective(AccountCurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    List<AccountCurrency> selectByExample(AccountCurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    AccountCurrency selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") AccountCurrency record, @Param("example") AccountCurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int updateByExample(@Param("record") AccountCurrency record, @Param("example") AccountCurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(AccountCurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    Integer insertAndReturnKey(AccountCurrency value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_currency
     *
     * @mbggenerated Tue Apr 30 10:59:00 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}