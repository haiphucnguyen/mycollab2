/*Domain class of table m_custom_field_format*/
package com.esofthead.mycollab.form.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class CustomFieldFormat extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_custom_field_format.id
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_custom_field_format.field_format
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=200, message="Field value is too long")
    private String fieldFormat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_custom_field_format.field_name
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String fieldName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_custom_field_format.module
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_custom_field_format.sAccountId
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_custom_field_format.id
     *
     * @return the value of m_custom_field_format.id
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_custom_field_format.id
     *
     * @param id the value for m_custom_field_format.id
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_custom_field_format.field_format
     *
     * @return the value of m_custom_field_format.field_format
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public String getFieldFormat() {
        return fieldFormat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_custom_field_format.field_format
     *
     * @param fieldFormat the value for m_custom_field_format.field_format
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_custom_field_format.field_name
     *
     * @return the value of m_custom_field_format.field_name
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_custom_field_format.field_name
     *
     * @param fieldName the value for m_custom_field_format.field_name
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_custom_field_format.module
     *
     * @return the value of m_custom_field_format.module
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_custom_field_format.module
     *
     * @param module the value for m_custom_field_format.module
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_custom_field_format.sAccountId
     *
     * @return the value of m_custom_field_format.sAccountId
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_custom_field_format.sAccountId
     *
     * @param saccountid the value for m_custom_field_format.sAccountId
     *
     * @mbggenerated Thu Oct 17 10:52:19 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}