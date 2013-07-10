/*Domain class of table s_account_currency*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class AccountCurrency extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_currency.id
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_currency.currencyid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_currency.accountid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    private Integer accountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_currency.id
     *
     * @return the value of s_account_currency.id
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_currency.id
     *
     * @param id the value for s_account_currency.id
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_currency.currencyid
     *
     * @return the value of s_account_currency.currencyid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_currency.currencyid
     *
     * @param currencyid the value for s_account_currency.currencyid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_currency.accountid
     *
     * @return the value of s_account_currency.accountid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_currency.accountid
     *
     * @param accountid the value for s_account_currency.accountid
     *
     * @mbggenerated Wed Jul 10 21:12:52 GMT+07:00 2013
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }
}