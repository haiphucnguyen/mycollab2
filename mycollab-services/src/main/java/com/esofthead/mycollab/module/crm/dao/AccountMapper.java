package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    List<Account> selectByExampleWithBLOBs(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKey(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    Integer insertAndReturnKey(Account value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Account record, @Param("primaryKeys") List primaryKeys);
}