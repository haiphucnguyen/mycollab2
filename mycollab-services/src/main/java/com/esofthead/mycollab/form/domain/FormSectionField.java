/*Domain class of table m_form_section_field*/
package com.esofthead.mycollab.form.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class FormSectionField extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.id
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.sectionId
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    private Integer sectionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isMandatory
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    private Boolean ismandatory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldIndex
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    private Integer fieldindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.displayName
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String displayname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldFormat
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=200, message="Field value is too long")
    private String fieldformat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldname
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String fieldname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.id
     *
     * @return the value of m_form_section_field.id
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.id
     *
     * @param id the value for m_form_section_field.id
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.sectionId
     *
     * @return the value of m_form_section_field.sectionId
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public Integer getSectionid() {
        return sectionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.sectionId
     *
     * @param sectionid the value for m_form_section_field.sectionId
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.isMandatory
     *
     * @return the value of m_form_section_field.isMandatory
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public Boolean getIsmandatory() {
        return ismandatory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.isMandatory
     *
     * @param ismandatory the value for m_form_section_field.isMandatory
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setIsmandatory(Boolean ismandatory) {
        this.ismandatory = ismandatory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.fieldIndex
     *
     * @return the value of m_form_section_field.fieldIndex
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public Integer getFieldindex() {
        return fieldindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.fieldIndex
     *
     * @param fieldindex the value for m_form_section_field.fieldIndex
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setFieldindex(Integer fieldindex) {
        this.fieldindex = fieldindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.displayName
     *
     * @return the value of m_form_section_field.displayName
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.displayName
     *
     * @param displayname the value for m_form_section_field.displayName
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.fieldFormat
     *
     * @return the value of m_form_section_field.fieldFormat
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public String getFieldformat() {
        return fieldformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.fieldFormat
     *
     * @param fieldformat the value for m_form_section_field.fieldFormat
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setFieldformat(String fieldformat) {
        this.fieldformat = fieldformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.fieldname
     *
     * @return the value of m_form_section_field.fieldname
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public String getFieldname() {
        return fieldname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.fieldname
     *
     * @param fieldname the value for m_form_section_field.fieldname
     *
     * @mbggenerated Fri Oct 18 15:45:32 ICT 2013
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }
}