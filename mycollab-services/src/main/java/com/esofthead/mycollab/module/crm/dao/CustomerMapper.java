/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    Customer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    Integer insertAndReturnKey(Customer value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_customer
     *
     * @mbggenerated Sat Nov 09 12:02:55 ICT 2013
     */
    void massUpdateWithSession(@Param("record") Customer record, @Param("primaryKeys") List primaryKeys);
}