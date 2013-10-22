package com.esofthead.mycollab.form.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.form.domain.FormSection;
import com.esofthead.mycollab.form.domain.FormSectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FormSectionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int countByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int deleteByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int insert(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int insertSelective(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    List<FormSection> selectByExample(FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    FormSection selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int updateByExampleSelective(@Param("record") FormSection record, @Param("example") FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int updateByExample(@Param("record") FormSection record, @Param("example") FormSectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    int updateByPrimaryKeySelective(FormSection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    Integer insertAndReturnKey(FormSection value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_form_section
     *
     * @mbggenerated Tue Oct 22 22:49:55 ICT 2013
     */
    void massUpdateWithSession(@Param("record") FormSection record, @Param("primaryKeys") List primaryKeys);
}