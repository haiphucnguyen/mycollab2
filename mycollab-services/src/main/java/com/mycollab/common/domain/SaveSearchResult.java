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
/*Domain class of table s_save_search_result*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_save_search_result")
public class SaveSearchResult extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.id
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.saveUser
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("saveUser")
    private String saveuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.sAccountId
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.type
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.createdTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryName
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Length(max=400, message="Field value is too long")
    @Column("queryName")
    private String queryname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.isShared
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Column("isShared")
    private Boolean isshared;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_save_search_result.queryText
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    @Length(max=65535, message="Field value is too long")
    @Column("queryText")
    private String querytext;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        SaveSearchResult item = (SaveSearchResult)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(11, 527).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.id
     *
     * @return the value of s_save_search_result.id
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.id
     *
     * @param id the value for s_save_search_result.id
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.saveUser
     *
     * @return the value of s_save_search_result.saveUser
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public String getSaveuser() {
        return saveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.saveUser
     *
     * @param saveuser the value for s_save_search_result.saveUser
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setSaveuser(String saveuser) {
        this.saveuser = saveuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.sAccountId
     *
     * @return the value of s_save_search_result.sAccountId
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.sAccountId
     *
     * @param saccountid the value for s_save_search_result.sAccountId
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.type
     *
     * @return the value of s_save_search_result.type
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.type
     *
     * @param type the value for s_save_search_result.type
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.createdTime
     *
     * @return the value of s_save_search_result.createdTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.createdTime
     *
     * @param createdtime the value for s_save_search_result.createdTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.lastUpdatedTime
     *
     * @return the value of s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.lastUpdatedTime
     *
     * @param lastupdatedtime the value for s_save_search_result.lastUpdatedTime
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryName
     *
     * @return the value of s_save_search_result.queryName
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
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
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.isShared
     *
     * @return the value of s_save_search_result.isShared
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public Boolean getIsshared() {
        return isshared;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_save_search_result.isShared
     *
     * @param isshared the value for s_save_search_result.isShared
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setIsshared(Boolean isshared) {
        this.isshared = isshared;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_save_search_result.queryText
     *
     * @return the value of s_save_search_result.queryText
     *
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
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
     * @mbggenerated Sun Jul 31 09:24:06 ICT 2016
     */
    public void setQuerytext(String querytext) {
        this.querytext = querytext;
    }

    public enum Field {
        id,
        saveuser,
        saccountid,
        type,
        createdtime,
        lastupdatedtime,
        queryname,
        isshared,
        querytext;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}