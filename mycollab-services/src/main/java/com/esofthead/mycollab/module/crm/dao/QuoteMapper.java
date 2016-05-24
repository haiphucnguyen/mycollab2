package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.QuoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface QuoteMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int countByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int deleteByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int insert(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int insertSelective(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    List<Quote> selectByExample(QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    Quote selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Quote record, @Param("example") QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int updateByPrimaryKeySelective(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    int updateByPrimaryKey(Quote record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    Integer insertAndReturnKey(Quote value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote
     *
     * @mbggenerated Tue May 24 18:34:09 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Quote record, @Param("primaryKeys") List primaryKeys);
}