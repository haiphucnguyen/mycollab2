/*Domain class of table m_form_custom_field_value*/
package com.esofthead.mycollab.form.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

class FormCustomFieldValue extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.id
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.module
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.typeid
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.number1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Double number1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.number2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Double number2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.number3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Double number3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.number4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Double number4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.number5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Double number5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.int1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer int1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.int2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer int2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.int3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer int3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.int4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer int4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.int5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Integer int5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.date1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Date date1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.date2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Date date2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.date3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Date date3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.date4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Date date4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_form_custom_field_value.date5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    private Date date5;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.id
     *
     * @return the value of m_form_custom_field_value.id
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.id
     *
     * @param id the value for m_form_custom_field_value.id
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.module
     *
     * @return the value of m_form_custom_field_value.module
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.module
     *
     * @param module the value for m_form_custom_field_value.module
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.typeid
     *
     * @return the value of m_form_custom_field_value.typeid
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.typeid
     *
     * @param typeid the value for m_form_custom_field_value.typeid
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.number1
     *
     * @return the value of m_form_custom_field_value.number1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Double getNumber1() {
        return number1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.number1
     *
     * @param number1 the value for m_form_custom_field_value.number1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.number2
     *
     * @return the value of m_form_custom_field_value.number2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Double getNumber2() {
        return number2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.number2
     *
     * @param number2 the value for m_form_custom_field_value.number2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.number3
     *
     * @return the value of m_form_custom_field_value.number3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Double getNumber3() {
        return number3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.number3
     *
     * @param number3 the value for m_form_custom_field_value.number3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setNumber3(Double number3) {
        this.number3 = number3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.number4
     *
     * @return the value of m_form_custom_field_value.number4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Double getNumber4() {
        return number4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.number4
     *
     * @param number4 the value for m_form_custom_field_value.number4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setNumber4(Double number4) {
        this.number4 = number4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.number5
     *
     * @return the value of m_form_custom_field_value.number5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Double getNumber5() {
        return number5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.number5
     *
     * @param number5 the value for m_form_custom_field_value.number5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setNumber5(Double number5) {
        this.number5 = number5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.int1
     *
     * @return the value of m_form_custom_field_value.int1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getInt1() {
        return int1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.int1
     *
     * @param int1 the value for m_form_custom_field_value.int1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.int2
     *
     * @return the value of m_form_custom_field_value.int2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getInt2() {
        return int2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.int2
     *
     * @param int2 the value for m_form_custom_field_value.int2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.int3
     *
     * @return the value of m_form_custom_field_value.int3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getInt3() {
        return int3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.int3
     *
     * @param int3 the value for m_form_custom_field_value.int3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setInt3(Integer int3) {
        this.int3 = int3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.int4
     *
     * @return the value of m_form_custom_field_value.int4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getInt4() {
        return int4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.int4
     *
     * @param int4 the value for m_form_custom_field_value.int4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setInt4(Integer int4) {
        this.int4 = int4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.int5
     *
     * @return the value of m_form_custom_field_value.int5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Integer getInt5() {
        return int5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.int5
     *
     * @param int5 the value for m_form_custom_field_value.int5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setInt5(Integer int5) {
        this.int5 = int5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.date1
     *
     * @return the value of m_form_custom_field_value.date1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Date getDate1() {
        return date1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.date1
     *
     * @param date1 the value for m_form_custom_field_value.date1
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.date2
     *
     * @return the value of m_form_custom_field_value.date2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.date2
     *
     * @param date2 the value for m_form_custom_field_value.date2
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.date3
     *
     * @return the value of m_form_custom_field_value.date3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Date getDate3() {
        return date3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.date3
     *
     * @param date3 the value for m_form_custom_field_value.date3
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.date4
     *
     * @return the value of m_form_custom_field_value.date4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Date getDate4() {
        return date4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.date4
     *
     * @param date4 the value for m_form_custom_field_value.date4
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_form_custom_field_value.date5
     *
     * @return the value of m_form_custom_field_value.date5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public Date getDate5() {
        return date5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_form_custom_field_value.date5
     *
     * @param date5 the value for m_form_custom_field_value.date5
     *
     * @mbggenerated Sat Oct 19 17:06:51 ICT 2013
     */
    public void setDate5(Date date5) {
        this.date5 = date5;
    }
}