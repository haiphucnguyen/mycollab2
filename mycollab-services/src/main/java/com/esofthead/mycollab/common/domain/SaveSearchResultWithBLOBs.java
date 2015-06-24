package com.esofthead.mycollab.common.domain;

@SuppressWarnings("ucd")
public class SaveSearchResultWithBLOBs extends SaveSearchResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryText
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("queryText")
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryName
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("queryName")
    private String queryname;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryText
     *
     * @return the value of s_save_search_result.queryText
     *
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
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
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
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
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
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
     * @mbggenerated Tue Jun 23 23:25:35 ICT 2015
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    public static enum Field {
        id,
        saveuser,
        saccountid,
        type,
        createdtime,
        lastupdatedtime,
        querytext,
        queryname;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}