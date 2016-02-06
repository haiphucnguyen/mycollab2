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
package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormCustomFieldValueExample;
import com.esofthead.mycollab.form.domain.FormCustomFieldValueWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface FormCustomFieldValueMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int countByExample(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int deleteByExample(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int insert(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int insertSelective(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    List<FormCustomFieldValueWithBLOBs> selectByExampleWithBLOBs(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    FormCustomFieldValueWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int updateByExampleSelective(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("example") FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("example") FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int updateByPrimaryKeySelective(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    Integer insertAndReturnKey(FormCustomFieldValueWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Sat Feb 06 17:34:27 ICT 2016
     */
    void massUpdateWithSession(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}