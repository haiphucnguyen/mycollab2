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
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int countByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insert(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insertSelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    List<Currency> selectByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    Currency selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKey(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    Integer insertAndReturnKey(Currency value);
}