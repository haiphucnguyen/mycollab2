package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormCustomFieldValueExample;
import com.esofthead.mycollab.form.domain.FormCustomFieldValueWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormCustomFieldValueMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int countByExample(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int deleteByExample(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int insert(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int insertSelective(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    List<FormCustomFieldValueWithBLOBs> selectByExampleWithBLOBs(FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    FormCustomFieldValueWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int updateByExampleSelective(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("example") FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("example") FormCustomFieldValueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int updateByPrimaryKeySelective(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    int updateByPrimaryKeyWithBLOBs(FormCustomFieldValueWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    Integer insertAndReturnKey(FormCustomFieldValueWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_custom_field_value
     *
     * @mbggenerated Thu Apr 03 11:05:07 ICT 2014
     */
    void massUpdateWithSession(@Param("record") FormCustomFieldValueWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}