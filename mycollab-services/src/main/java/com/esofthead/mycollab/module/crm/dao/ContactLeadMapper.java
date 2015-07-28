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
import com.esofthead.mycollab.module.crm.domain.ContactLead;
import com.esofthead.mycollab.module.crm.domain.ContactLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int countByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int deleteByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int insert(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int insertSelective(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    List<ContactLead> selectByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    ContactLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ContactLead record, @Param("example") ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int updateByExample(@Param("record") ContactLead record, @Param("example") ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int updateByPrimaryKeySelective(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    int updateByPrimaryKey(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    Integer insertAndReturnKey(ContactLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbggenerated Tue Jul 28 15:35:22 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ContactLead record, @Param("primaryKeys") List primaryKeys);
}