/*Domain class of table m_form_section_field*/
package com.mycollab.form.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_form_section_field")
@Alias("FormSectionField")
public class FormSectionField extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.id
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.sectionId
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("sectionId")
    private Integer sectionid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isMandatory
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("isMandatory")
    private Boolean ismandatory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldIndex
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("fieldIndex")
    private Integer fieldindex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.displayName
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=100, message="Field value is too long")
    @Column("displayName")
    private String displayname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldFormat
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=200, message="Field value is too long")
    @Column("fieldFormat")
    private String fieldformat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldname
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("fieldname")
    private String fieldname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.fieldType
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotEmpty
    @Length(max=1000, message="Field value is too long")
    @Column("fieldType")
    private String fieldtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isRequired
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("isRequired")
    private Boolean isrequired;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_section_field.isCustom
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
     */
    @NotNull
    @Column("isCustom")
    private Boolean iscustom;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        FormSectionField item = (FormSectionField)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1027, 461).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_section_field.id
     *
     * @return the value of m_form_section_field.id
     *
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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
     * @mbg.generated Fri Feb 01 07:44:16 CST 2019
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