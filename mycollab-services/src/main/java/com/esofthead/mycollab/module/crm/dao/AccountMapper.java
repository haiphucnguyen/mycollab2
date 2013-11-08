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
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    List<Account> selectByExampleWithBLOBs(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    Integer insertAndReturnKey(Account value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    void massUpdateWithSession(@Param("record") Account record, @Param("primaryKeys") List primaryKeys);
}