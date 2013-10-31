package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormSectionField;
import com.esofthead.mycollab.form.domain.FormSectionFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormSectionFieldMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int countByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int deleteByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int insert(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int insertSelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    List<FormSectionField> selectByExample(FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    FormSectionField selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int updateByExampleSelective(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int updateByExample(@Param("record") FormSectionField record, @Param("example") FormSectionFieldExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    int updateByPrimaryKeySelective(FormSectionField record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    Integer insertAndReturnKey(FormSectionField value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section_field
     *
     * @mbggenerated Thu Oct 31 22:34:21 ICT 2013
     */
    void massUpdateWithSession(@Param("record") FormSectionField record, @Param("primaryKeys") List primaryKeys);
}