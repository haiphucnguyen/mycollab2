package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormSectionField;
import com.esofthead.mycollab.form.domain.FormSectionFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface FormSectionFieldMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int countByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int deleteByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int insert(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int insertSelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    List<FormSectionField> selectByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    FormSectionField selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int updateByExampleSelective(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int updateByExample(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int updateByPrimaryKeySelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    int updateByPrimaryKey(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    Integer insertAndReturnKey(FormSectionField value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Tue Jan 05 23:09:05 ICT 2016
     */
    void massUpdateWithSession(@Param("record") FormSectionField record, @Param("primaryKeys") List primaryKeys);
}