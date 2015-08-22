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
/*Domain class of table m_tracker_query*/
package com.esofthead.mycollab.module.tracker.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_tracker_query")
public class Query extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.id
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.queryname
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("queryname")
    private String queryname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.sharetype
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Column("sharetype")
    private Integer sharetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.createddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Column("createddate")
    private Date createddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.updateddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Column("updateddate")
    private Date updateddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.owner
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("owner")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.querytext
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Length(max=4000, message="Field value is too long")
    @Column("querytext")
    private String querytext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.description
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Length(max=1000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_query.projectid
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    @Column("projectid")
    private Integer projectid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Query item = (Query)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(651, 151).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.id
     *
     * @return the value of m_tracker_query.id
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.id
     *
     * @param id the value for m_tracker_query.id
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.queryname
     *
     * @return the value of m_tracker_query.queryname
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public String getQueryname() {
        return queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.queryname
     *
     * @param queryname the value for m_tracker_query.queryname
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.sharetype
     *
     * @return the value of m_tracker_query.sharetype
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public Integer getSharetype() {
        return sharetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.sharetype
     *
     * @param sharetype the value for m_tracker_query.sharetype
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setSharetype(Integer sharetype) {
        this.sharetype = sharetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.createddate
     *
     * @return the value of m_tracker_query.createddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public Date getCreateddate() {
        return createddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.createddate
     *
     * @param createddate the value for m_tracker_query.createddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.updateddate
     *
     * @return the value of m_tracker_query.updateddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public Date getUpdateddate() {
        return updateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.updateddate
     *
     * @param updateddate the value for m_tracker_query.updateddate
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.owner
     *
     * @return the value of m_tracker_query.owner
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.owner
     *
     * @param owner the value for m_tracker_query.owner
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.querytext
     *
     * @return the value of m_tracker_query.querytext
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public String getQuerytext() {
        return querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.querytext
     *
     * @param querytext the value for m_tracker_query.querytext
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setQuerytext(String querytext) {
        this.querytext = querytext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.description
     *
     * @return the value of m_tracker_query.description
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.description
     *
     * @param description the value for m_tracker_query.description
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_query.projectid
     *
     * @return the value of m_tracker_query.projectid
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_query.projectid
     *
     * @param projectid the value for m_tracker_query.projectid
     *
     * @mbggenerated Fri Aug 21 10:43:58 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public enum Field {
        id,
        queryname,
        sharetype,
        createddate,
        updateddate,
        owner,
        querytext,
        description,
        projectid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}