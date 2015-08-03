package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Customer;
import com.esofthead.mycollab.module.crm.domain.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CustomerMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKey(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    Integer insertAndReturnKey(Customer value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Customer record, @Param("primaryKeys") List primaryKeys);
}