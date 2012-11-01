package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.QuoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuoteMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int countByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int deleteByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int insert(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int insertSelective(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    List<Quote> selectByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    Quote selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Quote record, @Param("example") QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Wed Oct 31 15:11:23 GMT+07:00 2012
     */
    int updateByPrimaryKey(Quote record);
}