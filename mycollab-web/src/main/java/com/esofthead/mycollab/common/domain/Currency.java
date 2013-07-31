/*Domain class of table s_currency*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Currency extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.id
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.name
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.isocode
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String isocode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.symbol
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String symbol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.conversionrate
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    private Double conversionrate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.id
     *
     * @return the value of s_currency.id
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.id
     *
     * @param id the value for s_currency.id
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.name
     *
     * @return the value of s_currency.name
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.name
     *
     * @param name the value for s_currency.name
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.isocode
     *
     * @return the value of s_currency.isocode
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public String getIsocode() {
        return isocode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.isocode
     *
     * @param isocode the value for s_currency.isocode
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.symbol
     *
     * @return the value of s_currency.symbol
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.symbol
     *
     * @param symbol the value for s_currency.symbol
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.conversionrate
     *
     * @return the value of s_currency.conversionrate
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public Double getConversionrate() {
        return conversionrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.conversionrate
     *
     * @param conversionrate the value for s_currency.conversionrate
     *
     * @mbggenerated Tue Jul 30 16:36:54 GMT+07:00 2013
     */
    public void setConversionrate(Double conversionrate) {
        this.conversionrate = conversionrate;
    }
}