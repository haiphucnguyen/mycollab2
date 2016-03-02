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
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.ContactCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactCaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int countByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int deleteByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int insert(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int insertSelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    List<ContactCase> selectByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    ContactCase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int updateByExample(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int updateByPrimaryKeySelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    int updateByPrimaryKey(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    Integer insertAndReturnKey(ContactCase value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Mar 02 20:08:05 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ContactCase record, @Param("primaryKeys") List primaryKeys);
}