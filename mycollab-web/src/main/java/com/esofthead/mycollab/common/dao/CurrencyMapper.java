package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.domain.CurrencyExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CurrencyMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int countByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int deleteByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int insert(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int insertSelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    List<Currency> selectByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    Currency selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Currency value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Tue Jul 23 09:51:40 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Currency record, @Param("primaryKeys") List primaryKeys);
}