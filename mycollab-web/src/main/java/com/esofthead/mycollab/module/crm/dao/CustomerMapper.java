package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Customer;
import com.esofthead.mycollab.module.crm.domain.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Customer value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Customer record, @Param("primaryKeys") List primaryKeys);
}