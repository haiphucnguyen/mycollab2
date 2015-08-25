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

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_form_section_field")
public class FormSectionField extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.id
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.sectionId
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("sectionId")
    private Integer sectionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isMandatory
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("isMandatory")
    private Boolean ismandatory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldIndex
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("fieldIndex")
    private Integer fieldindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.displayName
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("displayName")
    private String displayname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldFormat
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Length(max=200, message="Field value is too long")
    @Column("fieldFormat")
    private String fieldformat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldname
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("fieldname")
    private String fieldname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldType
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Length(max=1000, message="Field value is too long")
    @Column("fieldType")
    private String fieldtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isRequired
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("isRequired")
    private Boolean isrequired;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isCustom
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    @Column("isCustom")
    private Boolean iscustom;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        FormSectionField item = (FormSectionField)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1607, 1073).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.id
     *
     * @return the value of m_form_section_field.id
     *
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
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
     * @mbggenerated Tue Aug 25 14:46:06 ICT 2015
     */
    public void setIscustom(Boolean iscustom) {
        this.iscustom = iscustom;
    }

    public enum Field {
        id,
        sectionid,
        ismandatory,
        fieldindex,
        displayname,
        fieldformat,
        fieldname,
        fieldtype,
        isrequired,
        iscustom;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}