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
import com.esofthead.mycollab.module.crm.domain.AccountLead;
import com.esofthead.mycollab.module.crm.domain.AccountLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int countByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int deleteByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int insert(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int insertSelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    List<AccountLead> selectByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    AccountLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int updateByExampleSelective(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int updateByExample(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int updateByPrimaryKeySelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    int updateByPrimaryKey(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    Integer insertAndReturnKey(AccountLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbggenerated Mon Sep 21 13:52:02 ICT 2015
     */
    void massUpdateWithSession(@Param("record") AccountLead record, @Param("primaryKeys") List primaryKeys);
}