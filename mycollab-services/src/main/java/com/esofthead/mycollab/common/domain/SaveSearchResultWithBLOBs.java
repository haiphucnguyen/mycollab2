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

import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
public class SaveSearchResultWithBLOBs extends SaveSearchResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryText
     *
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("queryText")
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryName
     *
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("queryName")
    private String queryname;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryText
     *
     * @return the value of s_save_search_result.queryText
     *
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
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
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
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
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
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
     * @mbggenerated Tue Sep 01 11:38:26 ICT 2015
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    public enum Field {
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