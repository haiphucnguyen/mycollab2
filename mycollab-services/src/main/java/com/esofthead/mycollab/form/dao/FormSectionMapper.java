package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormSection;
import com.esofthead.mycollab.form.domain.FormSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface FormSectionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int countByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insert(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insertSelective(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    List<FormSection> selectByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    FormSection selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExampleSelective(@Param("record") FormSection record, @Param("example") FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExample(@Param("record") FormSection record, @Param("example") FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKeySelective(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKey(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    Integer insertAndReturnKey(FormSection value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void massUpdateWithSession(@Param("record") FormSection record, @Param("primaryKeys") List primaryKeys);
}