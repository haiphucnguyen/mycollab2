package com.esofthead.mycollab.common.domain;

public class SaveSearchResultWithBLOBs extends SaveSearchResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryText
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryName
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    private String queryname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryText
     *
     * @return the value of s_save_search_result.queryText
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    public String getQuerytext() {
        return querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.queryText
     *
     * @param querytext the value for s_save_search_result.queryText
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    public void setQuerytext(String querytext) {
        this.querytext = querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryName
     *
     * @return the value of s_save_search_result.queryName
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    public String getQueryname() {
        return queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.queryName
     *
     * @param queryname the value for s_save_search_result.queryName
     *
     * @mbggenerated Fri Jun 14 14:47:42 GMT+07:00 2013
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }
}