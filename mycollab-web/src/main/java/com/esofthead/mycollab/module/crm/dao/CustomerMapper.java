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
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKey(Customer record);
}