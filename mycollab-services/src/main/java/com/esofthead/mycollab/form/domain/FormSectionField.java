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
/*Domain class of table m_form_section_field*/
package com.esofthead.mycollab.form.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class FormSectionField extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.id
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.sectionId
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Integer sectionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isMandatory
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Boolean ismandatory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldIndex
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Integer fieldindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.displayName
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String displayname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldFormat
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=200, message="Field value is too long")
    private String fieldformat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldname
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String fieldname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldType
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=1000, message="Field value is too long")
    private String fieldtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isRequired
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Boolean isrequired;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isCustom
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    private Boolean iscustom;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.id
     *
     * @return the value of m_form_section_field.id
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
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
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.fieldType
     *
     * @return the value of m_form_section_field.fieldType
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public String getFieldtype() {
        return fieldtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.fieldType
     *
     * @param fieldtype the value for m_form_section_field.fieldType
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.isRequired
     *
     * @return the value of m_form_section_field.isRequired
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public Boolean getIsrequired() {
        return isrequired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.isRequired
     *
     * @param isrequired the value for m_form_section_field.isRequired
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public void setIsrequired(Boolean isrequired) {
        this.isrequired = isrequired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.isCustom
     *
     * @return the value of m_form_section_field.isCustom
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public Boolean getIscustom() {
        return iscustom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_section_field.isCustom
     *
     * @param iscustom the value for m_form_section_field.isCustom
     *
     * @mbggenerated Fri Nov 01 09:00:08 ICT 2013
     */
    public void setIscustom(Boolean iscustom) {
        this.iscustom = iscustom;
    }
}