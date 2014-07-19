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
package com.esofthead.mycollab.common.domain;

public class SaveSearchResultWithBLOBs extends SaveSearchResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryText
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("queryText")
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryName
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("queryName")
    private String queryname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryText
     *
     * @return the value of s_save_search_result.queryText
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
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
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
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
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
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
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }
}