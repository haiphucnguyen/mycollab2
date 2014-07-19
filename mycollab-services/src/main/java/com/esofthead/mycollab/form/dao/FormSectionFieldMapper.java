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
import com.esofthead.mycollab.form.domain.FormSectionField;
import com.esofthead.mycollab.form.domain.FormSectionFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("ucd")
public interface FormSectionFieldMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int countByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int deleteByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int insert(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int insertSelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    List<FormSectionField> selectByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    FormSectionField selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int updateByExampleSelective(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int updateByExample(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int updateByPrimaryKeySelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    int updateByPrimaryKey(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    Integer insertAndReturnKey(FormSectionField value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Sat Jul 19 21:11:35 ICT 2014
     */
    void massUpdateWithSession(@Param("record") FormSectionField record, @Param("primaryKeys") List primaryKeys);
}