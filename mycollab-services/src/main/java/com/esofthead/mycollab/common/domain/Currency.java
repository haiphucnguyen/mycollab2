/*Domain class of table s_currency*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Currency extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.id
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.shortname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String shortname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.isocode
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String isocode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.symbol
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String symbol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.conversionrate
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    private Double conversionrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.fullname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String fullname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.id
     *
     * @return the value of s_currency.id
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.shortname
     *
     * @return the value of s_currency.shortname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.shortname
     *
     * @param shortname the value for s_currency.shortname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.isocode
     *
     * @return the value of s_currency.isocode
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
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
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setConversionrate(Double conversionrate) {
        this.conversionrate = conversionrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.fullname
     *
     * @return the value of s_currency.fullname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.fullname
     *
     * @param fullname the value for s_currency.fullname
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}