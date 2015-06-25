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
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactOpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int countByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insert(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insertSelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<ContactOpportunity> selectByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    ContactOpportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExample(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeySelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKey(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    Integer insertAndReturnKey(ContactOpportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ContactOpportunity record, @Param("primaryKeys") List primaryKeys);
}