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
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int countByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<Contact> selectByExampleWithBLOBs(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<Contact> selectByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    Contact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExample(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKey(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    Integer insertAndReturnKey(Contact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Contact record, @Param("primaryKeys") List primaryKeys);
}